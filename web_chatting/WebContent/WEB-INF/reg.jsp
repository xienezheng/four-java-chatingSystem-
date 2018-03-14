<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String s=request.getParameter("name");
String s1=request.getParameter("password1");
String s2=request.getParameter("password2");
if(s1.equals(s2))
{
	String s4=request.getParameter("sex");
	String s5=request.getParameter("city");
	String s6=request.getParameter("add");
	out.println("您的基本信息为：");
	out.println("姓名："+s+"<br>");
	out.println("性别："+s4+"<br>");
	out.println("城市："+s5+"<br>");
	out.println("备注:"+s6+"<br>");
	
	
}
else
{
	out.println("密码不一致，请重新输入");
	
	
}
		
			
			


%>
</body>
</html>