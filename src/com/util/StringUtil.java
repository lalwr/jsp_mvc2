package com.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringUtil {
	public static String clobToString(ResultSet rs, String msg) throws SQLException, IOException {
		StringBuffer sb = new StringBuffer();
		
		Reader rd = rs.getCharacterStream(msg);
		char[] buffer = new char[1024];
		int byteRead;
		while((byteRead=rd.read(buffer,0,1024))!=-1){
			sb.append(buffer,0,byteRead);
		}
		rd.close();
		return sb.toString();
	}

}
