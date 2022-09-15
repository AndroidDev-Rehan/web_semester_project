<html>

<script>  
function verifyPassword() {  
  var pw = document.getElementById("pswd").value;  
  //check empty password field  
  if(pw == "") {  
     document.getElementById("message").innerHTML = "**Fill the password please!";  
     return false;  
  }  
   
 //minimum password length validation  
  if(pw.length < 8) {  
     document.getElementById("message").innerHTML = "**Password length must be atleast 8 characters";  
     return false;  
  }  
  
//maximum length of password validation  
  if(pw.length > 15) {  
     document.getElementById("message").innerHTML = "**Password length must not exceed 15 characters";  
     return false;  
  } else {  
     alert("Password is correct");  
  }  
}

function matchPassword() {  
    
  var pw1 = myform.password.value;  
  var pw2 = myform.confirm_pass.value;  
  if(pw1==pw2)  
  {   
 
    return true;
  }  
  alert("Passwords did not match");  
    return false; 
}  
</script>  

<body>
<center>
<form action = "ControllerServlet" name="myform" method = "get" onsubmit = "return matchPassword(this)">
Name :<input type="text" name="name" required><br><br>
Password :<input type="password" name="password" required><br><br>
Confirm Password :<input type="password" name="confirm_pass" required><br><br>
Address:<input type="text" name="address" required><br><br>
Phone No:<input type="number" name="phoneNum" required><br><br>
<%-- User Type :<input type="text" name="usertype" required><br><br> --%>

<input type="submit" value="signup" name = "action" ><br><br>
<h3>Already have an account? <a href="login.jsp" > Login </a></h3>
</form>
</center>
</body>
</html>