package com.itcast.caiwen.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

@Service("topicConsumer2")
public class TopicConsumer2 implements MessageListener {
	@Override
	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println(
					"[TopicConsumer2]来自消费者工程【activemq_consumes_test】获取的topic消息，接收的内容是：【" + textMsg.getText() + "】");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
