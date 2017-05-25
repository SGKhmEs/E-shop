package com.social.eshop.cucumber.stepdefs;

import com.social.eshop.EshopApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = EshopApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
