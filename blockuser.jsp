<%@page errorPage="addbookerror.jsp" %> 
<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html><body><center> 

<%
PersonInfo person = (PersonInfo)session.getAttribute("person");

if(person==null){
  response.sendRedirect("login.jsp?error=true");  
}

else if (person.getUserType().equals("admin")==false){
response.sendRedirect("home.jsp");  
}


%>

<h2> Virtual Store </h2> 
<h3> Search Product</h3> 

<form name ="block" action="ControllerServlet" > 
<TABLE BORDER="1" > 

<TR> 
  <TD><h4 >Name</h4></TD> 
  <TD><input type="text" name="name" required /></TD> 
</TR> 

<TR>
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="block" name="action" />
  <input type="reset" value="clear" /></TD> 
</TR> 

</TABLE></form> 

<h4><a href="ControllerServlet?action=addproduct" > Add Product </a></h4> 

</center></body></html>