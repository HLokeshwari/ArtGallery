<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="klu.model.Consumer" %>
<%
    Consumer consumer = (Consumer) session.getAttribute("cms");
    if (consumer == null) {
        response.sendRedirect("sessionexpiry.html");
        return; 
    } 
    if (!"admin".equals(consumer.getUserRole())) {
        response.sendRedirect("forbidden.html"); // Redirect to forbidden page
        return;
    }
%>
<%@ include file="adminnav.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
    <link rel="stylesheet" href="css/adminhome.css"/>

 </head>
<body>
<section class="dashboard-preview">
        <div class="hero-section">
            <div class="hero-content">
                <h1>Welcome, <%= consumer.getName() %>!</h1>
            </div>
        </div>



    </section></body>
</html>