<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="klu.model.ArtFair" %>
<%@ include file="sellernav.jsp" %>
<%@ page import="klu.model.Consumer" %>

<%
    Consumer consumer = (Consumer) session.getAttribute("cms");
    if (consumer == null) {
        response.sendRedirect("sessionexpiry.html");
        return;
    }

    @SuppressWarnings("unchecked")
    List<ArtFair> alltours = (List<ArtFair>) request.getAttribute("alltours");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Art Tours</title>
    <link rel="stylesheet" href="css/tourexplore.css">
</head>
<body>
    <div class="container">
        <h1>Art Tours</h1>
        <%
            if (alltours != null && !alltours.isEmpty()) {
        %>
        <div class="card-container">
            <%
                for (ArtFair tour : alltours) {
            %>
            <div class="card">
                <img src="<%= tour.getTimage() %>" alt="Tour Image">
                <h3><%= tour.getName() %></h3>
                <p><strong>Description:</strong> <%= tour.getTdescription() %></p>
                <p><strong>Start Date:</strong> <%= tour.getStartDate() %></p>
                <p><strong>End Date:</strong> <%= tour.getEndDate() %></p>
                <p><strong>Location:</strong> <%= tour.getLocation() %></p>
                <p><strong>Capacity:</strong> <%= tour.getCapacity() %></p>
                <p><strong>Entry Fee:</strong> ₹<%= tour.getEntryFee() %></p>
                <p><strong>Theme:</strong> <%= tour.getTheme() %></p>
                <p><strong>Curator ID:</strong> <%= tour.getCuratorId() %></p>
                <!-- Register Button -->
                <a href="registerForTour.jsp?tourId=<%= tour.getTourid() %>&name=<%= tour.getName() %>&description=<%= tour.getTdescription() %>&startDate=<%= tour.getStartDate() %>&endDate=<%= tour.getEndDate() %>&location=<%= tour.getLocation() %>&capacity=<%= tour.getCapacity() %>&entryFee=<%= tour.getEntryFee() %>&theme=<%= tour.getTheme() %>&curatorId=<%= tour.getCuratorId() %>" 
                   class="btn-register">Register for Tour</a>
            </div>
            <%
                }
            %>
        </div>
        <%
            } else {
        %>
        <p>No art tours available.</p>
        <%
            }
        %>
    </div>
</body>
</html>
