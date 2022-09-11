<%@page isErrorPage="true" %> 
<%@page import = "java.sql.SQLException" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head><body> 

<h2>Error Page</h2> 

<h3> 
<% if (exception instanceof SQLException) { %> 

An SQL Exception 

<% } else if (exception instanceof ClassNotFoundException){ %> 

A Class Not Found Exception 

<%} else { %> 
A Exception 

<% } %> 


occured while interacting with the database</h3> 

<h3>The Error Message was <%= (String)request.getAttribute("exception") %></h3>
<%-- <h3>The Error Code was <%= (String)request.getAttribute("code") %></h3> --%>
<h3>The Hi was <%= (String)request.getAttribute("hi") %></h3>
<h3 > Please Try Again Later! </h3> 


<h3> <a href="controller.jsp?action=addperson" > Add Person </a> 
<a href="controller.jsp?action=searchperson" > Search Person </a> </h3> 

</body></html> 