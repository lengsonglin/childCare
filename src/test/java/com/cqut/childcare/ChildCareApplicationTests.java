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
    void testSyncMessage() throws InterruptedException {
        mqProducer.sendSyncMessage("TEST_TOPIC","菀菀1111");
        Thread.sleep(5000);
    }

}
