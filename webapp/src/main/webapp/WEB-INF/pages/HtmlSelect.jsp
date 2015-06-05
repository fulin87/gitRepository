<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="mapValues" var="entry"  status="entry">
	<s:set var="%{#entry.key}" value="%{#entry.value}" scope="page"></s:set>
	key : <s:property value="key"/>
    value:<s:property value="value"/>
    <br>
</s:iterator>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Html Select option testing</title>
</head>
<body>
	<s:property value="%{#p0}"/>
<s:select list="{'aa','bb','cc'}"  headerKey="00" headerValue="0"></s:select>
<br>
<s:select list="#{1:'aa',2:'bb',3:'cc'}"  label="abc" listKey="key" listValue="value"  headerKey="0" headerValue="-1"></s:select>
<br>

</body>
</html>