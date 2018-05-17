package com.itcast.caiwen.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

@Service("queueConsumer2")
public class QueueConsumer2 implements MessageListener {
	@Override
	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println(
					"[QueueConsumer2]来自消费者工程【activemq_consumes_test】获取的queue消息，接收的内容是：【" + textMsg.getText() + "】");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
