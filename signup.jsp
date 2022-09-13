<html>
<body>
<center>
<form action = "ControllerServlet" method = "get">
Name :<input type="text" name="name" required><br><br>
Password :<input type="text" name="password" required><br><br>
Address:<input type="text" name="address" required><br><br>
Phone No:<input type="text" name="phoneNum" required><br><br>
<%-- User Type :<input type="text" name="usertype" required><br><br> --%>

<input type="submit" value="signup" name = "action"><br><br>
<h3>Already have an account? <a href="login.jsp" > Login </a></h3>
</form>
</center>
</body>
</html>