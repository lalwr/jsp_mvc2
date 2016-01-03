package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Action;
import com.dao.AlbumDao;
import com.domain.Album;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		AlbumDao manager = AlbumDao.getInstance();
		
		Album album = manager.getArticle(num);
		
		request.setAttribute("album", album);
		
		return "/view/updateForm.jsp";
	}

}
