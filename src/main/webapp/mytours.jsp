<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="klu.model.ArtFair" %>
<%@ include file="curatornav.jsp" %>
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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Tours</title>
    <link rel="stylesheet" href="css/mytours.css">
</head>
<body>
    <%
        List<ArtFair> toursList = (List<ArtFair>) request.getAttribute("toursList");

        if (toursList != null && !toursList.isEmpty()) {
            for (ArtFair tour : toursList) {
                Long tourId = tour.getTourid();
                String tourName = tour.getName();
                String tourDescription = tour.getTdescription();
                String startDate = tour.getStartDate();
                String endDate = tour.getEndDate();
                String location = tour.getLocation();
                String capacity = String.valueOf(tour.getCapacity());
                String entryFee = String.valueOf(tour.getEntryFee());
                String theme = tour.getTheme();
                String timage = tour.getTimage();
    %>
                <div class="card">
                    <div class="card-image">
                        <img src="<%= timage %>" alt="<%= tourName %>"/>
                    </div>
                    <div class="card-details">
                        <h3><%= tourName %></h3>
                        <p><strong>Description:</strong> <%= tourDescription %></p>
                        <p><strong>Start Date:</strong> <%= startDate %></p>
                        <p><strong>End Date:</strong> <%= endDate %></p>
                        <p><strong>Location:</strong> <%= location %></p>
                        <p><strong>Capacity:</strong> <%= capacity %></p>
                        <p><strong>Entry Fee:</strong> <%= entryFee %></p>
                        <p><strong>Theme:</strong> <%= theme %></p>
                        <div class="card-actions">
                            <a href="updatealltours?tourId=<%= tourId %>&tourName=<%= tourName %>&tourDescription=<%= tourDescription %>&startDate=<%= startDate %>&endDate=<%= endDate %>&location=<%= location %>&capacity=<%= capacity %>&entryFee=<%= entryFee %>&theme=<%= theme %>" class="button update-button">Update</a>
                            <a href="#" class="button delete-button" onclick="openDeleteModal('<%= tourId %>'); return false;">Delete</a>

                            <div id="deleteModal" class="modal" style="display:none;">
                                <div class="modal-content">
                                    <h2>Confirm Deletion</h2>
                                    <p>Are you sure you want to delete this tour?</p>
                                    <form id="deleteForm" action="deletealltours" method="POST">
                                        <input type="hidden" name="tourId" id="tourIdInput" value=""/>
                                        <button type="button" onclick="closeDeleteModal()">Cancel</button>
                                        <button type="submit">Confirm Delete</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    <%
            }
        } else {
    %>
        <div class="no-tours">
            <p>No tours found.</p>
        </div>
    <%
        }
    %>

    <script>
    function openDeleteModal(tourId) {
        document.getElementById('tourIdInput').value = tourId;
        document.getElementById('deleteModal').style.display = 'flex';
    }

    function closeDeleteModal() {
        document.getElementById('deleteModal').style.display = 'none';
    }

    </script>
</body>
</html>
