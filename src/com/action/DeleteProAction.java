package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Action;
import com.dao.AlbumDao;
import com.domain.Album;
import com.util.FileUtil;

public class DeleteProAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		AlbumDao manager = AlbumDao.getInstance();
		int check = manager.userCheck(num, passwd);
		
		if(check == 1){
			
			Album album = manager.getArticle(num);
			manager.deleteArticle(num);
			
			if(album.getImage() != null){
				FileUtil.removeFile(album.getImage());
			}
		}
		
		request.setAttribute("check", new Integer(check));
		
		return "/view/deletePro.jsp";
	}

	
}
