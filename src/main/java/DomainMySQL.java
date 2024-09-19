import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;

public class DomainMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER_NAME = "DB_User1";
    private static final String PASSWORD = "Abc12345@@@!";


    private static final String INSERT_NEW = "INSERT INTO dish VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL = "SELECT * FROM dish";

    private static final String DELETE = "DELETE FROM dish WHERE id=?";

    public static void main(String[] args) {
//        try {
//            Driver driver = new com.mysql.cj.jdbc.Driver(); // название класса драйвера не менять - другие не обслуживаются
//            DriverManager.registerDriver(driver);
//        } catch (SQLException e) {
//            System.out.println("Не удалось загрузить класс драйвера!");
//            e.printStackTrace();
//        }
//
//        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
//             Statement statement = connection.createStatement()) {
////            System.out.println(connection.isClosed()); // должны получить false
//            // универсальный метод - позволяет выполнять все запросы и получить даже несколько resultset
//            statement.execute("INSERT INTO animal (anim_name, anim_desc) VALUES ('Tom', 'cat');");
//            // следующим методом можно выполнять insert, update,delete
//            // получать данные нельзя
//            // данный метод также возвращает количество записей, в которые он внес изменения
//            int changes = statement.executeUpdate("UPDATE animal SET anim_name='new name' where id=1;");
//            System.out.println(changes);
//            // следующий метод позволяет получить resultset
//            // этим метдом можно выполнять только селекты
//            ResultSet rs = statement.executeQuery("SELECT * FROM animal");
//            // Если нужно выполнить сразу несколько команд, то их можно поместить в пакет - пакетная обработка
//            statement.addBatch("INSERT INTO animal (anim_name, anim_desc) VALUES ('Jack', 'dog');");
//            statement.addBatch("INSERT INTO animal (anim_name, anim_desc) VALUES ('Fred', 'horse');");
//            statement.addBatch("INSERT INTO animal (anim_name, anim_desc) VALUES ('Rose', 'cow');");
//
//            statement.executeBatch();
//
//            statement.clearBatch();
//
//            // этим метдом можно проверить, закрыто ли соединение
//            boolean status = statement.isClosed();
//            System.out.println(status);
//
//            // получить соединение с базой данных
//            statement.getConnection();
//
//            // закрыть соединение
//            statement.close();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Connection connection1 = DriverManager.getConnection(URL, USER_NAME, PASSWORD);


            preparedStatement = connection1.prepareStatement(INSERT_NEW);
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2, "Inserted title");
            preparedStatement.setString(3, "Inserted desc");
            preparedStatement.setFloat(4, 0.2f);
            preparedStatement.setBoolean(5, true);
            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
            preparedStatement.setBlob(7, new FileInputStream("smile.png"));
            preparedStatement.executeUpdate();



            preparedStatement = connection1.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                float rating = resultSet.getFloat("rating");
                boolean published = resultSet.getBoolean("published");
                Date date = resultSet.getDate("created");
                byte[] icon = resultSet.getBytes("icon");

                System.out.println("id: " + id + ", title: " + title + ", desc: " + desc + ", rating: " + rating + "" +
                        ", published: " + published + ", date: " + date + ", icon length: " + icon.length);

            }

            System.out.println("---------------------------------------------------------");

            preparedStatement = connection1.prepareStatement(DELETE);
            preparedStatement.setInt(1,2);
            preparedStatement.executeUpdate();


            preparedStatement = connection1.prepareStatement(GET_ALL);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()) {
                int id = resultSet1.getInt("id");
                String title = resultSet1.getString("title");
                String desc = resultSet1.getString("description");
                float rating = resultSet1.getFloat("rating");
                boolean published = resultSet1.getBoolean("published");
                Date date = resultSet1.getDate("created");
                byte[] icon = resultSet1.getBytes("icon");

                System.out.println("id: " + id + ", title: " + title + ", desc: " + desc + ", rating: " + rating + "" +
                        ", published: " + published + ", date: " + date + ", icon length: " + icon.length);

            }


        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
