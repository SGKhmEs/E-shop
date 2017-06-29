package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.WishListDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WishList and its DTO WishListDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, ProductsMapper.class, })
public interface WishListMapper extends EntityMapper <WishListDTO, WishList> {

    @Mapping(source = "customer.id", target = "customerId")

    @Mapping(source = "product.id", target = "productId")
    WishListDTO toDto(WishList wishList); 

    @Mapping(source = "customerId", target = "customer")

    @Mapping(source = "productId", target = "product")
    WishList toEntity(WishListDTO wishListDTO); 
    default WishList fromId(Long id) {
        if (id == null) {
            return null;
        }
        WishList wishList = new WishList();
        wishList.setId(id);
        return wishList;
    }
}
