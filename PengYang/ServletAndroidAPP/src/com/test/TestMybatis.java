package com.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.modle.Admin;
import com.pengyang.service.AdminService;
import com.service.impl.AdminServiceImpl;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class TestMybatis {

	
		@Resource
	
		public static void main(String []arg){
			AdminServiceImpl adminServiceImpl;
			ApplicationContext context= new FileSystemXmlApplicationContext("classpath:spring-mybatis.xml");
			adminServiceImpl = (AdminServiceImpl) context.getBean("adminService");
//			Admin admin = adminServiceImpl.findAdminById(2);
//			System.out.println(admin);
			List<Admin> adminList = adminServiceImpl.findAllAdmin();
			System.out.println(adminList);
	}
}
