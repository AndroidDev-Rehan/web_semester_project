<%@page errorPage="addbookerror.jsp" %> 

<html><body><center> 

<h2> Virtual Store </h2> 
<h3> Delete Product</h3> 

<form name ="delete" action="ControllerServlet" > 
<TABLE BORDER="1" > 

<TR> 
  <TD><h4 >Product Name</h4></TD> 
  <TD><input type="text" name="name" /></TD> 
</TR> 

<TR>
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="delete" name="action" />
  <input type="reset" value="clear" /></TD> 
</TR> 

</TABLE></form> 

<h4><a href="ControllerServlet?action=addproduct" > Add Product </a></h4> 

</center></body></html>