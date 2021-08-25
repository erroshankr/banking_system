<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Login Page </title>
<style>
Body {
  font-family: Calibri, Helvetica, sans-serif;
  background-color: pink;
}
.button {
       background-color: #4CAF50;
       width: 100%;
        color: orange;
        padding: 15px;
        margin: 10px 0px;
        border: none;
        cursor: pointer;
         }
 form {
        border: 3px solid #f1f1f1;
    }
 input[type=text], input[type=password] {
        width: 100%;
        margin: 8px 0;
        padding: 12px 20px;
        display: inline-block;
        border: 2px solid green;
        box-sizing: border-box;
    }
 .button:hover {
        opacity: 0.7;
    }

 .container {
        padding: 25px;
        background-color: lightblue;
    }
 .error{
    margin-left: 3rem;
    font-size:1.5rem;
    font-weight=bold;
 }
</style>
</head>
<body>
    <center> <h1> Login Form </h1> </center>
    <form:form action="/submitLogin" method="post" modelAttribute="loginForm">
    <div class="error">
      <c:if test="${NullUser eq 'NOT_EQUAL'}">
          <p>User does not exist</p>
      </c:if>
      <c:if test="${PasswordError eq 'wrongpass'}">
          <p>Password did not match!!</p>
      </c:if>
    </div>
            <div class="container">
            <form:label path="email">Username : </form:label>
            <form:input type="text" path="email" id="email" name="email" placeholder="Enter Username" ></form:input>
            <form:label path="password">Password : </form:label>
            <form:input type="password" path="password" id="password" name="password" placeholder="Enter Password" ></form:input>
            <input type="submit" value="Login" class="button">
            Forgot <a href="#"> password? </a>
        </div>
    </form:form>
</body>
</html>