package com.social.eshop.service.impl;

import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Customer;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.service.CommentsDTOService;
import com.social.eshop.service.dto.CommentsDTO;
import com.social.eshop.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Service
@Transactional
public class CommentsDTOServiceImpl implements CommentsDTOService {

    private final Logger log = LoggerFactory.getLogger(CommentsDTOServiceImpl.class);

    private CustomerDTOServiceImpl customerDTOService;
    private CommentsRepository commentsRepository;

    public CommentsDTOServiceImpl() {   }

    public CommentsDTOServiceImpl(CustomerDTOServiceImpl customerDTOService, CommentsRepository commentsRepository) {
        this.customerDTOService = customerDTOService;
        this.commentsRepository = commentsRepository;
    }

    public CommentsDTO findOne(Long id) {
        log.debug("Request to get Comments : {}", id);
        Comments comments = commentsRepository.getOne(id);
        CustomerDTO customerDTO = customerDTOService.findOne(commentsRepository.getOne(id).getCustomer().getCustomerRoom().getPersonalInfo().getId());
        CommentsDTO commentDTO = new CommentsDTO();
        try {
            commentDTO.mappingToDTO(commentDTO, customerDTO);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(CommentsDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentDTO;
    }

    public List<CommentsDTO> findAllByProduct(Long id) {

        List<Comments> commentList = commentsRepository.findByProductsId(id);
        List<CommentsDTO> commentDTOList = new ArrayList<>();

        for (Comments comment : commentList) {
            CommentsDTO commentDTO = new CommentsDTO();
            CustomerDTO customerDTO = customerDTOService.findOne(commentsRepository.getOne(comment.getId())
                                                .getCustomer().getCustomerRoom().getPersonalInfo().getId());
            try {
                commentDTO.mappingToDTO(comment, customerDTO);
            } catch (InvocationTargetException ex) {
                java.util.logging.Logger.getLogger(CommentsDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }

}
