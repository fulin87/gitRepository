<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<title>对不起，系统故障，您访问的页面暂时无法访问!</title>
<style type="text/css">
    body{margin:0px; padding:0px;}
	ul,ol,dl,li,dt,dd{list-style:none; margin:0px; padding:0px;}
	
	
	div.header{ width:100%; height:115px; background-color:#191919;}
	div.header img{display:block; margin:0; padding:0; border:0}
	div.nav{width:100%; height:35px; background-color:#aa1022}
	
	.error404{width:100%; margin:30px auto;overflow:hidden;font-family:"Microsoft Yahei",Arial}
	.error404_1{ float:left; width:128px; height:128px;background:url(painful.png) no-repeat; margin-left:250px; margin-top:10px}
	.error404_2{float:left; margin-left:30px}
	.error404_2 h3{ font-size:16px; margin:40px 0 10px 0;font-weight:normal}
	.error404_2 p{ line-height:25px;font-size:12px}
	.error404_2 p a{text-decoration: none; color: #999; font-size:12px}
</style>
</head> 
<body>
    <%
        //LogUtils.logPageError(request);

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        pageContext.setAttribute("statusCode", statusCode);

        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String queryString = request.getQueryString();
        String url = uri + (queryString == null || queryString.length() == 0 ? "" : "?" + queryString);
        pageContext.setAttribute("url", url);

        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        request.setAttribute("exception", exception);

    %>

    <c:if test="${statusCode eq 404}">
	    <div class="error404">
	       <div class="error404_1"></div>
		   <div class="error404_2">
		     <h3>您可以:</h3>
		        <p>
		        1.页面没有找到！对不起，暂时没有找到您所访问的页面地址,请联系管理员解决此问题！&nbsp;&nbsp;&nbsp;&nbsp;<refresh><a href='${url}' class='btn btn-danger'>刷新,看看是否能访问了</a></refresh>
		        </p>
		        <p>
		        2.去其他地方逛逛：<a href="javascript:history.go(-1);">返回上一页</a> 
		        </p>
		   </div>
	    </div>
     </c:if>

    <c:if test="${statusCode ne 404}">
	    <div class="error404">
	    	<div class="error404_1"></div>
	    	<div class="error404_2">
	     		<h3>您可以:</h3>
	        	<p>
	     		1、服务器程序出问题了！对不起,您访问的页面出了一点内部小问题，刷新重新访问或先去别的页面转转,过会再来吧~！&nbsp;&nbsp;&nbsp;&nbsp;<refresh><a href='${url}' class='btn btn-danger'>刷新,看看是否能访问了</a></refresh>
	        	</p>
	        	<p>
	       	 	2.去其他地方逛逛：<a href="javascript:history.go(-1);">返回上一页</a> 
	        	</p>
	    	</div>
	    </div>
        <c:if test="${not empty exception}">
            <%
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                exception.printStackTrace(printWriter);
                pageContext.setAttribute("stackTrace", stringWriter.toString());
            %>
        </c:if>
    </c:if>
 </body>