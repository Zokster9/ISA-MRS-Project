package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Tag;
import com.example.projectmrsisa.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    public Set<Tag> findTags(List<String> tags) {
        Set<Tag> retVal = new HashSet<Tag>();
        for (String tag: tags) {
            retVal.add(tagRepository.findTagByName(tag));
        }
        return retVal;
    }
}
