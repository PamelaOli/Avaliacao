<!--  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Example</title>
</head>
<body>
    <s:form action="/login" focus="userName">
    Username : <s:text property="userName" />
        <br>
    Password : <s:password property="password" />
        <br>
        <s:submit value="login" />
    </s:form>
 
</body>
</html>
-->