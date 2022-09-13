<%@page errorPage="addbookerror.jsp" %> 
<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html><body><center> 

<%
PersonInfo person = (PersonInfo)session.getAttribute("person");

if(person==null){
  response.sendRedirect("login.jsp?error=true");  
}

String name = (String)session.getAttribute("productname");

if(name==null){
  response.sendRedirect("home.jsp");  
}

%>

<h2> Virtual Products Store </h2> 
<h3> Editing Product <%= name %> </h3> 


<form name ="editproductform" action="ControllerServlet" > 

<TABLE BORDER="1" > 

<TR>
  <TD> <h4> Company </h4> </TD> 
  <TD> <input type="text" name="company" /> </TD> 
</TR> 

<TR> 
  <TD> <h4> Color</h4> </TD> 
  <TD> <input type="text" name="color" /> </TD> 
</TR> 

<TR> 
  <TD> <h4> Price</h4> </TD> 
  <TD> <input type="text" name="price" /> </TD> 
</TR> 


<TR> 
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="Edit and Save" name="action" />
			<input type="reset" value="clear" /></TD> 
</TR> 


</TABLE></form> 

<h4><a href="ControllerServlet?action=searchperson" > Search Person </a></h4> 

</center></body></html>