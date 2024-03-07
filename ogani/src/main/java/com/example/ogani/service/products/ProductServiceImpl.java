package com.example.ogani.service.products;

import com.example.ogani.common.Constants;
import com.example.ogani.entity.ImageEntity;
import com.example.ogani.entity.ProductEntity;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.ProductRequest;
import com.example.ogani.model.response.ProductResponse;
import com.example.ogani.repository.CategoryRepository;
import com.example.ogani.repository.ImageRepository;
import com.example.ogani.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<ProductResponse> getList() {
        var productList = productRepository.findAll(Sort.by("id").descending());
        return productList.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .categoryEntity(productEntity.getCategoryEntity())
                .imageEntities(productEntity.getImageEntities())
                .build();
    }

    @Override
    public ProductEntity getProductsById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_ID_NOT_EXIST, id)));
    }

    @Override
    public ProductEntity createProduct(ProductRequest productRequest) {
        return productRepository.save(buildProductsEntity(new ProductEntity(), productRequest));
    }

    @Override
    public ProductEntity updateProduct(long id, ProductRequest productRequest) {
        var productTemp = productRepository.findById(id)
                .map(productEntity -> buildProductsEntity(productEntity, productRequest))
                .orElseThrow(() -> new NotFoundException(String.format(Constants.PRODUCT_ID_NOT_EXIST, id)));
        if(Objects.isNull(productTemp)) {
            log.info("error update product id = {}", id);
            return null;
        }
        return productRepository.save(productTemp);
    }

    @Override
    public void deleteProduct(long id) {
        var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.ID_DELETE_NOT_EXIST, id)));
        productEntity.getImageEntities().remove(this);
        productRepository.delete(productEntity);
    }

    @Override
    public List<ProductEntity> getListByPrice() {
        return productRepository.getListByPrice();
    }

    @Override
    public List<ProductResponse> getListProductByCategory(long id) {
        var productList = productRepository.getListProductByCategory(id);
        return productList.stream().map(this::mapToProductResponse).toList();

    }

    @Override
    public List<ProductEntity> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }

    @Override
    public List<ProductEntity> getListByPriceRange(long id, int min, int max) {
        return productRepository.getListProductByPriceRange(id, min, max);
    }

    @Override
    public List<ProductEntity> findRelatedProduct(long id) {
        return productRepository.findRelatedProduct(id);
    }

    private ProductEntity buildProductsEntity(ProductEntity productEntity, ProductRequest productRequest) {
        productEntity.setName(productRequest.getName());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setQuantity(productRequest.getQuantity());
        var categoryEntity = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new NotFoundException(Constants.CATEGORY_ID_NOT_EXIST + productRequest.getCategoryId()));
        productEntity.setCategoryEntity(categoryEntity);

        Set<ImageEntity> images = new HashSet<>();
        for (long imageId: productRequest.getImageIds()) {
            var image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new NotFoundException(Constants.IMAGE_ID_NOT_EXIST + imageId));
            images.add(image);
        }
        productEntity.setImageEntities(images);

        return productEntity;
    }

    @Override
    public List<ProductEntity> getListNewest(int number) {
        return productRepository.getListNewest(number);
    }
}
