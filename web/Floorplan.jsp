<%-- 
    Document   : Floorplan
    Created on : 11-04-2016, 12:18:57
    Author     : Oliver
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <img src="ImageController" alt="some_text">
        <h1>You have not yet uploaded any floorplan for this building. Do you want to upload it now?</h1>
        <form action="ImageController" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="do_this" value="setimage"/>
            <input type="file" name="file" size="50"/>
            <input type="submit">
        </form>

    </body>
</html>
