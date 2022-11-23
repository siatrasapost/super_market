package com.sapostolos.super_market.controllers;

import com.sapostolos.super_market.dtos.GetProductDto;
import com.sapostolos.super_market.dtos.QuantityDto;
import com.sapostolos.super_market.dtos.RegisterProductDto;
import com.sapostolos.super_market.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/")
    List<GetProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping(path = "/{productId}")
    GetProductDto getProduct(@PathVariable UUID productId){
        return productService.getProduct(productId);
    }

    @PostMapping(path = "/")
    void createProduct(@RequestBody RegisterProductDto productDto){
        productService.createProduct(productDto);
    }

    @DeleteMapping(path = "/{productId}")
    void deleteProduct(@PathVariable UUID productId){
        productService.deleteProduct(productId);
    }

    @PatchMapping(path = "/{productId}")
    void updateProduct(@PathVariable UUID productId , @RequestBody RegisterProductDto productDto ){
        productService.updateProduct(productId, productDto);
    }

    @PostMapping(path = "/{productId}/purchase")
    String purchaseProduct(@PathVariable UUID productId){
        return productService.purchaseProduct(productId);
    }

    @PostMapping(path = "/{productId}/restock")
    @PreAuthorize("hasAuthority('ADMIN')")
    void restockProduct(@PathVariable UUID productId, @RequestBody QuantityDto quantityDto){
        productService.restockProduct(productId, quantityDto);
    }
}
