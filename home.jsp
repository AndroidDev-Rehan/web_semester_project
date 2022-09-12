<%@page errorPage="addbookerror.jsp" %> 
<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html><body><center> 

<%   
  
String statement = (String)session.getAttribute("header");

if(statement==null){
    statement = "no header";
}

  
%>  

<h3> <%= statement  %> </h3> 
<h4>  <a href="ControllerServlet?action=addproduct" > Add Product </a> </h4> 
<h4> <a href="ControllerServlet?action=viewproducts" > View All Products </a> </h4> 
<h4> <a href="ControllerServlet?action=searchproduct" > Search Product </a> </h4> 
<h4> <a href="ControllerServlet?action=deleteproduct" > Delete Product </a> </h4> 
<h4> <a href="ControllerServlet?action=viewusers" > View All Users </a> </h4> 
<h4> <a href="ControllerServlet?action=viewusers" > Block User </a> </h4> 
<h4> <a href="ControllerServlet?action=viewusers" > Unblock User </a> </h4> 

</center></body></html> 
