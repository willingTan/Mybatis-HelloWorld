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
			//�������ڲ��Բ������
			/*User myuser=new User();
			myuser.setUsername("cat");
			myuser.setPassword("12345");
			session.insert("insertUser",myuser);*/
			//�������ڲ��Ը������
			/*User myuser=new User();
			myuser.setUsername("catty");
			myuser.setPassword("666");
			myuser.setId(2);
			session.update("updateUser",myuser);*/
			//��������ע��ķ�ʽ���е�delete��䣬���ַ�ʽ�����ԣ�����һ�°ɣ����󶼲�ʹ��ע��ӿڵķ�ʽ
			//�ڸ��ӵ�ӳ�����о����ԣ��Ƽ�ʹ��map�����ļ����ַ�ʽ
			UserInterface ui=session.getMapper(UserInterface.class);//�ӿڵ�ʵ��
			ui.deleteUser(1);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
