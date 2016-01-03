<%@ page language="java" contentType="text/html; charset=UTF-8"

pageEncoding="UTF-8"%>

<%@ taglib    prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:if test="${check == 1}">

<script type="text/javascript">

alert("글정보가 삭제 되었습니다.");

location.href="list.do";
</script>
</c:if>
<c:if test="${check != 1}">   
<script type="text/javascript">
alert("비밀번호가 맞지 않습니다.");
history.go(-1);
</script>
</c:if>