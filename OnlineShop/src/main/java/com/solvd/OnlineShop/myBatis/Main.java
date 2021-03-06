package com.solvd.OnlineShop.myBatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.solvd.OnlineShop.dao.IUserDAO;
import com.solvd.OnlineShop.models.Order;
import com.solvd.OnlineShop.models.User;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		try {
			myBatisExample();
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

	private static void myBatisExample() throws IOException {	
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		try(SqlSession session = ((SqlSessionFactory) sqlSessionFactory).openSession()) {
			User user = session.selectOne("getUserById", 1);
			LOGGER.info(user.toString());
			LOGGER.info(user.getProducts().size());
			LOGGER.info(user.getProducts().get(0).toString());
			
			Order order = session.selectOne("getOrderById", 2);
			LOGGER.info(order.getTotal());
			LOGGER.info(order.getShippingAddress().getCity());
		}
	}
	
	
	
	
	
}
