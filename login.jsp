<html>
<body>
<center>
<form action = "ControllerServlet" method = "get">
<% 
if (request.getParameter("error")!=null){%>

<h1>Invalid Session, Login Again </h1>
<%} 
%>

<% 
if (request.getParameter("blocked")!=null){%>

<h1>Unable to Login, User is blocked </h1>
<%} 
%>

<% 
if (request.getParameter("userexists")!=null){%>

<h1>Sign Up failed, user with same name id already exists </h1>
<%} 
%>

<% 
if (request.getParameter("invalidcreds")!=null){%>

<h1>Invalid Credentials, try again. </h1>
<%} 
%>


Login Id:<input type="text" name="name" required><br><br>
Password :<input type="text" name="password" required><br><br>
<input type="submit" value="login" name = "action" ><br><br>
<h3>Dont have an account? <a href="signup.jsp" > signup </a></h3>
</form>
</center>
</body>
</html>