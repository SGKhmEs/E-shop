package com.social.eshop.service;

<<<<<<< HEAD
import com.social.eshop.domain.Comments;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.service.dto.CommentsDTO;
import com.social.eshop.service.dto.CustomerDTO;
import com.social.eshop.service.dto.ProductsDTO;
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
public class CommentsDTOService  {

    private final Logger log = LoggerFactory.getLogger(CommentsDTOService.class);
    private final CommentsRepository commentsRepository;
    private final CustomerDTOService customerDTOService;
    private final ProductsDTO productsDTO;

    public CommentsDTOService(CommentsRepository commentsRepository, CustomerDTOService customerDTOService, ProductsDTO productsDTO) {
        this.commentsRepository = commentsRepository;
        this.customerDTOService = customerDTOService;
        this.productsDTO = productsDTO;
    }

    public CommentsDTO findOne(Long id){
        log.debug("Request to get Comments : {}", id);
        Comments comment = commentsRepository.getOne(id);
        CustomerDTO customerDTO = customerDTOService.findOne(commentsRepository.getOne(id).getCustomer().getId());
        CommentsDTO commentDTO = new CommentsDTO();
        try {
            commentDTO.mappingToDTO(comment, customerDTO);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(CommentsDTOService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentDTO;
    }

    public List<CommentsDTO> findAllByProducts(Long id) {

        List<Comments> commentList = commentsRepository.findByProductsId(id);
        List<CommentsDTO> commentDTOList = new ArrayList<>();

        for (Comments comment : commentList) {
            CommentsDTO commentDTO = new CommentsDTO();
            CustomerDTO customerDTO = customerDTOService.findOne(commentsRepository.getOne(comment.getId()).getCustomer().getId());
            try {
                commentDTO.mappingToDTO(comment, customerDTO);
            } catch (InvocationTargetException ex) {
                java.util.logging.Logger.getLogger(CommentsDTOService.class.getName()).log(Level.SEVERE, null, ex);
            }
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
=======
public interface CommentsDTOService {

>>>>>>> creatingServices
}
