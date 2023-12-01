package DAO;

import Model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private List<Account> accountList = new ArrayList<>();
    public String login(String account, String password) {
        String result;

        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "{? = CALL PR1_LOGIN(?, ?)}";

            CallableStatement callableStatement = cn.prepareCall(sql);

            callableStatement.registerOutParameter(1, Types.NVARCHAR);
            callableStatement.setString(2, account);
            callableStatement.setString(3, password);

            callableStatement.execute();

            result = callableStatement.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;
        }

        return result;
    }
    public Account loadDataAccount(String account) {
        Account result = new Account();
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql =   "SELECT AC.[ID_STAFF]" +
                                    ",AC.[PASSWORD]" +
                                    ",AC.[CHECKIN]" +
                                    ",AC.[TASK]" +
                                    ",AC.[REQUEST]" +
                                    ",AC.[STAFF]" +
                                    ",AC.[STAFF_MANAGER]" +
                                    ",AC.[STATISTICAL]" +
                                    ",AC.[ACCOUNT]" +
                                    ",AC.[ADMIN] " +
                                    ",S.[DEPARTMENT] " +
                                    ",AC.[IS_MANAGER] " +
                                    ",AC.[CHAT] " +
                            "FROM [PROJECT_1].[dbo].[ACCOUNT] AS AC JOIN [PROJECT_1].[dbo].[STAFF] AS S ON AC.ID_STAFF = S.ID_STAFF " +
                            "WHERE AC.[ID_STAFF] = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, account);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result.setStaffId(resultSet.getString("ID_STAFF"));
                result.setPassword(resultSet.getString("PASSWORD"));
                result.setDepartment(resultSet.getString("DEPARTMENT"));
                result.setCheckIn(resultSet.getBoolean("CHECKIN"));
                result.setTask(resultSet.getBoolean("TASK"));
                result.setRequest(resultSet.getBoolean("REQUEST"));
                result.setStaff(resultSet.getBoolean("STAFF"));
                result.setStaffManager(resultSet.getBoolean("STAFF_MANAGER"));
                result.setStatistical(resultSet.getBoolean("STATISTICAL"));
                result.setAccount(resultSet.getBoolean("ACCOUNT"));
                result.setAdmin(resultSet.getBoolean("ADMIN"));
                result.setIsManager(resultSet.getBoolean("IS_MANAGER"));
                result.setChat(resultSet.getBoolean("CHAT"));
            } else {
                result = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;
        }
        return result;
    }
    public List<Account> loadDataAllAccount() {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql =    "SELECT AC.[ID_STAFF]" +
                            ",AC.[PASSWORD]" +
                            ",AC.[CHECKIN]" +
                            ",AC.[TASK]" +
                            ",AC.[REQUEST]" +
                            ",AC.[STAFF]" +
                            ",AC.[STAFF_MANAGER]" +
                            ",AC.[STATISTICAL]" +
                            ",AC.[ACCOUNT]" +
                            ",AC.[ADMIN] " +
                            ",S.[DEPARTMENT] " +
                            ",AC.[IS_MANAGER] " +
                            ",AC.[CHAT] " +
                            "FROM [PROJECT_1].[dbo].[ACCOUNT] AS AC JOIN [PROJECT_1].[dbo].[STAFF] AS S ON AC.ID_STAFF = S.ID_STAFF";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account acc = new Account()
                        .setStaffId(resultSet.getString("ID_STAFF"))
                        .setDepartment(resultSet.getString("DEPARTMENT"))
                        .setPassword(resultSet.getString("PASSWORD"))
                        .setCheckIn(resultSet.getBoolean("CHECKIN"))
                        .setTask(resultSet.getBoolean("TASK"))
                        .setRequest(resultSet.getBoolean("REQUEST"))
                        .setStaff(resultSet.getBoolean("STAFF"))
                        .setStaffManager(resultSet.getBoolean("STAFF_MANAGER"))
                        .setStatistical(resultSet.getBoolean("STATISTICAL"))
                        .setAccount(resultSet.getBoolean("ACCOUNT"))
                        .setAdmin(resultSet.getBoolean("ADMIN"))
                        .setIsManager(resultSet.getBoolean("IS_MANAGER"))
                        .setChat(resultSet.getBoolean("CHAT"));
                accountList.add(acc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            accountList = null;
        }
        return accountList;
    }
    public void updateAccount(Account account) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "UPDATE ACCOUNT " +
                         "SET CHECKIN = ?" +
                            " , TASK = ?" +
                            " , REQUEST = ?" +
                            " , STAFF = ?" +
                            " , STAFF_MANAGER = ?" +
                            " , STATISTICAL = ?" +
                            " , ACCOUNT = ?" +
                            " , ADMIN = ? " +
                            " , IS_MANAGER = ? " +
                            " , CHAT = ? " +
                         "WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setBoolean(1, account.getCheckIn());
            preparedStatement.setBoolean(2, account.getTask());
            preparedStatement.setBoolean(3, account.getRequest());
            preparedStatement.setBoolean(4, account.getStaff());
            preparedStatement.setBoolean(5, account.getStaffManager());
            preparedStatement.setBoolean(6, account.getStatistical());
            preparedStatement.setBoolean(7, account.getAccount());
            preparedStatement.setBoolean(8, account.getAdmin());
            preparedStatement.setString(11, account.getStaffId());
            preparedStatement.setBoolean(9, account.getIsManager());
            preparedStatement.setBoolean(10, account.getChat());

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Boolean addAccount(Account account) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "INSERT INTO ACCOUNT(ID_STAFF, PASSWORD) " +
                    "VALUES (?, ?)";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, account.getStaffId());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public void deleteAccount(String id) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "DELETE ACCOUNT WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Boolean checkStaffId(String id) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT COUNT(0) FROM STAFF WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public void updateInfoAccount(Account account) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "UPDATE ACCOUNT " +
                    "SET PASSWORD = ? " +
                    "WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getStaffId());

            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String checkStaffEmail(String staffId) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "SELECT EMAIL FROM STAFF WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, staffId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("EMAIL");
            }
            return "NO_ID";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FALSE";
        }
    }
    public String changePass(String staffId, String pass) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "UPDATE ACCOUNT SET PASSWORD = ? WHERE ID_STAFF = ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, staffId);

            preparedStatement.executeUpdate();

            return "YES";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FALSE";
        }
    }
    public String changePass1(String staffId, String pass, String pass1) {
        try (Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PROJECT_1;encrypt=true;trustServerCertificate=true"
                , "sa", "root123")) {
            String sql = "EXECUTE PR1_CHANGE_PASSWORD ?, ?, ?";

            PreparedStatement preparedStatement = cn.prepareStatement(sql);

            preparedStatement.setString(1, staffId);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, pass1);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("RESULT");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FALSE";
        }
        return "FALSE";
    }
}
