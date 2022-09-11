<%@page errorPage="addbookerror.jsp" %> 

<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html><body><center> 

<h2> Virtual Store </h2> 
<h3> Following results meet your search criteria</h3> 

<TABLE BORDER="1" > 

<TR> 
 <TH> Name </TH> 
 <TH> Company </TH> 
 <TH> Color </TH> 
 <TH> Price </TH> 
</TR> 

<% 
 ArrayList productList = (ArrayList)request.getAttribute("list"); 
 Product product = null; 

for(int i=0; i<productList.size(); i++) { 
product = (Product)productList.get(i); %> 


<TR> <TD> <%= product.getName()%> </TD>
<TD> <%= product.getCompany()%> </TD>
<TD> <%= product.getColor()%> </TD>
<TD> <%= product.getPrice()%> </TD> 
</TR> 

<% 
} 
%> 

</TABLE > 
<h4><a href="ControllerServlet?action=addproduct"> Add Product </a>
<a href="ControllerServlet?action=searchproduct">Search Product</a></h4> 
</center> </body></html> 

