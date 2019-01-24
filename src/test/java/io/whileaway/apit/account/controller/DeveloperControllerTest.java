package io.whileaway.apit.account.controller;

import io.whileaway.apit.base.enums.ErrorResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeveloperControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @Test
    public void testDevelopAbsentParameter() throws Exception {
        String json = "{\"developerName\":\"sean\"," +
                "\"developerPass\":\"123456\"," +
                "\"joinTime\":\"2018-12-12\"," +
                "\"email\":\"cccc\"}";
        mvc.perform(
                MockMvcRequestBuilders.post("/developers")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testEnumStream() {
        ErrorResponse.ERROR.getEnumStream().forEach(en -> System.out.println(en.getCode()));
    }
}
