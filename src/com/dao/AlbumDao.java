package com.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.domain.Album;

public class AlbumDao {

	private static AlbumDao instance = new AlbumDao();
	
	//싱글턴 패턴
	private AlbumDao(){}
	public static AlbumDao getInstance() {
		return instance;
	}
	
	//getConnection : JDBC DB연동
	private Connection getConection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/mysql");
		
		return ds.getConnection();
	}

	public Album getArticle(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object updateReadCount(int num) {
		// TODO Auto-generated method stub
		return null;
	}

}
