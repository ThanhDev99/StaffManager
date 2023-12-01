package DAO;

import Model.Request;
import Model.ShareData;
import Model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDAO {
    private List<Task> taskList = taskList = new ArrayList<>();
    public TaskDAO() {

    }
    public List<Task> getTaskByStaffId() {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "EXECUTE PR1_FIND_TASK_BY_STAFF_ID ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, ShareData.account.getStaffId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task t = new Task().setId(resultSet.getInt("ID_TASK"))
                                    .setName(resultSet.getString("NAME_TASK"))
                                    .setStatus(resultSet.getString("STATUS"))
                                    .setDepartment(resultSet.getString("ID_DEPARTMENT"))
                                    .setStaff(ShareData.account.getStaffId())
                                    .setDescription(resultSet.getString("DESCRIPTION"))
                                    .setRegDate(resultSet.getString("REG_DATE"))
                                    .setDeadline(resultSet.getString("DEADLINE"));
                taskList.add(t);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            taskList = null;
        }

        return taskList;
    }
    public Boolean updateTaskS(Task task) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "EXECUTE PR1_UPDATE_STAFF_TASK ?, ?, ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setInt(1, task.getId());
            preparedStatement.setString(2, ShareData.account.getStaffId());
            preparedStatement.setString(3, task.getStatus());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Boolean deleteTaskById(Integer id) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "DELETE TASK WHERE ID_TASK = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Map<String, String> getStaffByManager(String id) {
        Map<String, String> map = new HashMap<>();

        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT ID_STAFF, FULLNAME " +
                        "FROM STAFF S JOIN DEPARTMENT D ON S.DEPARTMENT = D.ID_DEPARTMENT " +
                        "WHERE D.MANAGER = ? AND ID_STAFF <> ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                map.put(resultSet.getString("ID_STAFF"), resultSet.getString("FULLNAME"));
            }

            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Boolean addTask(Task task) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "EXECUTE PR1_ADD_TASK ?, ?, ?, ?, ?, ?, ?, ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setInt(1, task.getId());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDepartment());
            preparedStatement.setString(4, task.getRegDate());
            preparedStatement.setString(5, task.getDeadline());
            preparedStatement.setString(6, task.getStaff());
            preparedStatement.setString(7, task.getDescription());
            preparedStatement.setString(8, task.getStatus());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public List<Task> getTaskByManager() {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "EXECUTE PR1_FIND_TASK_BY_MANAGER ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, ShareData.account.getStaffId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task t = new Task().setId(resultSet.getInt("ID_TASK"))
                        .setName(resultSet.getString("NAME_TASK"))
                        .setStatus(resultSet.getString("STATUS"))
                        .setDepartment(resultSet.getString("ID_DEPARTMENT"))
                        .setStaff(resultSet.getString("ID_STAFF"))
                        .setDescription(resultSet.getString("DESCRIPTION"))
                        .setRegDate(resultSet.getString("REG_DATE"))
                        .setDeadline(resultSet.getString("DEADLINE"));
                taskList.add(t);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            taskList = null;
        }
        return taskList;
    }
}
