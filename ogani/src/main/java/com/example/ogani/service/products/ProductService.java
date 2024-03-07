package com.example.ogani.service.products;

import com.example.ogani.entity.ProductEntity;
import com.example.ogani.model.request.ProductRequest;
import com.example.ogani.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getList();

    ProductEntity getProductsById(long id);

    ProductEntity createProduct(ProductRequest productRequest);

    ProductEntity updateProduct(long id, ProductRequest productRequest);

    void deleteProduct(long id);

    List<ProductEntity> getListByPrice();

    List<ProductResponse> getListProductByCategory(long id);

    List<ProductEntity> searchProduct(String keyword);

    List<ProductEntity> getListByPriceRange(long id, int min, int max);

    List<ProductEntity> findRelatedProduct(long id);

    List<ProductEntity> getListNewest(int number);

}
