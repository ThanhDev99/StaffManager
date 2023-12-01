package DAO;

import Model.ShareData;
import Model.Staff;
import Model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffManagerDAO {
    private List<Staff> staffList = new ArrayList<>();
    public StaffManagerDAO() {

    }
    public List<Staff> getAllStaff() {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT * FROM STAFF";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Staff s = new Staff().setId(resultSet.getString("ID_STAFF"))
                        .setName(resultSet.getString("FULLNAME"))
                        .setPhone(resultSet.getString("PHONE"))
                        .setEmail(resultSet.getString("EMAIL"))
                        .setAddress(resultSet.getString("ADDRESS"))
                        .setCountry((resultSet.getString("COUNTRY")))
                        .setDateStart(resultSet.getString("WORK_DAY"))
                        .setDepartment((resultSet.getString("DEPARTMENT")))
                        .setRatedCapacity(resultSet.getString("RATED_CAPACITY"))
                        .setSex(resultSet.getBoolean("SEX"))
                        .setBirthday(resultSet.getString("BIRTHDAY"))
                        .setImage(resultSet.getString("IMAGE"));
                staffList.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            staffList = null;
        }
        return staffList;
    }
    public Boolean deleteStaffById(String id) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "DELETE STAFF WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Boolean updateStaff(Staff staff) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "UPDATE STAFF " +
                    "SET FULLNAME = ?" +
                    ", PHONE = ?" +
                    ", EMAIL = ?" +
                    ", ADDRESS = ?" +
                    ", COUNTRY = ?" +
                    ", WORK_DAY = ?" +
                    ", DEPARTMENT = ?" +
                    ", RATED_CAPACITY = ?" +
                    ", SEX = ?" +
                    ", BIRTHDAY = ?" +
                    ", IMAGE = ? " +
                    "WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getPhone());
            preparedStatement.setString(3, staff.getEmail());
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getCountry());
            preparedStatement.setString(6, staff.getDateStart());
            preparedStatement.setString(7, staff.getDepartment());
            preparedStatement.setString(8, staff.getRatedCapacity());
            preparedStatement.setBoolean(9, staff.getSex());
            preparedStatement.setString(10, staff.getBirthday());
            preparedStatement.setString(11, staff.getImage());
            preparedStatement.setString(12, staff.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Boolean addNewStaff(Staff staff) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "INSERT INTO STAFF(ID_STAFF, FULLNAME, PHONE, EMAIL, ADDRESS, COUNTRY, WORK_DAY, DEPARTMENT, RATED_CAPACITY, SEX, BIRTHDAY, IMAGE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, staff.getPhone());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setString(5, staff.getAddress());
            preparedStatement.setString(6, staff.getCountry());
            preparedStatement.setString(7, staff.getDateStart());
            preparedStatement.setString(8, staff.getDepartment());
            preparedStatement.setString(9, staff.getRatedCapacity());
            preparedStatement.setBoolean(10, staff.getSex());
            preparedStatement.setString(11, staff.getBirthday());
            preparedStatement.setString(12, staff.getImage());
            preparedStatement.setString(1, staff.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
