package com.startaideia.vuttr.data;

import java.util.List;

import com.startaideia.vuttr.model.Tool;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    @Query("select t from Tool t JOIN t.tags s where s = LOWER(:tag)")
    List<Tool> findByTag(@Param("tag") String tag);
}