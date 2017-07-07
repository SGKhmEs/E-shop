package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.CustomerRoomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomerRoom and its DTO CustomerRoomDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonalInformationMapper.class, WishListMapper.class, AddressMapper.class, BucketMapper.class, SeenMapper.class, HistoryOrderMapper.class, })
public interface CustomerRoomMapper extends EntityMapper <CustomerRoomDTO, CustomerRoom> {
    @Mapping(source = "personalInfo.id", target = "personalInfoId")
    @Mapping(source = "wishList.id", target = "wishListId")
    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "bucket.id", target = "bucketId")
    @Mapping(source = "seen.id", target = "seenId")
    @Mapping(source = "historyOrder.id", target = "historyOrderId")
    CustomerRoomDTO toDto(CustomerRoom customerRoom); 
    @Mapping(source = "personalInfoId", target = "personalInfo")
    @Mapping(source = "wishListId", target = "wishList")
    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "bucketId", target = "bucket")
    @Mapping(source = "seenId", target = "seen")
    @Mapping(source = "historyOrderId", target = "historyOrder")
    @Mapping(target = "customers", ignore = true)
    CustomerRoom toEntity(CustomerRoomDTO customerRoomDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default CustomerRoom fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerRoom customerRoom = new CustomerRoom();
        customerRoom.setId(id);
        return customerRoom;
    }
}
