<%-- 
    Document   : Cusdelete
    Created on : 30-03-2016, 13:49:02
    Author     : philliphbrink
--%>

<%@page import="ServiceLayer.Controller"%>
<%@page import="ServiceLayer.Entity.Customer"%>
<%@page import="DataAccessLayer.DBFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ServiceLayer.Entity.Building"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Buildings</title>
    </head>
    <style>table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;

        }
    </style>
    <% Customer c = (Customer) session.getAttribute("LoggedInCustomer");%>
    <body bgcolor="#33CCFF">
        <h1>Hello <%= c.getUsername()%></h1>
        <p>Here you have an overview of your buildings and are able to delete them</p>
        <table style="width: 100%; background: lightblue;">
            <tr>
                <%--This part is writen by Phillip
                The code is to show the customers building and so that they can delete them
                For them to see their buildings they need user_id--%>
                <th><a href="SortedAdminBuildings.jsp">Building Condition</a></th><th>Building ID</th> <th>Building Status</th><th>Building Type</th><th>Building Year</th><th>Size</th><th>Building Name</th><th>Building Adress</th><th>Floor</th><th>Zipcode</th><th>Firm</th><th> </th><th> </th><th></th>
            </tr>
            <% Controller con = (Controller) session.getAttribute("Controller");
                ArrayList<Building> buildings = con.getAllBuildings();
                for (Building building : buildings) {
                    out.println("<tr>");
                    out.println("<td>" + con.getBuildingCondition(building.getBuilding_id()) + "</td>");
                    out.println("<td>" + building.getBuilding_id() + "</td>");
                    out.print("<td>" + building.getBuilding_status() + "</td>");
                    out.print("<td>" + building.getBuilding_type() + "</td>");
                    out.print("<td>" + building.getBuilding_year() + "</td>");
                    out.print("<td>" + con.getTotalSize(building.getBuilding_id()) + "</td>");
                    out.print("<td>" + building.getBuilding_name() + "</td>");
                    out.print("<td>" + building.getBuilding_adress() + "</td>");
                    out.println("<td>" + con.getAllFloors(building.getBuilding_id()) + "</td>");
                    out.println("<td>" + building.getBuilding_zipcode() + "</td>");
                    out.println("<td>" + building.getBuilding_firm() + "</td>");%>
            <%--This is the delete button--%>
            <td> <form action="DeleteBuildingController" method="POST">
                    <input type="hidden" name="do_this" value="delete"/>
                    <input type="hidden" name="deletebuilding" value="<%=building.getBuilding_id()%>" />
                    <input type="submit" value="Delete" width="200%" size="100%" style="width: 100%; font-size: 110%"/>
                </form>
            </td>
            <%--This button is made by Michael--%>
            <td> <form action="DeleteBuildingController" method="POST">
                    <input type="hidden" name="do_this" value="report"/>
                    <input type="hidden" name="viewreport" value="<%=building.getBuilding_id()%>" />
                    <input type="submit" value="View reports" width="200%" size="100%" style="width: 100%; font-size: 110%"/>
                </form>
            </td>
            <td> <form action="AddBuildingController" method="POST">
                    <input type="hidden" name="do_this" value="viewFloor"/>
                    <input type="hidden" name="floor" value="<%= building.getBuilding_id()%>">
                    <input type="submit" value="View Floors" width="200%" size="100%" style="width: 100%; font-size: 110%"/>
                </form>
            </td>
            <% out.println("</tr>");
                    }
            %>
            <%--The List of buildings and button in the table stops here--%>
        </table>
        <br>
    <td>
        <%--This is to add a building--%>
        <form action="AddBuilding.jsp">
            <input type="submit" value="Add Building" style="width: 30%; height: 30%; font-size: 100%;"/>
        </form>
        <%--This is to return to previous site--%>
    <td> <form action="DeleteBuildingController" method="POST">
            <input type="submit" name="do_this" value="Return" style="width: 30%; height: 30%; font-size: 100%;"/>
        </form>
    </td>
    <% if (request.getAttribute("viewFloorError") != null) {%>
    <%= request.getAttribute("viewFloorError")%>
    <%}%>
</body>
</html>
