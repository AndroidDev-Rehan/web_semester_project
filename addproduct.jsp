<html><body><center> 

<h2> Virtual Products Store </h2> 
<h3> Add New Product</h3> 


<form name ="register" action="ControllerServlet" > 

<TABLE BORDER="1" > 

<TR> 
  <TD> <h4 > Name </h4> </TD> 
 <TD> <input type="text" name="name" /> </TD> 
</TR> 

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
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="save" name="action" />
			<input type="reset" value="clear" /></TD> 
</TR> 


</TABLE></form> 

<h4><a href="ControllerServlet?action=searchperson" > Search Person </a></h4> 

</center></body></html>