package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Action;
import com.dao.AlbumDao;
import com.domain.Album;

public class ContentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		AlbumDao manager = AlbumDao.getInstance();
		Album album = manager.getArticle(num);
		if(album != null){
			album.setReadcount(manager.updateReadCount(num));
		}
		
		request.setAttribute("album", album);
		return "/view/content.jsp";
	}

}
