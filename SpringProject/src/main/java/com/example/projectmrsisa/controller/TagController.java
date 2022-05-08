package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.model.Tag;
import com.example.projectmrsisa.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(value="/{description}", produces = "application/json")
    public ResponseEntity<List<Tag>> getTags(@PathVariable String description) {
        try {
            List<Tag> tags = tagService.getTags(description);
            return new ResponseEntity<>(tags, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
