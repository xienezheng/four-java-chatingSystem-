<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

String s1=request.getParameter("name");
String s2=request.getParameter("password");
Cookie cookies[]=request.getCookies();
String name="";String password="";
for(int i=0;i<cookies.length;i++)
{
	if(cookies[i].getName().equals("name"))
	{
		name=cookies[i].getValue();
	}
	if(cookies[i].getName().equals("password"))
	{
		password=cookies[i].getValue();
	}
	
}
session.setAttribute("name1",s1);
if(s1.equals(name)&&s2.equals(password))
{
	response.sendRedirect("chating1.html");
}
else
{
	response.sendRedirect("download.html");
}
			

%>
</body>
</html>