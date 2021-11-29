package com.example.clientproject.service.dtos;

import com.example.clientproject.data.tags.Tags;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TagsDTO {
    long tagId;
    String tagName;

    public TagsDTO(Tags tags) {
        this(
                tags.getTagId(),
                tags.getTagName()
                );
    }
}
