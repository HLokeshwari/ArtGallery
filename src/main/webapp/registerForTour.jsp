<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="sellernav.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register for Art Tour</title>
    <link rel="stylesheet" href="css/registerfortour.css">
</head>
<body>
    <div class="container">
        <h1>Register for Art Tour</h1>
        <form action="registerTourController" method="post">
            <!-- Display Tour Details -->
            <label for="tourId">Tour ID:</label>
            <input type="text" id="tourId" name="tourId" value="<%= request.getParameter("tourId") %>" readonly>
            
            <label for="name">Tour Name:</label>
            <input type="text" id="name" name="name" value="<%= request.getParameter("name") %>" readonly>
            
            <label for="description">Description:</label>
            <textarea id="description" name="description" readonly><%= request.getParameter("description") %></textarea>
            
            <label for="startDate">Start Date:</label>
            <input type="text" id="startDate" name="startDate" value="<%= request.getParameter("startDate") %>" readonly>
            
            <label for="endDate">End Date:</label>
            <input type="text" id="endDate" name="endDate" value="<%= request.getParameter("endDate") %>" readonly>
            
            <label for="location">Location:</label>
            <input type="text" id="location" name="location" value="<%= request.getParameter("location") %>" readonly>
            
            <label for="entryFee">Entry Fee:</label>
            <input type="text" id="entryFee" name="entryFee" value="<%= request.getParameter("entryFee") %>" readonly>
            
            <label for="theme">Theme:</label>
            <input type="text" id="theme" name="theme" value="<%= request.getParameter("theme") %>" readonly>
            
            <label for="curatorId">Curator ID:</label>
            <input type="text" id="curatorId" name="curatorId" value="<%= request.getParameter("curatorId") %>" readonly>

            <!-- Artist Details -->
            <label for="artistName">Artist Name:</label>
            <input type="text" id="artistName" name="artistName" required>
            
            <label for="artistEmail">Artist Email:</label>
            <input type="email" id="artistEmail" name="artistEmail" required>
            
            <label for="artistPhone">Artist Phone:</label>
            <input type="tel" id="artistPhone" name="artistPhone" required>
            
            <button type="submit" class="btn-submit">Submit</button>
        </form>
    </div>
</body>
</html>
