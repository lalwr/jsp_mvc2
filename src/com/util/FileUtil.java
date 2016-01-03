package com.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUtil {
	
	public static final String UPLOAD_PATH = "/Users/mac/programing/eclipse_worksapce/jsp_mvc2/WebContent/upload";
	public static final String ENCODING_TYPE = "utf-8";
	public static final int MAX_SZIE = 10*1024*1024; //10M
	
	public static MultipartRequest createFile(HttpServletRequest request) throws IOException{
		return new MultipartRequest(request, UPLOAD_PATH, MAX_SZIE, ENCODING_TYPE, new DefaultFileRenamePolicy());
	}
	
	//알아서 파일명을 만들어줌
	public static String rename(String filename) throws Exception{
		if(filename == null) return null;
		String newName = Long.toString(System.currentTimeMillis())+(int)(Math.random()*99);		
		return rename(filename, newName);
	}
	
	//지정한 파일명을 사용한다
	private static String rename(String filename, String newName) throws Exception{
		if(filename == null) return null;
		File file = new File(UPLOAD_PATH,filename);
		//파일명을 원하는 형식으로 변경
		int idx = filename.lastIndexOf(".");
		String extention = "";
		String newFileName = "";
		
		if(idx != -1){
			extention = filename.substring(idx);
		}
		//newName 전달시 확장자를 제외해야 하지만 화장자를 포함할 경우 제거함
		int newIdx = newName.lastIndexOf(".");
		if(newIdx != -1){
			newName = newName.substring(0, newIdx);
		}
		
		newFileName = newName + extention.toLowerCase();
		File fs = new File(UPLOAD_PATH,newFileName);
		file.renameTo(fs);
		return newFileName;
	}

	public static void removeFile(String filename) {
		if(filename != null){
			File file = new File(UPLOAD_PATH, filename);
			if(file.exists()) file.delete();
		}
		
	}

	

	

}
