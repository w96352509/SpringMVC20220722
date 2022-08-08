<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
		href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Person Form</title>
<style type="text/css">
		.error {
			color: #FF0000;
		}
</style>
</head>
<body style="padding: 15px;">
 <spform:form class="pure-form"  method="post" modelAttribute="person" action="${ pageContext.request.contextPath }/mvc/person/">
  <fieldset>
   <legend>Person Form</legend>
   姓名 : <spform:input  path="name" type="text"/>
          <spform:errors path="name" cssClass="error" /> <p/>
   
   年齡 : <spform:input path="age"  type="number"/> 
          <spform:errors path="age" cssClass="error" />  <p/>
   
   會員 : <spform:radiobutton path="member" value="true"/> 會員 
          <spform:radiobutton path="member" value="false"/> 非會員
          <spform:errors path="member" cssClass="error" />
          <p/>
   
   生日 : <spform:input path="birth" type="date"/>
          
          <spform:errors path="birth" cssClass="error" />    <p/>
   <spform:errors path="*" cssClass="error"></spform:errors> <p/>       
          <button type="submit" class="pure-button pure-button-primary">新增</button>
  
  </fieldset>
 </spform:form>
 <c:forEach var="person" items="${people}" varStatus="status">
  ${ person.name } <p />
  ${ person.age } <p />
  ${ person.member } <p />
  <fmt:formatDate value="${person.birth}" pattern="yyyy-MM-dd"/>
 </c:forEach>

</body>
</html>