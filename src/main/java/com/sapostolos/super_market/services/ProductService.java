package com.sapostolos.super_market.services;

import com.sapostolos.super_market.dtos.GetProductDto;
import com.sapostolos.super_market.dtos.QuantityDto;
import com.sapostolos.super_market.dtos.RegisterProductDto;
import com.sapostolos.super_market.repositories.ProductRepository;
import com.sapostolos.super_market.utilities.Utilities;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<GetProductDto> getProducts(){
        return productRepository.findAll()
                .stream()
                .map(Utilities::productToGetProductDtoConverter)
                .collect(Collectors.toList());
    }

    public GetProductDto getProduct(UUID productId){
        var product = productRepository.findById(productId);
        if (product.isPresent()){
            return Utilities.productToGetProductDtoConverter(product.get());
        }
        else{
            throw new EntityNotFoundException("Product with this productId does not exist!");
        }
    }

    public void createProduct(RegisterProductDto newProduct){
        var product = Utilities.registerProductDtoToProductConverter(newProduct);
        try{
            productRepository.save(product);
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicateKeyException("Cannot have more than 1 product with this name");
        }

    }

    public void deleteProduct(UUID productId){
        productRepository.deleteById(productId);
    }

    public void updateProduct(UUID productId, RegisterProductDto productDto){
        var product = productRepository.findById(productId);
        if (product.isPresent()){
            product.get().setName(productDto.name());
            product.get().setPrice(productDto.price());
            product.get().setAisle(productDto.aisle());
            productRepository.save(product.get());
        }
        else{
            throw new EntityNotFoundException("Product with this productId does not exist!");
        }
    }

    public String purchaseProduct(UUID productId){
        GetProductDto product = getProduct(productId);
        return product.quantity() > 0 ? "Purchase completed!" : "Product out of stock!";
    }

    public void restockProduct(UUID productId, QuantityDto quantityDto){
        var product = productRepository.findById(productId);
        if (product.isPresent()){
            product.get().setQuantity(quantityDto.quantity());
            productRepository.save(product.get());
        }
        else{
            throw new EntityNotFoundException("Product with this productId does not exist!");
        }
    }

}
