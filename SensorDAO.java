import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorDAO {

 
    public boolean addSensor(Sensor sensor) {
        String query = "INSERT INTO Sensors (field_id, type, data, timestamp) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sensor.getFieldId());
            stmt.setString(2, sensor.getType());
            stmt.setFloat(3, sensor.getData());
            stmt.setTimestamp(4, sensor.getTimestamp());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Sensor getSensorById(int sensorId) {
        Sensor sensor = null;
        String query = "SELECT * FROM Sensors WHERE sensor_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sensorId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sensor = new Sensor(
                    rs.getInt("sensor_id"),
                    rs.getInt("field_id"),
                    rs.getString("type"),
                    rs.getFloat("data"),
                    rs.getTimestamp("timestamp")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensor;
    }


    public List<Sensor> getSensorsByFieldId(int fieldId) {
        List<Sensor> sensors = new ArrayList<>();
        String query = "SELECT * FROM Sensors WHERE field_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, fieldId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sensor sensor = new Sensor(
                    rs.getInt("sensor_id"),
                    rs.getInt("field_id"),
                    rs.getString("type"),
                    rs.getFloat("data"),
                    rs.getTimestamp("timestamp")
                );
                sensors.add(sensor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sensors;
    }


    public boolean updateSensorData(Sensor sensor) {
        String query = "UPDATE Sensors SET data = ?, timestamp = ? WHERE sensor_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setFloat(1, sensor.getData());
            stmt.setTimestamp(2, sensor.getTimestamp());
            stmt.setInt(3, sensor.getSensorId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteSensor(int sensorId) {
        String query = "DELETE FROM Sensors WHERE sensor_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sensorId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

