package com.nanye.sharenote;

import com.nanye.sharenote.mapper.StarMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MiniprogramSharenoteApplicationTests {

    @Autowired
    StarMapper starMapper;

    @Test
    void contextLoads() {
        System.out.println(starMapper.selectIsStar("ouqrb4iMTR14nXCT_TcycZKSQa4A",23));
    }

}
