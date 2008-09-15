package org.senro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMetadataTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/localApplicationContext.xml");
		Object sessionFactory = ctx.getBean("sessionFactory");
	}
}
