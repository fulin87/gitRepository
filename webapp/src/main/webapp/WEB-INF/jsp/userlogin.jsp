<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User login page</title>
</head>
<body>
<b>Please Login</b><br>
<form action="userLogin.form" method="POST">
    User Name: <INPUT id="username" name="username" value="${user.username}"/><br>
    Passwrod : <INPUT id="password" name="password" value="${user.password}" type="password" /><br>
    UserModel Name: <INPUT id="username1" name="username1" value="${usermodel.username}"/><br>
    <INPUT id="btnLogin" type="submit" value="登 录 " name=btnLogin>&nbsp;
    <INPUT id="reset" type="reset" value="重 置" name="reset">
    <br>
    City: 
    <select>
      <c:forEach items="${cityList}" var="city">
        <option value="${city}">${city}</option>
      </c:forEach>
    </select>
</form>
</body>
</html>