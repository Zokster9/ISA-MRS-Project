package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("select t from Tag t where t.name = ?1")
    public Tag findTagByName(String tagName);
}
