package com.example.ogani.service.tags;

import com.example.ogani.common.Constants;
import com.example.ogani.entity.TagEntity;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.TagRequest;
import com.example.ogani.model.response.TagResponse;
import com.example.ogani.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    @Override
    public List<TagResponse> getListTag() {
        var tagList = tagRepository.findAll(Sort.by("id").descending());
        return tagList.stream().map(this::mapToTagResponse).toList();
    }

    private TagResponse mapToTagResponse(TagEntity tagEntity) {
        return TagResponse.builder()
                .id(tagEntity.getId())
                .name(tagEntity.getName())
                .enable(tagEntity.isEnable())
                .build();
    }

    @Override
    public void createTag(TagRequest tagRequest) {
        var tagEntity = TagEntity.builder()
                .name(tagRequest.getName())
                .enable(false)
                .build();
        tagRepository.save(tagEntity);
        log.info("Tag {} is saved", tagEntity.getId());
    }

    private TagEntity buildTagEntity(TagEntity tagEntity, TagRequest tagRequest) {
        tagEntity.setName(tagRequest.getName());
        return tagEntity;
    }

    @Override
    public TagEntity updateTag(long id, TagRequest tagRequest) {
        var tagTemp = tagRepository.findById(id)
                .map(tagEntity -> buildTagEntity(tagEntity, tagRequest))
                .orElse(null);

        if (Objects.isNull(tagTemp)) {
            log.error("error update tag with id = {}", id);
            return null;
        }
        return tagRepository.save(tagTemp);
    }

    @Override
    public void enableTag(long id) {
        var tagEntity = tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_TAG));
//        if (tagEntity.isEnable()) {
//            tagEntity.setEnable(false);
//        } else {
//            tagEntity.setEnable(true);
//        }
        tagEntity.setEnable(!tagEntity.isEnable());
        tagRepository.save(tagEntity);
    }

    @Override
    public void deleteTag(long id) {
        var tagEntity = tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.ID_DELETE_TAG_NOT_EXIST, id)));
        tagRepository.delete(tagEntity);
    }
}
