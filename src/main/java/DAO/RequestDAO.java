package DAO;

import Model.Request;
import Model.ShareData;
import Model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    private List<Request> list = new ArrayList<>();
    public List<Request> getAllRequestById() {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT ID_REQUEST, DEPARTMENT_SEND, DEPARTMENT_RECEIVE, SEND_DATE, STATUS, CONTENT " +
                    "FROM REQUEST " +
                    "WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, ShareData.account.getStaffId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Request r = new Request().setId(resultSet.getInt("ID_REQUEST"))
                                .setDateSend(resultSet.getString("SEND_DATE"))
                                        .setDepartmentSend(resultSet.getString("DEPARTMENT_SEND"))
                                                .setDepartmentReceive(resultSet.getString("DEPARTMENT_RECEIVE"))
                                                        .setStatus(resultSet.getString("STATUS"))
                                                                .setContent(resultSet.getString("CONTENT"))
                                                                        .setStaff(ShareData.account.getStaffId());

                list.add(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            list = null;
        }
        return list;
    }
    public Boolean addRequest(Request request) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "INSERT INTO REQUEST(ID_REQUEST, ID_STAFF, DEPARTMENT_SEND, DEPARTMENT_RECEIVE, SEND_DATE, STATUS, CONTENT) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setInt(1, request.getId());
            preparedStatement.setString(2, request.getStaff());
            preparedStatement.setString(3, request.getDepartmentSend());
            preparedStatement.setString(4, request.getDepartmentReceive());
            preparedStatement.setString(5, request.getDateSend());
            preparedStatement.setString(6, request.getStatus());
            preparedStatement.setString(7, request.getContent());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public List<Integer> getAllRequest() {
        List<Integer> listId = new ArrayList<>();
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT ID_REQUEST FROM REQUEST";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listId.add(resultSet.getInt("ID_REQUEST"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            listId = null;
        }
        return listId;
    }
    public List<Request> getAllRequestByManager() {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT ID_REQUEST, DEPARTMENT_SEND, DEPARTMENT_RECEIVE, SEND_DATE, STATUS, CONTENT " +
                    "FROM REQUEST " +
                    "WHERE DEPARTMENT_RECEIVE = ? AND STATUS = 'SENT'";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, ShareData.account.getDepartment());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Request r = new Request().setId(resultSet.getInt("ID_REQUEST"))
                        .setDateSend(resultSet.getString("SEND_DATE"))
                        .setDepartmentSend(resultSet.getString("DEPARTMENT_SEND"))
                        .setDepartmentReceive(resultSet.getString("DEPARTMENT_RECEIVE"))
                        .setStatus(resultSet.getString("STATUS"))
                        .setContent(resultSet.getString("CONTENT"))
                        .setStaff(ShareData.account.getStaffId());

                list.add(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            list = null;
        }
        return list;
    }
    public Boolean updateStatus(Request request) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "UPDATE REQUEST SET STATUS = ? WHERE ID_REQUEST = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, ShareData.account.getDepartment());
            preparedStatement.setString(2, ShareData.account.getDepartment());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
