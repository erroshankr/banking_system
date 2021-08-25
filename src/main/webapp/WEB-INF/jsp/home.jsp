<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <style>
    #fixedbutton1 {
         position:absolute;
         top: 15px;
         left: 16px;

    }
      #fixedbutton2 {
             position:absolute;
              top: 70px;
              left: 16px;

        }
     body, html {
       height: 100%;
       overflow:hidden;
     }

     .bg {
       /* The image used */

       background-image: url("https://c8.alamy.com/comp/PH8RMP/vector-flat-illustration-bank-building-on-a-white-background-bank-financing-money-exchange-financial-services-atm-giving-out-money-PH8RMP.jpg");


       /* Full height */
       height: 102vh;
       width:99%;
       position:absolute;


       /* Center and scale the image nicely */
       background-position: center;
       background-repeat: no-repeat;
       background-size: cover;
     }
       .button {
                background-color: black;
                color: azure;
                cursor: pointer;
                width: 8rem;
                height: 3rem;
                border-radius: 8px;
                font-family: cursive;
        }
        #button{
            background-color: red;
                color: azure;

                cursor: pointer;
                width: 8rem;
                height: 3rem;
                border-radius: 8px;
                font-family: cursive;
        }
        .button:hover,#button:hover{
            background-color: rgb(49, 44, 44);
            font-weight: bold;
        }
        h2{
          text-align:center;
          font-size:40px;
          color:black;
          }

    </style>

</head>
<body>
<div class="bg">
<h2>Welcome To Grip Bank</h2>
<a href="/userDetails">
    <button class="button" id="fixedbutton1">View Customers</button>
</a>

<a href="/txnHistory">
    <button class="button"id="fixedbutton2">View transaction</button>
</a>
</div>
</body>


