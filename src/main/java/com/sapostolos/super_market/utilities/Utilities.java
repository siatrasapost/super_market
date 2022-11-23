package com.sapostolos.super_market.utilities;

import com.sapostolos.super_market.dtos.GetProductDto;
import com.sapostolos.super_market.dtos.RegisterProductDto;
import com.sapostolos.super_market.entities.Product;

public class Utilities {
    public static GetProductDto productToGetProductDtoConverter(Product product){
        return new GetProductDto(product.getId(),
                            product.getName(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getAisle());
    }

    public static Product registerProductDtoToProductConverter(RegisterProductDto registerProductDto){
        var product = new Product();
        product.setName(registerProductDto.name());
        product.setPrice(registerProductDto.price());
        product.setAisle(registerProductDto.aisle());
        product.setQuantity(0);
        return product;
    }
}
