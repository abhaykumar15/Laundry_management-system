package Controller;

import DAO.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Locale;

/**
 * Simple keyword-based chatbot for laundry queries
 */
@WebServlet(name = "ChatBotServlet", urlPatterns = {"/ChatBotServlet"})
public class ChatBotServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userMessage = (request.getParameter("message") == null) ? "" : request.getParameter("message").toLowerCase(Locale.ROOT);
        String botReply = "I'm not sure I understand. You can ask me about laundry status, prices, or best day.";

        if (containsAny(userMessage, new String[]{"status", "progress", "track", "update", "check"})) {
            botReply = "You can check your laundry status in the 'Pending Orders' section of your dashboard.";
        } else if (containsAny(userMessage, new String[]{"price", "cost", "rate", "charges", "fee", "how much"})) {
            botReply = "Free for first 10 washes. Standard Wash: ₹50 | Express Wash: ₹80.";
        } else if (containsAny(userMessage, new String[]{"book", "schedule", "reserve", "order", "make"})) {
            botReply = "To book a laundry order, click on the 'Book Laundry' button on your dashboard.";
        } else if (containsAny(userMessage, new String[]{"best day", "less crowded", "least crowded", "less busy", "quiet day"})) {
            botReply = getLeastCrowdedDay();
        }

        response.setContentType("text/plain");
        response.getWriter().write(botReply);
    }

    private boolean containsAny(String input, String[] keywords) {
        if (input == null || input.isEmpty()) return false;
        for (String k : keywords) {
            if (input.contains(k)) return true;
        }
        return false;
    }

    private String getLeastCrowdedDay() {
        String sql = "SELECT TO_CHAR(booking_date, 'Day') AS weekday, COUNT(*) AS total_orders " +
                     "FROM laundry_orders " +
                     "WHERE booking_date >= CURRENT_DATE AND booking_date < CURRENT_DATE + INTERVAL '7 days' " +
                     "GROUP BY TO_CHAR(booking_date, 'Day') " +
                     "ORDER BY total_orders ASC LIMIT 1";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String day = rs.getString("weekday").trim();
                int count = rs.getInt("total_orders");
                return "Based on recent bookings, " + day + " is the least crowded day with " + count + " orders.";
            } else {
                return "No upcoming bookings yet — any day is good to pick!";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, I couldn’t fetch the data right now.";
        }
    }
}
