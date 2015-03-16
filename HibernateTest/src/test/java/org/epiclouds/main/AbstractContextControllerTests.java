package org.epiclouds.main;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;



@ContextConfiguration(locations = {"file:src/main/resources/spring.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractContextControllerTests {

	@Autowired
	protected ApplicationContext wac;

}
