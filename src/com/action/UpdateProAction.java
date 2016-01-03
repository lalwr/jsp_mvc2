package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Action;
import com.dao.AlbumDao;
import com.domain.Album;
import com.oreilly.servlet.MultipartRequest;
import com.util.FileUtil;

public class UpdateProAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		String image = multi.getFilesystemName("image");
		
		AlbumDao manager = AlbumDao.getInstance();
		
		int check = manager.userCheck(Integer.parseInt(multi.getParameter("num")), multi.getParameter("passwd"));
		
		if(check ==1){
			
			String originImage = multi.getParameter("originImage");
			
			Album album = new Album();
			
			album.setNum(Integer.parseInt(multi.getParameter("num")));			
			album.setWriter(multi.getParameter("writer"));
			album.setEmail(multi.getParameter("email"));
			album.setSubject(multi.getParameter("subject"));
			album.setPasswd(multi.getParameter("passwd"));
			album.setContent(multi.getParameter("content"));
			album.setIp(request.getRemoteAddr());
			
			if(image != null){
				album.setImage(FileUtil.rename(image));
			}else{
				album.setImage(originImage);
			}
			manager.update(album);
			if(image != null){
				FileUtil.removeFile(originImage);
			}
		
		}else{
			//비번이 틀려 인증 실패시 올리려고 전송된 이미지 삭제
			if(image !=null)FileUtil.removeFile(image);
		}
		
		request.setAttribute("check", new Integer(check));
		return "/view/updatePro.jsp";
	}

}
