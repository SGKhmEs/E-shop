//package com.social.eshop.service.impl;
//
//import com.social.eshop.domain.Comments;
//import com.social.eshop.domain.Customer;
//import com.social.eshop.repository.CommentsRepository;
//import com.social.eshop.repository.CustomerRepository;
//import com.social.eshop.service.CommentsDTOService;
//import com.social.eshop.service.dto.CommentsDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.logging.Level;
//
//@Service
//@Transactional
//public class CommentsDTOServiceImpl implements CommentsDTOService {
//
//    private final Logger log = LoggerFactory.getLogger(CommentsDTOServiceImpl.class);
//
//    private CommentsRepository commentsRepository;
//    private CustomerRepository customerRepository;
//
//    public CommentsDTOServiceImpl() {}
//
//    public CommentsDTOServiceImpl(CommentsRepository commentsRepository, CustomerRepository customerRepository) {
//        this.commentsRepository = commentsRepository;
//        this.customerRepository = customerRepository;
//    }
//
//    public CommentsDTO findOne(Long id) {
//        log.debug("Request to get Comments : {}", id);
//        Comments comments = commentsRepository.getOne(id);
//
//        CommentsDTO commentDTO = new CommentsDTO();
//
//        try {
//            commentDTO.mappingToDTO(comments);
//        } catch (InvocationTargetException ex) {
//            java.util.logging.Logger.getLogger(CommentsDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return commentDTO;
//    }
//
//}
