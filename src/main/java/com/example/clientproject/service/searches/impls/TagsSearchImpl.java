package com.example.clientproject.service.searches.impls;

import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.service.dtos.TagsDTO;
import com.example.clientproject.service.searches.TagSearch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagsSearchImpl implements TagSearch {
    private final TagsRepo tagsRepo;

    public TagsSearchImpl(TagsRepo aTagsRepo) {
        tagsRepo = aTagsRepo;
    }

    @Override
    public List<TagsDTO> findAll() {
        return tagsRepo
                .findAll()
                .stream()
                .map(TagsDTO::new)
                .collect(Collectors.toList());
    }
}
