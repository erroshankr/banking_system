<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body{
  font-family: Calibri, Helvetica, sans-serif;
  background-color: pink;
}
.container {
    padding: 50px;
  background-color: lightblue;
}

input[type=text], input[type=password], textarea {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}
input[type=text]:focus, input[type=password]:focus {
  background-color: orange;
  outline: none;
}
 div {
            padding: 10px 0;
         }
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
.registerbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}
.registerbtn:hover {
  opacity: 1;
}
</style>


</head>
<body>
<form:form action="/submitForgotPassword" method="post" modelAttribute="forgotPasswordForm">
<div class="error">
  <c:if test="${userNameError eq 'NOT_FOUND_USER'}">
      <p>User Name Not Found !!</p>
  </c:if>
  <c:if test="${passwordError eq 'PASSWORD_NOT_SAME'}">
        <p>Password And Repeat Password Does Not Match !!</p>
    </c:if>
</div>
  <div class="container">
  <center>  <h1>Forgot Password</h1></center>
  <hr>
  <form:label path="email" class="font">Email</form:label>
              <form:input type="text" class="" path="email" id="email" name="email" placeholder="Enter Email"></form:input><br>
<form:label path="newPassword" class="font">New Password:</form:label>
            <form:input type="text" class="" path="newPassword" id="newPassword" name="newPassword" placeholder="Enter New Password"></form:input><br>
<form:label path="repeatPassword" class="font">Repeat Password:</form:label>
            <form:input type="text" class="recieve" path="repeatPassword" id="repeatPassword" name="repeatPassword" placeholder="Re-Enter Password"></form:input><br>
<div>
    <input type="submit" value="SUBMIT" class="button">

</form:form>
</body>
</html>