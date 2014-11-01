package com.test;

import java.util.List;

import net.sf.ehcache.Statistics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.Calculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context.xml")
public class SpringAppTests {
	@Autowired
	Calculator calculator;
	@Autowired
	EhCacheCacheManager cacheManager;

	@Test
	public void testSayHello() {

		System.out.println(calculator.add(2, 3));
		System.out.println(calculator.add(2, 3));
		System.out.println(calculator.add(3, 2));
		calculator.clearCache();
		System.out.println(calculator.add(2, 3));
		System.out.println(calculator.add(2, 3));
		System.out.println(calculator.add(3, 2));

		// Collection<String> cacheNames = cacheManager.getCacheNames();
		// for(String name:cacheNames){
		// System.out.println(name);
		// }
		Cache cache = cacheManager.getCache("namespace1");
		net.sf.ehcache.Cache c = (net.sf.ehcache.Cache) cache.getNativeCache();
		Statistics statistics = c.getStatistics();

		List keys = c.getKeys();
		for (Object object : keys) {
			System.out.println(c.get(object));
		}
		System.out.println("hits:" + statistics.getCacheHits());
		System.out.println("misses:" + statistics.getCacheMisses());

	}
}