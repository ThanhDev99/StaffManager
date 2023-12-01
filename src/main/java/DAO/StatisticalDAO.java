package DAO;

import Model.ShareData;
import Model.Statistical;
import Model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticalDAO {
    private List<Statistical> listSalary = new ArrayList<>();

    public List<Statistical> getSalary() {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT SA.*, ST.FULLNAME FROM SALARY SA JOIN STAFF ST ON SA.ID_STAFF = ST.ID_STAFF";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Statistical s = new Statistical().setId(resultSet.getInt("ID_SALARY"))
                        .setStaffId(resultSet.getString("ID_STAFF"))
                        .setStaffName(resultSet.getString("FULLNAME"))
                        .setMonth(resultSet.getInt("MONTH"))
                        .setQuarter(resultSet.getInt("QUARTER"))
                        .setYear(resultSet.getInt("YEAR"))
                        .setNote(resultSet.getString("NOTE"))
                        .setSalary(resultSet.getDouble("VALUE"));
                listSalary.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            listSalary = null;
        }
        return listSalary;
    }
}
