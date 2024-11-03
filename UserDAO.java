import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public User getUsername(String username) {
		User user = null;
		String query = "SELECT * FROM Users WHERE username = ?";
		
		try (Connection conn = DatabaseUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt("user_id"),
								rs.getString("username"),
								rs.getString("password"),
								rs.getString("role"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean createUser(String username, String password, String role) {
        String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
}	
