package com.example.ogani.service.blogs;

import com.example.ogani.entity.BlogEntity;
import com.example.ogani.model.request.BlogRequest;
import com.example.ogani.model.response.BlogResponse;

import java.util.List;

public interface BlogService {

    List<BlogResponse> getList();

    List<BlogEntity> getListNewest(int limit);

    BlogEntity getBlogById(long id);

    void createBlog(BlogRequest blogRequest);

    BlogEntity updateBlog(long id, BlogRequest blogRequest);

    void deleteBlog(long id);

}
