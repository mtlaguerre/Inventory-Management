package com.mtlaguerre.inventory_management.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtlaguerre.inventory_management.services.ProductRmService;

@RestController
@RequestMapping("/products/rms")
@CrossOrigin("http://127.0.0.1:5500")
public class ProductRmController {

    private final ProductRmService productRmService;

    public ProductRmController(ProductRmService productRmService) {
        this.productRmService = productRmService;
    }

}
