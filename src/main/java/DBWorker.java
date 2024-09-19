import javax.xml.stream.FactoryConfigurationError;
import java.sql.*;

public class DBWorker {
    private final String HOST = "jdbc:postgresql://localhost:5432/testdb";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "Cat!jav@!";

    private Connection connection;

    public DBWorker() {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
