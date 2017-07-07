package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.SessionIdDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SessionId and its DTO SessionIdDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, })
public interface SessionIdMapper extends EntityMapper <SessionIdDTO, SessionId> {
    @Mapping(source = "customer.id", target = "customerId")
    SessionIdDTO toDto(SessionId sessionId); 
    @Mapping(source = "customerId", target = "customer")
    SessionId toEntity(SessionIdDTO sessionIdDTO); 
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default SessionId fromId(Long id) {
        if (id == null) {
            return null;
        }
        SessionId sessionId = new SessionId();
        sessionId.setId(id);
        return sessionId;
    }
}
