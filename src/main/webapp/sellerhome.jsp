<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="klu.model.Consumer" %>
<%
Consumer consumer = (Consumer) session.getAttribute("cms");
if (consumer == null) {
    response.sendRedirect("sessionexpiry.html");
    return;
}
if (!"seller".equals(consumer.getUserRole())) {
    response.sendRedirect("forbidden.html");
    return;
}
%>
<%@ include file="sellernav.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Seller Home</title>
    <link rel="stylesheet" href="css/sellerhome.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
    <style>
        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background: #f7f7f7;
            color: #333;
        }

        .dashboard-preview {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
        }

        .hero-section {
            background: #ffcc00;
            padding: 80px 20px;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
        }

        .hero-content h1 {
            font-size: 3rem;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        .hero-content p {
            font-size: 1.2rem;
            line-height: 1.6;
            max-width: 800px;
            margin: auto;
            color: #333;
        }

        .quote-section {
            margin-top: 40px;
            text-align: center;
        }

        .quote-section blockquote {
            font-style: italic;
            font-size: 1.5rem;
            color: #4CAF50;
            padding: 20px;
            border-left: 5px solid #4CAF50;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 8px;
            max-width: 800px;
            margin: auto;
        }

        .stats-summary {
            margin-top: 40px;
            padding: 30px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .stats-summary h2 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        .stats {
            display: flex;
            justify-content: space-around;
            gap: 20px;
            flex-wrap: wrap;
        }

        .stat-item {
            background: #f7f7f7;
            padding: 30px;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            min-width: 200px;
            flex: 1;
            margin: 10px;
            color: #333;
        }

        .stat-item h3 {
            font-size: 1.5rem;
            color: #333;
            margin-bottom: 10px;
        }

        .stat-item p {
            font-size: 2.5rem;
            color: #1e3c72;
        }

        .dashboard-preview .btn {
            display: inline-block;
            padding: 12px 25px;
            font-size: 1.2rem;
            background-color: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 8px;
            margin-top: 25px;
            transition: background 0.3s ease, transform 0.3s ease;
        }

        .dashboard-preview .btn:hover {
            background-color: #388e3c;
            transform: translateY(-3px);
        }

        footer {
            text-align: center;
            padding: 20px;
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>
   
    <section class="dashboard-preview">
        <div class="hero-section">
            <div class="hero-content">
                <h1>Welcome, <%= consumer.getName() %>!</h1>
                <p>Your journey to success starts here. Explore endless opportunities to showcase your creativity and connect with buyers from around the world. Manage your art with ease, track sales, and watch your business grow. We provide all the tools you need to thrive in the art marketplace, so you can focus on what truly matters—creating stunning works of art.</p>
                <a href="myarts" class="btn">Manage Artworks</a>
            </div>
        </div>

        <div class="quote-section">
            <blockquote>
                "Art is not freedom from discipline, but disciplined freedom." – John F. Kennedy
            </blockquote>
        </div>

        <div class="stats-summary">
            <h2>Quick Stats</h2>
            <div class="stats">
                <div class="stat-item">
                    <h3>Artworks Listed</h3>
                    <p>120</p>
                </div>
                <div class="stat-item">
                    <h3>Orders Received</h3>
                    <p>45</p>
                </div>
                <div class="stat-item">
                    <h3>Total Sales</h3>
                    <p>$3,200</p>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <p>&copy; 2024 Your Art Marketplace. All Rights Reserved.</p>
    </footer>

</body>
</html>
