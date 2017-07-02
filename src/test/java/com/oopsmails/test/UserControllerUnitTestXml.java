package com.oopsmails.test;

import com.oopsmails.controller.UserController;
import com.oopsmails.filter.CORSFilter;
import com.oopsmails.legcybean.LogLevelType;
import com.oopsmails.legcybean.TestService;
import com.oopsmails.model.User;
import com.oopsmails.service.UserService;
import com.oopsmails.test.config.WebConfigTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liu on 2017-07-01.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-rest-test.xml"})
public class UserControllerUnitTestXml {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private TestService testService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CORSFilter())
                .build();
    }

    // =========================================== Get All Users ==========================================

    @Test
    public void test_get_all_success() throws Exception {
        LogLevelType logLevel = testService.determineLogLevelType(1);
        System.out.println("================= logLevel = " + logLevel);
    }


}
