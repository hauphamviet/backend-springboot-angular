package com.example.ogani.service.tags;

import com.example.ogani.entity.TagEntity;
import com.example.ogani.model.request.TagRequest;
import com.example.ogani.model.response.TagResponse;

import java.util.List;

public interface TagService {

    List<TagResponse> getListTag();

    void createTag(TagRequest tagRequest);

    TagEntity updateTag(long id, TagRequest tagRequest);

    void enableTag(long id);

    void deleteTag(long id);

}
