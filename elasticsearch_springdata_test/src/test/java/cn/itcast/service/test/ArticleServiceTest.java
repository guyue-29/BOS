package cn.itcast.service.test;

import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itcast.caiwen.domain.Article;
import com.itcast.caiwen.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ArticleServiceTest {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private Client client;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void createIndex() {
		elasticsearchTemplate.createIndex(Article.class);
		elasticsearchTemplate.putMapping(Article.class);
	}

	@Test
	public void testSave() {
		Article article = new Article();
		article.setId(1001);
		article.setTitle("ElasticSearch（下面简称ES）是一个开源的、基于Apache Lucene的、分布式的实时分析搜索引擎");
		article.setContent(
				"其设计理念就是可以从不用的数据源获取数据，进行实时的检索和分析（As the company behind the open source projects — Elasticsearch, Logstash, Kibana, and Beats — designed to take data from any source and search, analyze, and visualize it in real time, Elastic is helping people make sense of data. ）。");

		articleService.save(article);
	}

	@Test
	public void testDelete() {
		Article article = new Article();
		article.setId(1001);

		articleService.delete(article);
	}

	@Test
	public void testFindOne() {
		System.out.println(articleService.findOne(1001));
	}

	@Test
	public void testSaveBatch() {
		for (int i = 1; i <= 100; i++) {
			Article article = new Article();
			article.setId(i);
			article.setTitle("ElasticSearch（下面简称ES）是一个开源的、基于Apache Lucene的、分布式的实时分析搜索引擎");
			article.setContent(
					"其设计理念就是可以从不用的数据源获取数据，进行实时的检索和分析（As the company behind the open source projects — Elasticsearch, Logstash, Kibana, and Beats — designed to take data from any source and search, analyze, and visualize it in real time, Elastic is helping people make sense of data. ）。");

			articleService.save(article);
		}
	}

	@Test
	public void testSortAndPaging() {
		Iterable<Article> articles = articleService.findAll();
		for (Article article : articles) {
			System.out.println(article);
		}

		Pageable pageable = new PageRequest(0, 10);
		Page<Article> pageData = articleService.findAll(pageable);
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}

	@Test
	public void testConditionQuery() {
		// List<Article> articles = articleService.findByTitle("ElasticSearch");
		// for (Article article : articles) {
		// System.out.println(article);
		// }

		Pageable pageable = new PageRequest(0, 10);
		Page<Article> pageData = articleService.findByTitle("简称ES", pageable);
		System.out.println("查询的总数量是：" + pageData.getTotalElements());
		for (Article article : pageData.getContent()) {
			System.out.println(article);
		}
	}

}
