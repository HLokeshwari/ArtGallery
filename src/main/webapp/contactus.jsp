<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <link rel="stylesheet" href="css/contactus.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        /* Modal styling */
        .modal {
            display: none;
            position: fixed;
            z-index: 100;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.6);
            justify-content: center;
            align-items: center;
            animation: fadeIn 0.4s ease-in-out;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        .modal-content {
            background-color: #fff;
            padding: 30px 20px;
            border-radius: 12px;
            width: 350px;
            max-width: 90%;
            text-align: center;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            transform: scale(0.7);
            animation: scaleUp 0.3s ease-in-out forwards;
        }
        @keyframes scaleUp {
            from { transform: scale(0.7); }
            to { transform: scale(1); }
        }

        .modal-content h3 {
            margin: 0;
            font-size: 1.4em;
            color: #333;
        }

        .modal-content p {
            margin: 20px 0;
            color: #555;
            font-size: 1.1em;
        }

        .modal-close {
            background-color: #4CAF50;
            color: white;
            padding: 10px 25px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s;
        }
        .modal-close:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function displayMessage(event) {
            event.preventDefault();
            document.getElementById("modal").style.display = "flex";
            event.target.reset();
        }

        function closeModal() {
            document.getElementById("modal").style.display = "none";
        }
    </script>
</head>

<header class="main-header">
    <h1 style="color: white;">The Art Spectrum</h1>
    <nav class="nav-links">
        <a href="/">Home</a>
        <a href="login">Login</a>
    </nav>
</header>

<body>
    <div class="container">
        <div class="form-container">
            <div class="left-container">
                <div class="left-inner-container">
                    <h2>Let's Chat</h2>
                    <p>Whether you have a question, want to start a project, or simply want to connect.</p>
                    <br>
                    <p>Feel free to send me a message in the contact form.</p>
                </div>
            </div>

            <div class="right-container">
                <div class="right-inner-container">
                    <form action="#" onsubmit="displayMessage(event)">
                        <h2 class="lg-view">Contact</h2>
                        <h2 class="sm-view">Let's Chat</h2>
                        <p>* Required</p>
                        <input type="text" placeholder="Name *" required />
                        <input type="email" placeholder="Email *" required />
                        <input type="text" placeholder="Company" />
                        <input type="tel" placeholder="Phone" />
                        <textarea rows="4" placeholder="Message"></textarea>
                        <button type="submit">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Popup -->
    <div id="modal" class="modal">
        <div class="modal-content">
            <h3>Thank You!</h3>
            <p>Thank you for contacting us! Our support team is on it and will follow up with you as soon as possible.</p>
            <button class="modal-close" onclick="closeModal()">OK</button>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2024 The Art Spectrum. All rights reserved.</p>
    </footer>
</body>
</html>
