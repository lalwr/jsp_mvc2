package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Action;
import com.dao.AlbumDao;
import com.domain.Album;
import com.domain.AlbumPage;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String keyField = request.getParameter("keyField");
		String keyWord = request.getParameter("keyWord");
		if(keyField==null){
			keyField="";
		}
		if(keyWord==null){
			keyWord="";
		}
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null){
			pageNum = "1";
		}
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List<Album> albumList = null;
		AlbumDao manager = AlbumDao.getInstance();
		count = manager.getArticleCount(keyField, keyWord);
		
		if(count>0){
			System.out.println(startRow + "," + endRow);
			albumList = manager.getArticles(startRow, endRow, keyField, keyWord);
		}
		
		number = count - (currentPage-1)*pageSize;
		
		AlbumPage page = new AlbumPage();
		page.setCount(count);
		page.setCurrentPage(currentPage);
		page.setNumber(number);
		page.setPageSize(pageSize);
		page.setKeyField(keyField);
		page.setKeyWord(keyWord);
		
		request.setAttribute("page", page);
		request.setAttribute("albumList", albumList);		
		
		return "/view/list.jsp";
	}
	
	

}
