package com.zsz.rabbit.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/8/9.
 */
public class Produce {
    private final static String QUEUE_NAME = "hello";

   // private final static Logger logger = LoggerFactory.getLogger(Produce.class);

    @Test
    public void setHelloWorld() throws IOException, TimeoutException {
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
        //发送的消息
        String message = "hello workd";
        //发送消息到队列中
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));

       // logger.info("message = "+message);
        System.out.println("message = "+message);
        //关闭频道和连接
        channel.close();
        connection.close();
    }
}
