package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/mysql");
		System.out.println(ds); 
		return ds.getConnection();
	}
	
	public void insertArticle(Album album) throws Exception{

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		
		try {
			conn = getConnection();
			sql = "insert into ALBUM (writer, subject, email, content, passwd, reg_date, ip, image )"
				 +"values(?, ?, ?, ?, ?, now(), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, album.getWriter());
			pstmt.setString(++cnt,  album.getSubject());
			pstmt.setString(++cnt, album.getEmail());
			pstmt.setString(++cnt, album.getContent());
			pstmt.setString(++cnt, album.getPasswd());
			pstmt.setString(++cnt, album.getIp());
			pstmt.setString(++cnt, album.getImage());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			execClose(null,pstmt,conn);
		}
		
	}
	
	//글 갯수
	public int getArticleCount(String keyField, String keyWord) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = null;
		
		try {
			conn = getConnection();
			if(keyWord == null || "".equals(keyWord.trim())){
				sql =  "select count(*) from ALBUM";
				pstmt = conn.prepareStatement(sql);
			}else{
				sql="select count(*) from album where" + keyField + " like ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
			}
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			execClose(rs, pstmt, conn);
		}
		return count;
	}
	
	public List<Album> getArticles(int startRow, int endRow, String keyField, String keyWord) throws Exception{

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Album> list = null;
		String sql = null;
		
		try {
			conn = getConnection();
			if(keyWord == null || "".equals(keyWord.trim())){
				sql ="select b.* from (select a.* from (select *, @ROWNUM := @ROWNUM + 1 rnum from ALBUM order by num desc) a ) b where rnum >=? and rnum <=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}else{
				sql = "select * from(select a.*, rownum rnum from(select * from ALBUM where " + keyField + " like ? order by num desc)a) where rnum >=? and rnum <=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<Album>();
				do{
					Album album = new Album();
					album.setNum(rs.getInt("num"));
					album.setWriter(rs.getString("wirte"));
					album.setSubject(rs.getString("subject"));
					album.setEmail(rs.getString("email"));
					album.setPasswd(rs.getString("passwd"));
					album.setReg_date(rs.getTimestamp("reg_date"));
					album.setIp(rs.getString("ip"));
					album.setImage(rs.getString("image"));
					album.setReadcount(rs.getInt("readcount"));
					album.setContent(rs.getString("content"));
					list.add(album);
				}while(rs.next());						
			}else{
				list = Collections.EMPTY_LIST;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			execClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public Album getArticle(int num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Album album = null;
		String sql =null;
		
		try {
			conn = getConnection();
			sql = "select * from ALBUm where num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				album = new Album();
				album.setNum(rs.getInt(num));
				album.setWriter(rs.getString("writer"));
				album.setSubject(rs.getString("subject"));
				album.setEmail(rs.getString("email"));
				album.setPasswd(rs.getString("passwd"));
				album.setReg_date(rs.getTimestamp("reg_date"));
				album.setIp(rs.getString("ip"));
				album.setImage(rs.getString("image"));
				album.setReadcount(rs.getInt("readcount"));
				album.setContent(rs.getString("content"));					
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			execClose(rs, pstmt, conn);
		}
		return album;
	}

	public int updateReadCount(int num) {
		// TODO Auto-generated method stub
		return 1;
	}
	public int userCheck(int num, String passwd) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void deleteArticle(int num) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//자원정리
	private void execClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) try {rs.close();} catch(SQLException sqle){}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}	
	}

}
