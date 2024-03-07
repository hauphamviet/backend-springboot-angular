package com.example.ogani.service.blogs;

import com.example.ogani.common.Constants;
import com.example.ogani.entity.BlogEntity;
import com.example.ogani.entity.TagEntity;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.BlogRequest;
import com.example.ogani.model.response.BlogResponse;
import com.example.ogani.repository.BlogRepository;
import com.example.ogani.repository.ImageRepository;
import com.example.ogani.repository.TagRepository;
import com.example.ogani.repository.UserRepository;
import com.example.ogani.service.images.ImageService;
import com.example.ogani.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogRepository blogRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    @Override
    public List<BlogResponse> getList() {
        var blogList = blogRepository.findAll(Sort.by("id").descending());
        return blogList.stream().map(this::mapToBlogResponse).toList();
    }

    private BlogResponse mapToBlogResponse(BlogEntity blogEntity) {
        return BlogResponse.builder()
                .id(blogEntity.getId())
                .title(blogEntity.getTitle())
                .description(blogEntity.getDescription())
                .content(blogEntity.getContent())
                .createAt(blogEntity.getCreateAt())
                .image(blogEntity.getImage())
                .user(blogEntity.getUser())
                .tags(blogEntity.getTags())
                .build();
    }

    @Override
    public List<BlogEntity> getListNewest(int limit) {
        return blogRepository.getListNewest(limit);
    }

    @Override
    public BlogEntity getBlogById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BLOG_ID_NOT_EXIST, id)));
    }

    @Override
    public void createBlog(BlogRequest blogRequest) {
        var imageEntity = imageRepository.findById(blogRequest.getImageId())
                .orElseThrow(() -> new NotFoundException(Constants.BLOG_NOT_FOUND_IMAGE));
        var userEntity = userRepository.findByUsername(blogRequest.getUsername())
                .orElseThrow(() -> new NotFoundException(Constants.BLOG_NOT_FOUND_USER));
        Set<TagEntity> tags = new HashSet<>();
        for (Long tagId : blogRequest.getTags()) {
            var tagEntity = tagRepository.findById(tagId)
                    .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_TAG));
            tags.add(tagEntity);
            //Vòng lặp để duyệt qua danh sách các id của các thẻ (tags) được gửi trong yêu cầu (blogRequest.getTags()):
        }

        var blogEntity = BlogEntity.builder()
                .title(blogRequest.getTitle())
                .description(blogRequest.getDescription())
                .content(blogRequest.getContent())
                .image(imageEntity)
                .user(userEntity)
                .createAt(new Timestamp(System.currentTimeMillis()))
                .tags(tags)
                .build();
        blogRepository.save(blogEntity);
        log.info("Blog {} is saved", blogEntity.getId());

    }

    private BlogEntity buildBlogEntity(BlogEntity blogEntity, BlogRequest blogRequest) {

        var imageEntity = imageRepository.findById(blogRequest.getImageId())
                .orElseThrow(() -> new NotFoundException(Constants.BLOG_NOT_FOUND_IMAGE));
        Set<TagEntity> tags = new HashSet<>();
        for (Long tagId : blogRequest.getTags()) {
            var tagEntity = tagRepository.findById(tagId)
                    .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_TAG));
            tags.add(tagEntity);
            //Vòng lặp để duyệt qua danh sách các id của các thẻ (tags) được gửi trong yêu cầu (blogRequest.getTags()):
        }

        return BlogEntity.builder()
                .title(blogRequest.getTitle())
                .description(blogRequest.getDescription())
                .content(blogRequest.getContent())
                .image(imageEntity)
                .tags(tags)
                .build();
    }

    @Override
    public BlogEntity updateBlog(long id, BlogRequest blogRequest) {
        var blogTemp = blogRepository.findById(id)
                .map(blogEntity -> buildBlogEntity(blogEntity, blogRequest))
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BLOG_ID_NOT_EXIST, id)));

        if (Objects.isNull(blogTemp)) {
            log.error("error update blog id = {}", id);
            return null;
        }
        return blogRepository.save(blogTemp);
    }

    @Override
    public void deleteBlog(long id) {
        var blogEntity = blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.ID_DELETE_BLOG_NOT_EXIST, id)));
        blogRepository.delete(blogEntity);
    }
}
