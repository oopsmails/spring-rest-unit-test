package com.oopsmails.legcybean;

import org.springframework.stereotype.Service;
//import javax.ejb.Stateless;

//@Service(value = "TestService")
//@Stateless(mappedName="TestService") //cannot be used because no web container
public class TestServiceImpl implements TestService {

    @Override
    public LogLevelType determineLogLevelType(int logLevel) {
        return LogLevelType.getEnum(logLevel);
    }

}
