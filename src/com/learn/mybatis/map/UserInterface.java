package com.learn.mybatis.map;

import org.apache.ibatis.annotations.Delete;

public interface UserInterface {
	//����ע��ķ�ʽ��ʵ�����ݿ�ɾ��������Ү������xml�����ļ���֪ʶ��һ�ַ�ʽ
	@Delete("delete from users where id=#{id}")
	public void deleteUser(Integer id);
}
