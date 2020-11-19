package com.company.xite.equation_calculator.IntegrationTests.Utils;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class Requests {

    public static MvcResult httpRequest(MockMvc mockMvc, String url, String body, long userId, MediaType bodyMediaType, ResultMatcher expectedStatusCode) throws Exception {
        return mockMvc.perform(post(url, userId)
                .contentType(bodyMediaType)
                .content(body))
                .andDo(print())
                .andExpect(expectedStatusCode)
                .andReturn();
    }
}

