<%@page errorPage="addbookerror.jsp" %> 

<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

<html><body><center> 

<%
PersonInfo oldPerson = (PersonInfo)session.getAttribute("person");

if(oldPerson==null){
  response.sendRedirect("login.jsp?error=true");  
}

%>

<h2> Virtual Store </h2> 
<h3> Following results meet your search criteria</h3> 

<TABLE BORDER="1" > 

<TR> 
 <TH> Name </TH> 
 <TH> Phone No </TH> 
 <TH> Address </TH> 
 <TH> User Type </TH> 
</TR> 

<% 
 ArrayList userslist = (ArrayList)request.getAttribute("userslist"); 
 PersonInfo person = null; 

for(int i=0; i<userslist.size(); i++) { 
person = (PersonInfo)userslist.get(i); %> 


<TR> <TD> <%= person.getName()%> </TD>
<TD> <%= person.getPhoneNum()%> </TD>
<TD> <%= person.getAddress()%> </TD>
<TD> <%= person.getUserType()%> </TD> 

</TR> 

<% 
} 
%> 

</TABLE > 
<h4><a href="ControllerServlet?action=addproduct"> Add Product </a>
<a href="ControllerServlet?action=searchproduct">Search Product</a></h4> 
</center> </body></html> 

