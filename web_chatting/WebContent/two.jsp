<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="REFRESH" content="1">

<title>Insert title here</title>
</head>
<body>
<% Object m=application.getAttribute("words");
Cookie cookies[]=request.getCookies();
String name="";
for(int i=0;i<cookies.length;i++)
{
	if(cookies[i].getName().equals("name"))
	{
		name=cookies[i].getValue();
	}
	
	
}
if(m!=null)
{
out.println(m.toString());
}
%>
</body>
</html>