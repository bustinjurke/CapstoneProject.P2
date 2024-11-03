import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FieldDAO {
	
	 public boolean addField(Field field) {
	        String query = "INSERT INTO Fields (user_id, location, size) VALUES (?, ?, ?)";
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, field.getUserId());
	            stmt.setString(2, field.getLocation());
	            stmt.setDouble(3, field.getSize());
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	
	public boolean Field getFieldId(int fieldId) {
		Field field = null;
		String query = "SELECT * FROM Fields WHERE field_id = ?";
		
		try (Connection conn = DatabaseUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, fieldId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				field = new Field(rs.getInt("field_id"),
								rs.getInt("user_id"),
								rs.getString("location"),
								rs.getDouble("size"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return field;
	}
	public List<Field> getFieldsByUserId(int userId) {
        List<Field> fields = new ArrayList<>();
        String query = "SELECT * FROM Fields WHERE user_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Field field = new Field(
                    rs.getInt("field_id"),
                    rs.getInt("user_id"),
                    rs.getString("location"),
                    rs.getDouble("size")
                );
                fields.add(field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fields;
    }
	 public boolean updateField(Field field) {
	        String query = "UPDATE Fields SET location = ?, size = ? WHERE field_id = ?";
	        
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, field.getLocation());
	            stmt.setDouble(2, field.getSize());
	            stmt.setInt(3, field.getFieldId());
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 public boolean deleteField(int fieldId) {
	        String query = "DELETE FROM Fields WHERE field_id = ?";
	        
	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, fieldId);
	            stmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}
