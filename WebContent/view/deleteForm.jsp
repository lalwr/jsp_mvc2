<%@ page language="java" contentType="text/html; charset=UTF-8"

pageEncoding="UTF-8"%>

<%

String num = request.getParameter("num");

String image = request.getParameter("image");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head><title>글삭제</title>

<link href="style.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
<!--
function begin(){
document.myform.passwd.focus();
}

function checkIt(){
if(!document.myform.passwd.value){
alert("비밀번호를 입력하지 않으셨습니다.");
document.myform.passwd.focus();
return false;
}
return true;
}  
//-->
</script>
</head>

<BODY onload="begin()">
<form name="myform" action="deletePro.do?num=<%=num %>" method="post" onSubmit="return checkIt()">
<table cellspacing=1 cellpadding=1 width="260" border=1 align="center" >
<tr height="30">
<td colspan="2" align="center">
<font size="+1" ><b>글삭제</b></font></td></tr>

<tr height="30">
<td width="110" align=center>비밀번호</td>
<td width="150" align=center>
<input type=password name="passwd"  size="15" maxlength="12"></td></tr>
<tr height="30">
<td colspan="2" align="center">
<input type=submit value="글삭제하기">
<input type="button" value="취  소" onclick="location.href='list.do'"></TD></TR>
</TABLE>
</form>
</BODY>
</HTML>