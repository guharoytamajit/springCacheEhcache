package com;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component(value = "cal")
public class Calculator {
	@Cacheable(value = "namespace1", key = "new Integer(#a).toString()+\"-\"+new Integer(#b).toString()")
	public int add(int a, int b) {
		return a + b;
	}

	@CacheEvict(value = "namespace1", allEntries = true)
	public void clearCache() {

	}

}
