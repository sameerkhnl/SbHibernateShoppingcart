package org.khanal.SbHibernateShoppingcart.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.khanal.SbHibernateShoppingcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    private ResourceLoader resourceLoader;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getProducts() {
//        List<Product> products = productService.listAll();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    @RequestMapping(value = "/auth/products", method = RequestMethod.GET)
    public ResponseEntity<?> getProductsByPage(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer size) {
        if(pageNum != null && size != null){
            pageNum = pageNum <= 1? 1 : pageNum;
            int pageIndex = pageNum - 1;
            Page<Product> page = productService.findAll(pageIndex, size, Sort.Direction.ASC, "code");
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        List<Product> products = productService.listAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(path = "/products/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        Product product = productService.findByProductCode(code);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<byte[]> getImage() throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("classpath:111111.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos);
        baos.flush();
        byte[] media = baos.toByteArray();
        return new ResponseEntity<byte[]>(media, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product ) {
        Product retrived = this.productService.saveOrUpdate(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(retrived.getCode()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/products/{code}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable String code, @RequestBody Product product) {
        Product retrieved = this.productService.saveOrUpdate(product);
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(retrieved.getCode()).toUri();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value="/products/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable String code) {
        try {
            this.productService.findByProductCode(code);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        this.productService.deleteProductByCode(code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


//    @RequestMapping("/products")
//    public List<Product> getProducts() {
//
//    }

}
