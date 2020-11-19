package com.company.xite.equation_calculator.IntegrationTests.Utils;

import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class Helpers {

    public  static Long generateTestTicket() {
        return ThreadLocalRandom.current().nextLong(0, 1000 + 1);
    }

    public static String buildRequestBody(String equation){
        return "{\"equation\":\""+equation+"\"\n}";
    }

    public static Properties loadApplicationProperties() {
        Properties prop = new Properties();
        try {
            prop.load(ApplicationContext.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException ex) {
            System.out.println("Cannot Load Application Properties");
        }
        return prop;
    }

    public static void validatePathWithValue(MvcResult mvcResult, String jsonPath, Object expectedJsonValue) throws Exception{
        Object actualValue = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read(jsonPath);
        Assert.assertEquals( expectedJsonValue, actualValue);
    }

    public static Object getValueFromPath(MvcResult mvcResult, String jsonPath) throws Exception{
        return JsonPath.parse(mvcResult.getResponse().getContentAsString()).read(jsonPath);
    }
}
