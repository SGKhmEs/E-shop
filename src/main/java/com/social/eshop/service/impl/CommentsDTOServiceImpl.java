package com.social.eshop.service.impl;

import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Customer;
import com.social.eshop.domain.PersonalInformation;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.service.CommentsDTOService;
import com.social.eshop.service.dto.CommentsDTO;
import com.social.eshop.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;

@Service
@Transactional
public class CommentsDTOServiceImpl implements CommentsDTOService {

    private final Logger log = LoggerFactory.getLogger(CommentsDTOServiceImpl.class);

    @Autowired
    private CommentsRepository commentsRepository;
//    @Autowired
//    private CustomerRepository customerRepository;


//    public CommentsDTOServiceImpl(CommentsRepository commentsRepository) {
//        this.commentsRepository = commentsRepository;
//    }



    @Transactional(readOnly = true)
    public CommentsDTO findOne(Long id) {
        log.debug("Request to get Comments : {}", id);
        Comments comments = commentsRepository.findOne(id);


        CommentsDTO commentDTO = new CommentsDTO();
        //CustomerDTO customerDTO = new CustomerDTO();
//
//        PersonalInformation info = new PersonalInformation();
//        info.setFirstName("Vasy");
//        customerDTO.setPersonalInformation(info);
////
//        commentDTO.setId(1001L);
//        commentDTO.setCustomerDTO(customerDTO);

        try {
            commentDTO.mappingToDTO(comments);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(CommentsDTOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commentDTO;
    }

    //@Transactional(readOnly = true)
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }
}
