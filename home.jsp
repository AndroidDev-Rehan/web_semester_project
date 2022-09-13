<%@page errorPage="addbookerror.jsp" %> 
<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<div class="w3-bar w3-black">
  <a href="ControllerServlet?action=logout" class="w3-bar-item w3-button">Log Out</a>
  <%-- <a href="#" class="w3-bar-item w3-button">Link 1</a>
  <a href="#" class="w3-bar-item w3-button">Link 2</a>
  <a href="#" class="w3-bar-item w3-button">Link 3</a> --%>
</div>

<center> 

<%   


String statement = (String)session.getAttribute("header");
PersonInfo person = (PersonInfo)session.getAttribute("person");

if(person==null){
  response.sendRedirect("login.jsp?error=true");  
}

if(statement==null){
    statement = "no header";
}

  
%>  

<h3> <%= statement  %> </h3><br> 
<h4>  <a href="ControllerServlet?action=addproduct" > Add Product </a> </h4><br> 
<h4> <a href="ControllerServlet?action=viewproducts" > View All Products </a> </h4><br> 
<h4> <a href="ControllerServlet?action=searchproduct" > Search Product </a> </h4><br> 
<h4> <a href="ControllerServlet?action=deleteproduct" > Delete Product </a> </h4><br> 
<h4> <a href="ControllerServlet?action=viewusers" > View All Users </a> </h4><br> 
<h4> <a href="ControllerServlet?action=blockuser" > Block User </a> </h4><br> 
<h4> <a href="ControllerServlet?action=viewusers" > Unblock User </a> </h4><br>
<h4> <a href="ControllerServlet?action=editproduct" > Edit Product </a> </h4><br> 

</center></body></html> 
