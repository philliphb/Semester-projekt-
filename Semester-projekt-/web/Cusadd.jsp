<%-- 
    Document   : Cusadd
    Created on : Mar 30, 2016, 11:14:38 AM
    Author     : Thesoap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Please enter building infomation</h1>
        <form action="loginctrl" method="POST">
            Name of building:<br>
            <input type="text" name="name"><br>
            Address:<br>
            <input type="text" name="address"><br>
            Zipcode:<br>
            <input type="text" name="zipcode"><br>
            City:<br>
            <input type="text" name="City"><br>
            Parcel no.:<br>
            <input type="text" name="parcel"><br>
            Size of building(m^2):<br>
            <input type="text" name="size"><br>
            Building year:<br>
            <input type="text" name="year"><br>
            <input type="submit" value="add" name="dothis">
        </form>
    </body>
</html>
