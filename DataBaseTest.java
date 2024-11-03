import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseTest {
	public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
