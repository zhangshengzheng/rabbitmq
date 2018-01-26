package com.zsz.rabbit.helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/8/9.
 */
public class Consumer {

    //private final static Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final static String QUEUE_NAME = "hello";

    @Test
   public void getHelloWorld() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置rabbitmq地址
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //创建一个队列，不存在就创建，存在，没有任何影响
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        com.rabbitmq.client.Consumer consumer = new com.rabbitmq.client.DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                //接收消息
                String message = new String(body,"UTF-8");
                System.out.println("message = "+message);
            }
        };
        //自动回复队列应答   ---   rabbitmq中的消息确认几只
        channel.basicConsume(QUEUE_NAME,true,consumer);

   }
}
