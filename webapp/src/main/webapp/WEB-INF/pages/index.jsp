<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<b>Hello World Using Struts 2</b>
<p><a href="<s:url action='helloWorld'/>">Hello World</a></p>
<p><a href="${helloLink}">Hello Bruce Phillips</a></p>
<p>Get your own personal hello by filling out and submitting this form.</p>

<s:form action="helloWorld">
	<s:textfield name="userName" label="Your name" />
	<s:submit value="Submit" />
</s:form>
</body>
</html>