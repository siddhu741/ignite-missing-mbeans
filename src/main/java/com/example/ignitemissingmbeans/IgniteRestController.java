package com.example.ignitemissingmbeans;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A sample REST controller that can be accessed without authorization.
 */
@RestController
public class IgniteRestController {

    private IgniteMBeanService igniteMBeanService;

    @Autowired
    public IgniteRestController(IgniteMBeanService igniteMBeanService) {
        this.igniteMBeanService = igniteMBeanService;
    }

    @GetMapping("/log-ignite-mBeans-count")
    public void logIgniteMBeansCount() {
        this.igniteMBeanService.queryMBeans();
    }

}
