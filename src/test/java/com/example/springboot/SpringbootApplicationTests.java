package com.example.springboot;

import com.example.springboot.mapperDao.PlaneMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    PlaneMapper p;

    @Test
    void contextLoads() {
        p.planes();
    }

}
