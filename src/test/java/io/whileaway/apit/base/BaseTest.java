package io.whileaway.apit.base;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;


    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @Test
    public void testThrowError() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/test/error")
                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andDo(MockMvcResultHandlers.print());
    }
}
