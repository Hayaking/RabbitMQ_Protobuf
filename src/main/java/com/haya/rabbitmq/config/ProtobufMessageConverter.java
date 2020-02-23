package com.haya.rabbitmq.config;

import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import static com.haya.rabbitmq.message.ProtoMessage.PersonTest;

/**
 * @author haya
 */
public class ProtobufMessageConverter extends AbstractMessageConverter {
    /**
     * object转换为ProtoBuf, 发送消息
     */
    @Override
    public Message createMessage(Object object, MessageProperties messageProperties) {
        System.out.println("发送转换的消息");
        PersonTest person = (PersonTest)object;
        return new Message(person.toByteArray(), messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        System.out.println("转换消息");
        PersonTest from = null;
        try {
            from = PersonTest.parseFrom( message.getBody() );
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return from;
    }

}
