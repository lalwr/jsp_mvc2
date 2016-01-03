<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>입력</title>
</head>
<body>

<table width="70%" border="1" cellpadding="0" cellspacing="0" align="center" >
<tr><td colspan="2 align="center><h1>상세보기</h1></td></tr>
<tr>
<td>글번호</td>
<td>${album.num}</td>
</tr>

<tr>
<td>작성자</td>
<td>${album.writer}</td>
</tr>

<tr>
<td>IP</td>
<td>${album.ip}</td>
</tr>

<tr>
<td>조회수</td>
<td>${album.readcount}</td>
</tr>

<tr>
<td>작성일</td>
<td><fmt:formatDate value="${album.reg_date}" pattern="yyyy년MM월dd일"/></td>
</tr>

<tr>
<td>이메일</td>
<c:if test="${!empty album.email }">
		<td>${album.email }</td>
</c:if>
<c:if test="${empty album.email }">
		<td>&nbsp;</td>
</c:if>
</tr>

<tr>
<td>이미지</td>
<td><img src="../upload/${album.image}" /></td>
</tr>

<tr>
<td>글제목</td>
<td>${album.subject}</td>
</tr>

<tr>
<td>내용</td>
<td>${album.content}</td>
</tr>

<tr>
<td colspan="2" align="center">
	<input type="button" value="수정하기" onClick="location.href='updateForm.do?num=${album.num}'" >
	<input type="button" value="삭제하기" onClick="location.href='deleteForm.do?num=${album.num}'" >
	<input type="button" value="목록보기" onClick="location.href='list.do'" >
</td>
</tr>
</table>
</body>
</html>