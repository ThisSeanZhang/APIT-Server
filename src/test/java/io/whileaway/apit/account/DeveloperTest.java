package io.whileaway.apit.account;

import io.whileaway.apit.ApitServerApplication;
import io.whileaway.apit.account.entity.Developer;
import io.whileaway.apit.account.repository.DeveloperRepository;
import io.whileaway.apit.account.service.DeveloperService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeveloperTest {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private DeveloperService developerService;

    private Developer willBeSaveDeveloper;

    @Before
    public void initData() {
        // "Sean","123456"
        willBeSaveDeveloper = new Developer();
    }

    @Test
    public void testCreateDeveloper() {
        developerService.createDeveloper(willBeSaveDeveloper);
    }
}
