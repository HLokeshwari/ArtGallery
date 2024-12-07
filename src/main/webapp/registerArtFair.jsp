<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="klu.model.Consumer" %>
<%
    Consumer consumer = (Consumer) session.getAttribute("cms");
    if (consumer == null) {
        response.sendRedirect("sessionexpiry.html");
        return;
    }
    if (!"curator".equals(consumer.getUserRole())) {
        response.sendRedirect("forbidden.html"); // Redirect to forbidden page
        return;
    }
%>
<%@ include file="curatornav.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Curator Tour Registration</title>
    <link rel="stylesheet" href="css/tour.css"> <!-- Link to your external CSS file -->
</head>
<body>

<div class="form-container">
<h2 style="text-align: center;">Art Fair Registration</h2>
<form action="/registerArtFair" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Fair Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="tdescription">Description:</label>
            <textarea id="tdescription" name="tdescription" required></textarea>
        </div>
        <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" required>
        </div>
        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" required>
        </div>
        <div class="form-group">
            <label for="location">Location:</label>
            <input type="text" id="location" name="location" required>
        </div>
        <div class="form-group">
            <label for="capacity">Capacity:</label>
            <input type="number" id="capacity" name="capacity" required>
        </div>
        <div class="form-group">
            <label for="entryFee">Entry Fee:</label>
            <input type="number" step="0.01" id="entryFee" name="entryFee" required>
        </div>
        <div class="form-group">
            <label for="theme">Theme:</label>
            <input type="text" id="theme" name="theme" required>
        </div>
        <div class="form-group">
            <label for="timage">Image Upload:</label>
            <input type="file" id="timage" name="timage" accept=".jpg, .jpeg, .png" required>
        </div>
        <div class="button-group">
            <button type="submit">Register Art Fair</button>
        </div>
    </form>
</div>

</body>
</html>
