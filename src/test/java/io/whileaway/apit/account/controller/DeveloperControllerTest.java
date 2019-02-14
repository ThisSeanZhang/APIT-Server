package io.whileaway.apit.account.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.repository.DeveloperRepository;
import io.whileaway.apit.account.request.CreateDeveloper;
import io.whileaway.apit.account.request.CreateSession;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.enums.ErrorResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeveloperControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    DeveloperRepository developerRepository;
    private MockMvc mvc;
    private Developer developer;
    @Before
    public void setupMockMvc(){
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        developer = new Developer("Scott", "123456", "123456789@123.com");
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @Test
    public void testTheNameNotExist() throws Exception {
        Optional<Developer> op = developerRepository.findByDeveloperName(developer.getDeveloperName());
        op.ifPresent(developer1 -> developerRepository.delete(developer1));
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/developers/developer-name/" + developer.getDeveloperName()))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(404, status);
    }

    @Test
    public void testCreateDeveloper() throws Exception {
        Optional<Developer> op = developerRepository.findByDeveloperName(developer.getDeveloperName());
        op.ifPresent(developer1 -> developerRepository.delete(developer1));
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/developers")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(developer)) )
                .andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testTheNameIsExist() throws Exception {
        Optional<Developer> op = developerRepository.findByDeveloperName(developer.getDeveloperName());
        op.orElseGet( () -> developerRepository.save(developer));
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/developers/developer-name/" + developer.getDeveloperName()))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDevelopLogin() throws Exception {
        Optional<Developer> op = developerRepository.findByDeveloperName(developer.getDeveloperName());
        op.orElseGet( () -> developerRepository.save(developer));
        CreateSession cs = new CreateSession();
        cs.setDeveloperNameOrEmail(developer.getDeveloperName());
        cs.setDeveloperPass(developer.getDeveloperPass());
        cs.setRememberMe(false);
        mvc.perform(
                MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(cs)) //传json参数
        )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}
