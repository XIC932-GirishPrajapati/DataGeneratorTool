package com.data.generator.controller;

import com.data.generator.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DataController {

    @Autowired
    private final DataService generatedService;

    public DataController(DataService generatedService) {
        this.generatedService = generatedService;
    }

    @GetMapping("/generatedData")
    public String generateData(@RequestParam("totalRows") Long totalRows) {
        return generatedService.generateData(totalRows);
    }
}
