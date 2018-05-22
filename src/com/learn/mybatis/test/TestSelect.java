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
			//����ʹ����������Ϊhashmap����ʽ
			/*HashMap<String,String> hm=new HashMap();
			hm.put("username", "catty");
			hm.put("password", "666");
			User myuser=session.selectOne("loginUser",hm);*/
			//����ʹ�ö���ķ�ʽ�������������͵���ʽ
			/*User user=new User();
			user.setUsername("catty");
			user.setPassword("666");
			User myuser=session.selectOne("loginUser2",user);
			if(myuser!=null) {
				System.out.println("��½�ɹ�������");
			}*/
			//�������ڻ�ý�����������ݿ���ѡ��������������ʹ��
			//List<User> lu=session.selectList("selectUserlist");
			List<User> lu=session.selectList("selectUsers");
			for(User temp:lu) {
				System.out.println("�û���Ϊ��"+temp.getUsername()+" ����Ϊ��"+temp.getPassword());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}