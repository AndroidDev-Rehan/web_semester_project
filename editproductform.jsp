<%@page errorPage="addbookerror.jsp" %> 
<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html><body><center> 

<%
PersonInfo person = (PersonInfo)session.getAttribute("person");

if(person==null){
  response.sendRedirect("login.jsp?error=true");  
}

String name = (String)session.getAttribute("productname");
Product product = (Product)session.getAttribute("product");

if((name==null) || (product == null) ){
  session.setAttribute("header", "You dont have permissions to perform this operation");  
  response.sendRedirect("home.jsp");  
}

System.out.println(product.getCompany());

%>

<h2> Virtual Products Store </h2> 
<h3> Editing Product <%= name %> </h3> 


<form name ="editproductform" action="ControllerServlet" > 

<TABLE BORDER="1" > 

<TR>
  <TD> <h4> Company </h4> </TD> 
  <TD> <input type="text" name="company" value=<%= product.getCompany() %> /> </TD> 
</TR> 

<TR> 
  <TD> <h4> Color</h4> </TD> 
  <TD> <input type="text" name="color" value="<%= product.getColor() %>" /> </TD> 
</TR> 

<TR> 
  <TD> <h4> Price</h4> </TD> 
  <TD> <input type="text" name="price" value="<%= product.getPrice() %>" /> </TD> 
</TR> 


<TR> 
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="Update and Save" name="action" />
			<input type="reset" value="clear" /></TD> 
</TR> 


</TABLE></form> 

<h4><a href="ControllerServlet?action=searchperson" > Search Person </a></h4> 

</center></body></html>