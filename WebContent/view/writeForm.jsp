<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
<script type="text/javaScript">
function writeSave(){

if(document.writeform.writer.value==""){
alert("작성자를 입력하십시요.");
document.writeform.writer.focus();
return false;
}
if(document.writeform.subject.value==""){
alert("제목을 입력하십시요.");
document.writeform.subject.focus();
return false;
}

if(document.writeform.content.value==""){
alert("내용을 입력하십시요.");
document.writeform.content.focus();
return false;
}

if(document.writeform.passwd.value==""){
alert(" 비밀번호를 입력하십시요.");
document.writeform.passwd.focus();
return false;
}
return true;
}   
</script>
</head>
<body>
<center><b>글쓰기</b></center>
<br>
<form method="post" name="writeform" action="writePro.do" encType="multipart/form-data" onsubmit="return writeSave()">
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
<tr>
<td  width="70" align="center">이 름</td>
<td  width="330">
<input type="text" size="10" maxlength="10" name="writer"></td>
</tr>
<tr>
<td  width="70" align="center" >제 목</td>
<td  width="330">
<input type="text" size="40" maxlength="50" name="subject">
</td>
</tr>
<tr>
<td  width="70" align="center">Email</td>
<td  width="330">
<input type="text" size="40" maxlength="30" name="email" ></td>
</tr>
<tr>
<td  width="70" align="center" >내 용</td>
<td  width="330" >
<textarea name="content" rows="13" cols="40"></textarea> </td>
</tr>
<tr>
<td  width="70" align="center" >비밀번호</td>
<td  width="330" >
<input type="password" size="8" maxlength="12" name="passwd">
</td>
</tr>
<tr>
<td  width="70" align="center" >이미지</td>
<td  width="330" >
<input type="file" size="8" name="image">
</td>
</tr>
<tr>     
<td colspan=2 align="center">
<input type="submit" value="글쓰기" > 
<input type="reset" value="다시작성">
<input type="button" value="목록보기" onClick="location.href='list.do'">
</td></tr></table>
</form>           
</body>
</html>