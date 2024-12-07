<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="klu.model.Consumer" %>
<%@ include file="curatornav.jsp" %>

<%
    Consumer consumer = (Consumer) session.getAttribute("cms");
    if (consumer == null) {
        response.sendRedirect("sessionexpiry.html");
        return; 
    }
    if (!"curator".equals(consumer.getUserRole())) {
        response.sendRedirect("forbidden.html");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Tour</title>
    <link rel="stylesheet" type="text/css" href="css/updatealltours.css">
    <style>
        /* Inline styles for minimal setup */
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Update Tour Details</h2>
    <form action="updatealltours" method="POST">
        <div class="form-group">
            <label for="tourId">Tour ID:</label>
            <input type="text" id="tourId" name="tourid" value="<%= request.getParameter("tourId") %>" readonly>
        </div>

        <div class="form-group">
            <label for="tourName">Tour Name:</label>
            <input type="text" id="tourName" name="name" value="<%= request.getParameter("tourName") %>">
        </div>

        <div class="form-group">
            <label for="tourDescription">Description:</label>
            <input type="text" id="tourDescription" name="tdescription" value="<%= request.getParameter("tourDescription") %>">
        </div>

        <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="text" id="startDate" name="startdate" value="<%= request.getParameter("startDate") %>">
        </div>

        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="text" id="endDate" name="enddate" value="<%= request.getParameter("endDate") %>">
        </div>

        <div class="form-group">
            <label for="location">Location:</label>
            <input type="text" id="location" name="location" value="<%= request.getParameter("location") %>">
        </div>

        <div class="form-group">
            <label for="capacity">Capacity:</label>
            <input type="number" id="capacity" name="capacity" value="<%= request.getParameter("capacity") %>">
        </div>

        <div class="form-group">
            <label for="entryFee">Entry Fee:</label>
            <input type="text" id="entryFee" name="entryfee" value="<%= request.getParameter("entryFee") %>">
        </div>

        <div class="form-group">
            <label for="theme">Theme:</label>
            <input type="text" id="theme" name="theme" value="<%= request.getParameter("theme") %>">
        </div>

        <button type="submit">Save Changes</button>
    </form>
</div>
</body>
</html>
