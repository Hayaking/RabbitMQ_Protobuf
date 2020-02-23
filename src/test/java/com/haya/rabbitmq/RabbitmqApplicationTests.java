package com.haya.rabbitmq;

import com.haya.rabbitmq.config.ProtobufMessageConverter;
import com.haya.rabbitmq.message.ProtoMessage;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend( "demo.haya","q1",  ProtoMessage.PersonTest.newBuilder().setName( "qweqweqwewqeqw" ).build());
    }

    @Test
    void rec() {
        Object q1 = rabbitTemplate.receiveAndConvert( "q1" );
        System.out.println( q1.getClass() );
        System.out.println(q1);
    }
}
