<%@page errorPage="addbookerror.jsp" %> 
<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html><body><center> 

<%
PersonInfo person = (PersonInfo)session.getAttribute("person");

if(person==null){
  response.sendRedirect("login.jsp?error=true");  
}
%>

<h2> Virtual Products Store </h2> 
<h3> Add New Product</h3> 


<form name ="register" action="ControllerServlet" > 

<TABLE BORDER="1" > 

<TR> 
  <TD> <h4 > Name </h4> </TD> 
 <TD> <input type="text" name="name" required /> </TD> 
</TR> 

<TR>
  <TD> <h4> Company </h4> </TD> 
  <TD> <input type="text" name="company" required /> </TD> 
</TR> 

<TR> 
  <TD> <h4> Color</h4> </TD> 
  <TD> <input type="text" name="color" required /> </TD> 
</TR> 

<TR> 
  <TD> <h4> Price</h4> </TD> 
  <TD> <input type="number" name="price" required /> </TD> 
</TR> 


<TR> 
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="save" name="action" />
			<input type="reset" value="clear" /></TD> 
</TR> 


</TABLE></form> 


</center></body></html>