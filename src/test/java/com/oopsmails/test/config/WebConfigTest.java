package com.oopsmails.test.config;

import com.oopsmails.config.WebConfig;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ImportResource("classpath:applicationContext-rest-test.xml")
@Import({WebConfig.class })
@ComponentScan("com.oopsmails")
public class WebConfigTest extends WebMvcConfigurerAdapter {

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }

}
