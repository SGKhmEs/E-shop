package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.WishListDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WishList and its DTO WishListDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WishListMapper extends EntityMapper <WishListDTO, WishList> {
    
    @Mapping(target = "products", ignore = true)
    WishList toEntity(WishListDTO wishListDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default WishList fromId(Long id) {
        if (id == null) {
            return null;
        }
        WishList wishList = new WishList();
        wishList.setId(id);
        return wishList;
    }
}
