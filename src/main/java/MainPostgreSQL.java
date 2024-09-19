import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainPostgreSQL {
    public static void main(String[] args) {
        DBWorker worker = new DBWorker();

//        String query = "SELECT * FROM users";
        String query = "SELECT * FROM users WHERE id=1";

        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // ResultSet - это полная модель ответа нашей таблицы - все колонки со значениями
            // Как же пройтись по всем значениям?
            while (resultSet.next()) { // next изначально на нулевой позиции и будет переходить дальше
//                int id = resultSet.getInt(1);// можно указать название колонки или ее индекс, индекс нумеруется с 1, а не с 0
//                System.out.println(id);
                // можно создать User и с резалтсета получать его поля
                User user = new User();
                user.setId(resultSet.getInt(2));
//                user.setUsername(resultSet.getString(1));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString(3));
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);        }


    }
}
