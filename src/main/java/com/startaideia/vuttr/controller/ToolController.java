package com.startaideia.vuttr.controller;

import java.util.List;

import com.startaideia.vuttr.data.ToolRepository;
import com.startaideia.vuttr.model.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

    @Autowired
    private ToolRepository repository;

    @GetMapping("/tools")
    public List<Tool> findTools(@RequestParam(required = false) String tag) {

        if (tag != null && !"".equals(tag)) {
            return repository.findByTag(tag);
        }

        return repository.findAll();
    }

    @PostMapping("/tools")
    public ResponseEntity<Tool> createTool(@RequestBody Tool tool) {
        Tool newTool = repository.save(tool);
        return new ResponseEntity<>(newTool, HttpStatus.CREATED);
    }

    @DeleteMapping("/tools/{id}")
    public ResponseEntity<HttpStatus> deleteTool(@PathVariable("id") long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}