package com.itcast.caiwen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itcast.caiwen.queue.QueueSender;
import com.itcast.caiwen.topic.TopicSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MQSendTest {
	public static final String MQ_QUEUE_NAME = "mq_queue_test";
	public static final String MQ_TOPIC_NAME = "mq_topic_test";
	@Autowired
	private QueueSender queueSender;
	
	@Autowired
	private TopicSender topicSender;
	
	@Test
	public void testSendQueue() {
		queueSender.sendMq(MQ_QUEUE_NAME, "这是【activemq_produces_test】工程中，发送的queue类型消息");
	}

	@Test
	public void testSendTopic() {

		topicSender.sendMq(MQ_TOPIC_NAME, "这是【activemq_produces_test】工程中，发送的topic类型消息");
	}
}
