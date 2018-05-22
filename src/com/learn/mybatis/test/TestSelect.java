package com.learn.mybatis.test;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.learn.mybatis.map.UserInterface;
import com.learn.mybatis.pojo.User;

public class TestSelect {
	public static void main(String args[]) {
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
			//这是使用数据类型为hashmap的形式
			/*HashMap<String,String> hm=new HashMap();
			hm.put("username", "catty");
			hm.put("password", "666");
			User myuser=session.selectOne("loginUser",hm);*/
			//这是使用对象的方式来定义数据类型的形式
			/*User user=new User();
			user.setUsername("catty");
			user.setPassword("666");
			User myuser=session.selectOne("loginUser2",user);
			if(myuser!=null) {
				System.out.println("登陆成功！！！");
			}*/
			//这是用于获得结果集，在数据库中选择多个对象的情况下使用
			//List<User> lu=session.selectList("selectUserlist");
			List<User> lu=session.selectList("selectUsers");
			for(User temp:lu) {
				System.out.println("用户名为："+temp.getUsername()+" 密码为："+temp.getPassword());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}