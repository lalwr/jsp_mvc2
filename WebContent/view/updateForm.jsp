<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import ="com.dao.AlbumDao" %>  
<%@ page import ="com.domain.Album" %>  
<%
int num = Integer.parseInt(request.getParameter("num"));
AlbumDao manager = AlbumDao.getInstance();
Album album = manager.getArticle(num);
if(album.getEmail()==null){
album.setEmail("");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글수정</title>
<script type="text/javascript">
<!--
20.
function checkIt(){
21.
var user =document.userinput;
22.
 
23.
if(!user.writer.value){
24.
alert("사용자 이름을 입력하세요");
25.
user.name.focus();
26.
return false;
27.
}
28.
if(!user.passwd.value){
29.
alert("비밀번호를 입력하세요");
30.
user.passwd.focus();
31.
return false;
32.
}
33.
if(!user.content.value){
34.
alert("내용을 입력하세요");
35.
user.content.focus();
36.
return false;
37.
}   
38.
}
39.
//-->
</script>
</head>
<body>
<form action="updatePro.do" method="post" encType="multipart/form-data" name="userinput"  onSubmit="return checkIt()"> 
<input type="hidden" name="num" value="<%=num%>">
<table width ="70%" border ="1" cellpadding="0" cellspacing="0" align="center">
<tr>
<td colspan="2" align="center"><h1>수정하기</h1></td>
</tr>
<tr>
<td>글번호</td>
<td><%=album.getNum() %></td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" name="writer" value="<%=album.getWriter() %>" size="10"></td>
</tr>
<tr>
<td>이메일</td>
<td><input type="text" name="email" value="<%=album.getEmail() %>" size="30"></td>
</tr>
<tr>
<td>제목</td>
<td><input type="text" name="subject" value="<%=album.getSubject()%>" size="50"></td>
</tr>
<tr>
<td>이미지교체</td>
<input type="hidden" name="originImage" value="<%=album.getImage()%>">
<td><img src="../upload/<%=album.getImage()%>" width="50" height="50">
<input type="file" size="8" name="image"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea name="content" rows="5" cols="50"><%=album.getContent() %></textarea></td>
</tr>
<tr>
<td>암호</td>
<td><input type="password" name ="passwd" size="10">
암호와 동일해야 글이 수정됩니다.</td>
</tr>
<tr>
<td colspan="2"  align="center">
<input type="submit" value="수정하기" > 
<input type="reset" value="다시작성">
<input type="button" value="목록보기" onClick="location.href='list.do'">   
</td>
</tr>
</table>   
</form> 
</body>
</html>