package com.learn.mybatis.map;

import org.apache.ibatis.annotations.Delete;

public interface UserInterface {
	//利用注解的方式来实现数据库删除操作，耶可以用xml配置文件，知识多一种方式
	@Delete("delete from users where id=#{id}")
	public void deleteUser(Integer id);
}
