package com.learn.mybatis.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.learn.mybatis.pojo.Author;
import com.learn.mybatis.pojo.User;
/*
 * 这是来测试事务的测试代码
 * mybatis的事务处理使用sqlsessio对象来控制的
 */
public class TestTran {

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
		session=sqlMapper.openSession(false);//令其为false，当然其默认值也是false
		try {
			User user=new User();
			user.setUsername("auther001");
			user.setPassword("123456");
			session.insert("insertUser",user);
			System.out.println("新加入的id是："+user.getId());
			//这里的操作是先使得添加一个user对象，然后对author对象进行添加，但是在我们添加user成功的情况下，若是添加author并未成功
			//那这时就需要用到事务中的回滚操作了，避免了数据库操作出现的一系列问题而数据乱的情况
			
			Author at=new Author();
			at.setUser(user);
			at.setRealname("a grate man");
			session.insert("insertAuthor",at);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();//回滚操作
		}finally {
			session.close();
		}
	}

}
