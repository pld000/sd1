package task_1;

import java.sql.*;

public class DatabaseStorage implements Storage {

    @Override()
    public void save(String data) {
        try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement();) {
            statement.executeQuery(prepareData(data));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override()
    public String retrieve(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(makeIdQuery(id));
            return resultSet.toString();
        } catch (SQLException e) {
            return null;
        }
    }

    private String prepareData(String data) {
        return data;
    }

    private String makeIdQuery(int id) {
        return "SELECT data FROM any_table WHERE id = " + id;
    }
}
