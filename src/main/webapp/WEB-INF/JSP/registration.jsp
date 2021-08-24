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
<form:form action="/submitregistration" method="post" modelAttribute="registerForm">
  <div class="container">
  <center>  <h1>Registration Form</h1></center>
  <hr>
  <form:label path="firstName" class="font">First Name:</form:label>
              <form:input type="text" class="" path="firstName" id="firstName" name="firstName" placeholder="Enter first name"></form:input><br>
<form:label path="middleName" class="font">Middle Name:</form:label>
            <form:input type="text" class="" path="middleName" id="middleName" name="middleName" placeholder="Enter middle name"></form:input><br>
<form:label path="lastName" class="font">Last Name:</form:label>
            <form:input type="text" class="recieve" path="lastName" id="lastName" name="lastName" placeholder="Enter last name"></form:input><br>
<div>
<form:label path="email" class="font">Email:</form:label>
            <form:input type="text" class="recieve" path="email" id="email" name="email" placeholder="Enter Your Email"></form:input><br>
</div>

<form:label path="mobileNumber" class="font">Mobile Number:</form:label>
            <form:input type="text" class="recieve" path="mobileNumber" id="mobileNumber" name="mobileNumber" placeholder="Enter Mobile Number"></form:input><br>
<form:label path="dateofbirth" class="font">Date Of Birth:</form:label>
            <form:input type="date" class="recieve" path="dateofbirth" id="dateofbirth" name="dateofbirth" placeholder="Date Of Birth"></form:input><br>
<form:label path="line1" class="font">Line 1:</form:label>
            <form:input type="text" class="line1" path="line1" id="line1" name="line1" placeholder="Line 1"></form:input><br>
<form:label path="line2" class="font">Line 2:</form:label>
            <form:input type="text" class="line2" path="line2" id="line2" name="line2" placeholder="Line 2"></form:input><br>
<form:label path="zipCode" class="font">Zip Code:</form:label>
            <form:input type="text" class="zipCode" path="zipCode" id="zipCode" name="zipCode" placeholder="Zip Code"></form:input><br>
 <form:label path="city" class="font">City:</form:label>
             <form:input type="text" class="zipCode" path="city" id="city" name="city" placeholder="City"></form:input><br>
<form:label path="state" class="font">State:</form:label>
            <form:input type="text" class="zipCode" path="state" id="state" name="state" placeholder="State"></form:input><br>
 <form:label path="country" class="font">Country:</form:label>
             <form:input type="text" class="zipCode" path="country" id="country" name="country" placeholder="Country"></form:input><br>
  <form:label path="password" class="font">Password:</form:label>
              <form:input type="password" class="zipCode" path="password" id="password" name="password" placeholder="Enter Password"></form:input><br>
 <form:label path="reTypePassword" class="font">ReTypePassword:</form:label>
             <form:input type="password" class="zipCode" path="reTypePassword" id="reTypePassword" name="reTypePassword" placeholder="Re-enter Password"></form:input><br>
  <form:label path="gender" class="font">Gender:</form:label>
  <form:radiobutton path="gender" value="MALE" label="Male" />
    <form:radiobutton path="gender" value="FEMALE" label="Female" />
    <form:radiobutton path="gender" value="OTHERS" label="Others" />
    <input type="submit" value="Register" class="button">

</form:form>
</body>
</html>