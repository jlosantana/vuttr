package com.startaideia.vuttr.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.startaideia.vuttr.data.ToolRepository;
import com.startaideia.vuttr.model.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Returns a tool list.")
    @ResponseStatus(HttpStatus.OK)
    public List<Tool> findTools(
            @Parameter(description = "A tag name for a opicional filtering.") @RequestParam(required = false) String tag) {

        if (tag != null && !"".equals(tag)) {
            return repository.findByTag(tag);
        }

        return repository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates a tool.")
    @ResponseStatus(HttpStatus.CREATED)
    public Tool createTool(@NotNull @Valid @RequestBody final Tool tool) {
        return repository.save(tool);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a tool.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTool(@Parameter(description = "The tool id for delete.") @PathVariable final long id) {
        repository.deleteById(id);
    }

}