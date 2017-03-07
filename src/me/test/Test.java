package me.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import me.domain.User;

public class Test {
	
	public static void main(String[] args){
		//1.mybatis的CURD
//		testAdd();
		testUpdate();
//		testDelete();
//		testGetAll();
//		testGetUser();
	}
	
	public static void testAdd(){
//		SqlSession sqlSession = MybatisUtil.getSqlSession(false);
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * addUser是insert标签的id属性值，通过insert标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "me.mapping.userMapper.addUser";
		User user = new User("用户孤傲苍狼",20);
		//执行插入操作
		int result = sqlSession.insert(statement, user);
		//手动提交事务
//		sqlSession.commit();
		//使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		System.out.println("插入影响条数："+result);
	}
	
	public static void testUpdate(){
		//updateUser是update标签的id属性值，通过update标签的id属性值就可以找到要执行的SQL
		String statement = "me.mapping.userMapper.updateUser";
		User user = new User("白虎神皇23",23);
		user.setId(2);
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		int result = sqlSession.update(statement,user);
		sqlSession.close();
		System.out.println("update影响条数："+result);
	}
	
	public static void testDelete(){
		//deleteUser是delete标签的id属性值，通过delete标签的id属性值就可以找到要执行的SQL
		String statement = "me.mapping.userMapper.deleteUser";
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		int result = sqlSession.delete(statement,4);
		sqlSession.close();
		System.out.println("delete影响条数："+result);
	}
	
	public static void testGetAll(){
		//getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		String statement = "me.mapping.userMapper.getAllUsers";
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		List<User> list = sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println("条数："+list.size()+" 内容为："+list);
	}
	
	public static void testGetUser(){
		String statement = "me.mapping.userMapper.getUser";
		SqlSession sqlSession = MybatisUtil.getSqlSession(true);
		User user = sqlSession.selectOne(statement, 1);
		sqlSession.close();
		System.out.println(user);		
	}
}
