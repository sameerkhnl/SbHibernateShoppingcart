package org.khanal.SbHibernateShoppingcart.controllers;

import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String getPage() {
        return "hello";
    }

//    @RequestMapping("/products")
//    public List<Product> getProducts() {
//
//    }

}
