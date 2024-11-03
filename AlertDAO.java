
	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;

	public class AlertDAO {

	    public boolean addAlert(Alert alert) {
	        String query = "INSERT INTO Alerts (user_id, sensor_id, `condition`, trigger_status) VALUES (?, ?, ?, ?)";
	        
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, alert.getUserId());
	            stmt.setInt(2, alert.getSensorId());
	            stmt.setString(3, alert.getCondition());
	            stmt.setBoolean(4, alert.isTriggerStatus());
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public Alert getAlertById(int alertId) {
	        Alert alert = null;
	        String query = "SELECT * FROM Alerts WHERE alert_id = ?";
	        
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, alertId);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                alert = new Alert(
	                    rs.getInt("alert_id"),
	                    rs.getInt("user_id"),
	                    rs.getInt("sensor_id"),
	                    rs.getString("alert_condition"),
	                    rs.getBoolean("trigger_status")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return alert;
	    }

	    public List<Alert> getAlertsByUserId(int userId) {
	        List<Alert> alerts = new ArrayList<>();
	        String query = "SELECT * FROM Alerts WHERE user_id = ?";
	        
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, userId);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Alert alert = new Alert(
	                    rs.getInt("alert_id"),
	                    rs.getInt("user_id"),
	                    rs.getInt("sensor_id"),
	                    rs.getString("alert_condition"),
	                    rs.getBoolean("trigger_status")
	                );
	                alerts.add(alert);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return alerts;
	    }

	    // Method to update an alert's condition and trigger status
	    public boolean updateAlert(Alert alert) {
	        String query = "UPDATE Alerts SET alert_condition = ?, trigger_status = ? WHERE alert_id = ?";
	        
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, alert.getCondition());
	            stmt.setBoolean(2, alert.isTriggerStatus());
	            stmt.setInt(3, alert.getAlertId());
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    // Method to delete an alert by alert ID
	    public boolean deleteAlert(int alertId) {
	        String query = "DELETE FROM Alerts WHERE alert_id = ?";
	        
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, alertId);
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}

}
