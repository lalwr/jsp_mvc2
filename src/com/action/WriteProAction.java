package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Action;
import com.dao.AlbumDao;
import com.domain.Album;
import com.oreilly.servlet.MultipartRequest;
import com.util.FileUtil;

public class WriteProAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		MultipartRequest multi = FileUtil.createFile(request);
		
		Album album = new Album();
		
		album.setWriter(multi.getParameter("writer"));
		album.setSubject(multi.getParameter("subject"));
		album.setEmail(multi.getParameter("email"));
		album.setContent(multi.getParameter("content"));
		album.setPasswd(multi.getParameter("passwd"));
		album.setIp(request.getRemoteAddr());
		
		album.setImage(FileUtil.rename(multi.getFilesystemName("image")));
		
		AlbumDao manager = AlbumDao.getInstance();
		manager.insertArticle(album);
		
		return "/view/writePro.jsp";
	}

}
