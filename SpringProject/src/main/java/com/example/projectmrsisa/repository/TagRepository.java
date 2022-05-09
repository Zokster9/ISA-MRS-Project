package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("select t from Tag t where t.name = ?1 and t.description = ?2")
    public Tag findTagByNameAndDescription(String tagName, String description);

    @Query("select t from Tag t where t.description = ?1")
    public List<Tag> findTagByDescription(String description);
}
