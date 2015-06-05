<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<title>Motang-validate</title>
</head>

<body scroll="no">
    <H1>Welcome to Motang's webapp-test, This is a validate page!</H1>
	<form:form method="post" modelAttribute="testVo" id="testVo" >
	    测试页面
	     <p style="color:red"><form:errors path="*" cssClass="errors"/> </p>
	     Id: <form:input path="id" size="30" />
	     Name: <form:input path="name" size="30" />
	     Email: <form:input path="email" size="30" />
	     
	     <p><form:button name="testVoSubmit" id="testVoSubmit">Submit</form:button></p>
	</form:form>
</body>
</html>
