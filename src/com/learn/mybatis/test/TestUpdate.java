package com.learn.mybatis.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.learn.mybatis.map.UserInterface;
import com.learn.mybatis.pojo.User;

public class TestUpdate {

	public static void main(String[] args) {
		String resource="com/learn/mybatis/map/mybatis-config.xml";
		Reader reader=null;
		SqlSession session;
		try {
			reader=Resources.getResourceAsReader(resource);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlMapper=  new SqlSessionFactoryBuilder().build(reader);
		session=sqlMapper.openSession();
		try {
			//这是用于测试插入语句
			/*User myuser=new User();
			myuser.setUsername("cat");
			myuser.setPassword("12345");
			session.insert("insertUser",myuser);*/
			//这是用于测试更新语句
			/*User myuser=new User();
			myuser.setUsername("catty");
			myuser.setPassword("666");
			myuser.setId(2);
			session.update("updateUser",myuser);*/
			//这是利用注解的方式进行的delete语句，两种方式都可以，都记一下吧，但大都不使用注解接口的方式
			//在复杂的映射中有局限性，推荐使用map配置文件这种方式
			UserInterface ui=session.getMapper(UserInterface.class);//接口的实现
			ui.deleteUser(1);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
