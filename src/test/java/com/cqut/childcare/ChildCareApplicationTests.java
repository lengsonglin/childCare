package com.cqut.childcare;

import com.cqut.childcare.common.transaction.service.MQProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChildCareApplicationTests {

    @Autowired
    private MQProducer mqProducer;

    @Test
    void contextLoads() {
    }
    @Test
    void testSyncMessage(){
        mqProducer.sendSyncMessage("TEST_TOPIC","菀菀");
    }

}
