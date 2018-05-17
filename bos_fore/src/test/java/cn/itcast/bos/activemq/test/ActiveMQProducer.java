package cn.itcast.bos.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQProducer {
	@Test
	public void testProduceMQ() throws Exception {
		// 连接工厂
		// 使用默认用户名、密码、路径
		// 路径 tcp://host:61616
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// 获取一个连接
		Connection connection = connectionFactory.createConnection();
		// 建立会话
		Session session = connection.createSession(true,
				Session.AUTO_ACKNOWLEDGE);
		// 创建队列或者话题对象
		Queue queue = session.createQueue("HelloWorld");
		// 创建生产者 或者 消费者
		MessageProducer producer = session.createProducer(queue);

		// 发送消息
		for (int i = 0; i < 10; i++) {
			producer.send(session.createTextMessage("你好，activeMQ:" + i));
		}
		// 提交操作
		session.commit();

	}
}
