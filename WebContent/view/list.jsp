<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글목록</title>
<script>
	function ssearchMessage() {
		if(document.search.keyWord.value==""){
			alert('검색어를 입력하세요');
			document.search.keyWord.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<c:if test="${page.count == 0 }">
<table width="70%" border="1" cellpadding="0" cellspacing="0" align="center">
<tr>
	<td bgcolor="#e9e9e9">앨범에 저장된 글이 없습니다.</td>
</tr>
</table>
</c:if>

<c:if test="${page.count > 0 }">
<table width="70%" border="1" cellpadding="0" cellspacing="0" align="center">
<tr><td colspan="6" align="center"><h1>게시판</h1></td></tr>
<tr bgcolor="gray">
<td width="70">번호</td>
<td width="70">글번호</td>   
<td>글제목</td>
<td width="120">작성자</td>

<td width="70">조회수</td>
<td width="120">작성일 </td>

</tr>
<c:set var="number" value="${page.number}"/>
<c:forEach var="album" items="${albumList}">
<tr>
<td>${number}<c:set var="number" value="${number - 1}"/></td>
<td>${album.num}</td>   
<td><a href="content.do?num=${album.num}">${album.subject}</a></td>
<td>${album.writer}</td>
<td>${album.readcount}</td>
<td><fmt:formatDate value="${album.reg_date}" pattern="yyyy년MM월dd일"/></td>
</tr>
</c:forEach>
</table>
</c:if>
<form action="list.do" name="search" method="get" onsubmit="return searchMessage()">
<table width="70%" border="1" align="center" cellpadding="4" cellspacing="0">
<tr><td width="200"><a href="writeForm.do">글쓰기</a></td>
<td align="center" valign="bottom">
<select name="keyField">
<option value="subject">제목</option>     
<option value="writer">이름</option>
<option value="content">내용</option>
</select></td>

<td><input type="text" size=16 name="keyWord"><input type="submit" value="찾기"></td>

</tr>

<tr><td colspan="3" align="center">

<c:if test="${page.count > 0}">
<c:set var="pageCount" value="${(page.count - 1) / page.pageSize + 1}"/>
<c:set var="pageBlock" value="${10}"/>
<fmt:parseNumber var="rs" value="${(currentPage - 1) / pageBlock}" integerOnly="true" />
<c:set var="startPage" value="${rs*pageBlock+1}"/>
<c:set var="endPage" value="${startPage + pageBlock-1}"/>
<c:if test="${endPage > pageCount}">
<c:set var="endPage" value="${pageCount}"/>
</c:if>
 
<c:if test="${startPage > pageBlock}">
<a href="list.do?pageNum=${startPage - pageBlock }&keyField=${page.keyField}&keyWord=${page.keyWord}">[이전]</a>
</c:if>
<c:forEach var="i" begin="${startPage}" end="${endPage}">
<c:if test="${i == page.currentPage}">
[${i}]
</c:if>
<c:if test="${i != page.currentPage}">
<a href="list.do?pageNum=${i}&keyField=${page.keyField}&keyWord=${page.keyWord}">[${i}]</a>
</c:if>
</c:forEach>
<c:if test="${endPage < pageCount}">
<a href="list.do?pageNum=${startPage + pageBlock}&keyField=${page.keyField}&keyWord=${page.keyWord}">[다음]</a>
</c:if>
</c:if>
</td></tr>
</table>
</form>
</body>
</html>