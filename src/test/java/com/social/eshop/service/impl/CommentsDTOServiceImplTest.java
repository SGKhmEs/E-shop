package com.social.eshop.service.impl;

import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Customer;
import com.social.eshop.repository.CommentsRepository;
import com.social.eshop.repository.CustomerRepository;
import com.social.eshop.service.dto.CommentsDTO;
import com.social.eshop.service.dto.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by user on 24.06.2017.
 */
//@RunWith(MockitoJUnitRunner.class)
public class CommentsDTOServiceImplTest {


    @Autowired
    private CommentsRepository commentsRepository;
//    @Autowired
//    private CustomerRepository customerRepository;

    private CommentsDTOServiceImpl sut = new CommentsDTOServiceImpl(commentsRepository);


    public CommentsDTOServiceImplTest() {
    }


    @Test
    public void findOne() throws Exception {

        CommentsDTO comments = sut.findOne(1001L);
        //assertThat(commentsDTO.getCustomerDTO().getPersonalInformation().getFirstName()).isEqualTo("Vasy");

        if (comments == null) System.out.println("ISKYSTVINII INTELEKT RULIT");//System.out.println(commentsDTO.toString());
    }
//    @Test
//    public void findAll() throws Exception {
//        List<Comments> comments = sut.findAll();
//        if (comments.isEmpty()) System.out.println("ISKYSTVINII INTELEKT RULIT");
//    }

}
