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
 * ��������������Ĳ��Դ���
 * mybatis��������ʹ��sqlsessio���������Ƶ�
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
		session=sqlMapper.openSession(false);//����Ϊfalse����Ȼ��Ĭ��ֵҲ��false
		try {
			User user=new User();
			user.setUsername("auther001");
			user.setPassword("123456");
			session.insert("insertUser",user);
			System.out.println("�¼����id�ǣ�"+user.getId());
			//����Ĳ�������ʹ�����һ��user����Ȼ���author���������ӣ��������������user�ɹ�������£��������author��δ�ɹ�
			//����ʱ����Ҫ�õ������еĻع������ˣ����������ݿ�������ֵ�һϵ������������ҵ����
			
			Author at=new Author();
			at.setUser(user);
			at.setRealname("a grate man");
			session.insert("insertAuthor",at);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();//�ع�����
		}finally {
			session.close();
		}
	}

}
