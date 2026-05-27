<%@ page import="model.LaundaryOrder" %>
<%@ page import="DAO.LaundaryDao" %>
<%@ page import="model.Student" %>
<%@ page import="model.Hostel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%
    // 🔹 Check login session
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
        response.sendRedirect("StudentLogin.jsp");
        return;
    }

    // 🔹 Prevent browser caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    Hostel hostel = student.getHostel();

    // 🔹 Use DAO safely
    LaundaryDao dao = new LaundaryDao();
    int yearlyLimit = 10;

    int remainingWashes = dao.getRemainingFreeWashes(student.getId(), yearlyLimit);
    int totalOrders = dao.getTotalCompleted(student.getId());
    List<LaundaryOrder> pendingOrders = dao.getPendingOrders(student.getId());
    List<LaundaryOrder> orderHistory = dao.getOrderHistory(student.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/js/all.min.js"></script>
    <style>
        body {
            background: linear-gradient(to right, #e3f2fd, #ffffff);
            font-family: 'Poppins', sans-serif;
        }
        .navbar {
            background-color: #007bff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .navbar-brand, .nav-link {
            color: #fff !important;
            font-weight: 500;
        }
        .profile-card {
            border-radius: 20px;
            background: #fff;
            box-shadow: 0 6px 18px rgba(0,0,0,0.1);
            padding: 40px 30px;
            margin-bottom: 40px;
        }
        .card { box-shadow: 0 4px 10px rgba(0,0,0,0.1); border-radius: 10px; margin-bottom: 25px; }
        .stat-value { font-size: 2rem; font-weight: bold; color: #007bff; }
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark">
  <div class="container-fluid px-4">
    <a class="navbar-brand" href="#"><i class="fa-solid fa-shirt"></i> Laundry Dashboard</a>
    <div class="d-flex ms-auto">
      <a href="logout.jsp" class="btn btn-light btn-sm">
        <i class="fa-solid fa-right-from-bracket text-danger"></i> Logout
      </a>
    </div>
  </div>
</nav>

<div class="container mt-5">
  <div class="profile-card text-center mx-auto col-md-8">
      <h2>Welcome, <%= student.getName() %></h2>
      <p><%= student.getEmail() %></p>
      <p><b>Hostel:</b> <%= (hostel != null ? hostel.getName() : "N/A") %></p>
  </div>

  <div class="text-center mb-4">
    <a href="bookWash.jsp" class="btn btn-primary">Book New Wash</a>
  </div>

  <!-- Pending Orders -->
  <div class="card p-3">
    <h3>Pending Orders</h3>
    <table class="table table-bordered">
      <tr><th>ID</th><th>Date</th><th>Status</th><th>Accepted By</th><th>QR</th></tr>
      <% for (LaundaryOrder o : pendingOrders) { %>
        <tr>
          <td><%= o.getOrderId() %></td>
          <td><%= o.getBookingDate() %></td>
          <td><%= o.getStatus() %></td>
          <td><%= o.getEmployeeName() %></td>
          <td><img src="data:image/png;base64,<%= o.getQrBase64() %>" width="60"/></td>
        </tr>
      <% } %>
    </table>
  </div>

  <!-- Stats -->
  <div class="card text-center p-3">
    <h4>Remaining Free Washes: <%= remainingWashes %></h4>
    <h4>Total Completed Orders: <%= totalOrders %></h4>
  </div>

  <!-- Order History -->
  <div class="card p-3">
    <h3>Wash History</h3>
    <table class="table table-bordered">
      <tr><th>ID</th><th>Date</th><th>Status</th><th>Free</th><th>Amount</th><th>Employee</th></tr>
      <% for (LaundaryOrder o : orderHistory) { %>
        <tr>
          <td><%= o.getOrderId() %></td>
          <td><%= o.getBookingDate() %></td>
          <td><%= o.getStatus() %></td>
          <td><%= o.isFree() ? "Yes" : "No" %></td>
          <td><%= o.getAmount() %></td>
          <td><%= o.getEmployeeName() %></td>
        </tr>
      <% } %>
    </table>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
