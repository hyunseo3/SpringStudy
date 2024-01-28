package com.example.demo.db;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVO;

public class DBManager {
	public static SqlSessionFactory sqlSeSessionFactory;
	static {
		try {
			String resource="com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream=Resources.getResourceAsStream(resource);
			sqlSeSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			inputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static List<MemberVO> findAll(){
		SqlSession session=sqlSeSessionFactory.openSession();
		List<MemberVO> list= session.selectList("member.findAll");
		session.close();
		return list;
	}
	
	public static int insert(MemberVO m) {
		SqlSession session=sqlSeSessionFactory.openSession();
		int re=session.insert("member.insert",m);
		session.commit();
		session.close();
		return re;
	} 
}
