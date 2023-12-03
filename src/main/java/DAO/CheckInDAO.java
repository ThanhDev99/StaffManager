package DAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CheckInDAO {
    private List<String> onlineList;
    public CheckInDAO() {
        onlineList = new LinkedList<>();
    }
    public List<String> getCheckin(String staffId) {
        onlineList.add("Tất cả");
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT ID_STAFF FROM ACCOUNT WHERE IS_CHECKIN = 1 AND ID_STAFF <> ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, staffId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                onlineList.add(resultSet.getString("ID_STAFF"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return onlineList;
    }
    public void setCheckIn(String staffId) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "UPDATE ACCOUNT SET IS_CHECKIN = 1 WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, staffId);

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setCheckOut(String staffId) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "UPDATE ACCOUNT SET IS_CHECKIN = 0 WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, staffId);

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
