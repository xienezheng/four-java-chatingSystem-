<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8");

String s=request.getParameter("message");


String name="";
Object u=session.getAttribute("name2");
name=u.toString();
s=name+":"+s;



	 
	  Object m1=application.getAttribute("words");
	  if(m1==null)
	  {
		  application.setAttribute("words",s+"<br>");
	  }
	  else
	  {
		  application.setAttribute("words",m1.toString()+s+"<br>");
		     
	  }


%>
<form action="name2.jsp"method="post">

<input type="text"name="message"size="50">
<input type="submit"value="发送">
</form>
</body>
</html>