/**
 * @author Administrator
 * @created 2015 2015年1月12日 下午7:46:46
 * @version 1.0
 */
package org.epiclouds.main;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.epiclouds.dao.bean.User;
import org.epiclouds.dao.bean.UserHome;
import org.epiclouds.dao.imp.UserDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;
import org.w3c.dom.views.AbstractView;

/**
 * @author Administrator
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class HibernateUserDaoImpTests extends AbstractContextControllerTests{

 

	


	/**
	 * simple context
	 * @throws Exception
	 */
	@Test
	public void testAddUser() throws Exception{
		//ud=new ClassPathXmlApplicationContext("spring.xml").getBean("userdao", UserDaoImp.class);
		UserHome uh=new UserHome();
		int count=20000;
		long t1=System.currentTimeMillis();
		for(int i=0;i<count;i++){
			User ub=new User("user"+i, "123");
			uh.attachClean(ub);
		}
		long t2=System.currentTimeMillis();
		System.err.println((t2-t1)+"毫秒");
		
		System.err.println(count*1000/(t2-t1)+"行每秒");
	}

}
