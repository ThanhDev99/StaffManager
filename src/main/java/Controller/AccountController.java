package Controller;

import Model.Account;
import Services.AccountService;
import Services.ServicesImpl.AccountServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class AccountController {
    private List<Account> accountList = new ArrayList<>();
    private AccountService service;
    public AccountController() {
        service = new AccountServiceImpl();
    }
    public String login(String account, String password) {
        String result;

        try {
            result = service.login(account, password);
            System.out.println("Login successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("login fail");
            result = "FALSE";
        }

        return result;
    }
    public Account loadDataAccount(String account) {
        Account result;

        try {
            result = service.loadDataAccount(account);
            System.out.println("Load data account successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("login fail");
            result = null;
        }

        return result;
    }
    public List<Account> loadDataAllAccount() {
        try {
            accountList = service.loadDataAllAccount();
            System.out.println("Load data account successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("login fail");
            accountList = null;
        }

        return accountList;
    }
    public Boolean updateAccount(Account account) {
        try {
            return service.updateAccount(account);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update fail");
            return false;
        }
    }
    public Boolean deleteAccount(String id) {
        try {
            return service.deleteAccount(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Delete fail");
            return false;
        }
    }
    public Boolean checkStaffId(String id) {
        try {
            return service.checkStaffId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Check fail");
            return false;
        }
    }
    public String updateInfoAccount(Account account) {
        try {
            return service.updateInfoAccount(account);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update fail");
            return "FALSE";
        }
    }
    public String checkStaffEmail(String staffId) {
        try {
            return service.checkStaffEmail(staffId);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update fail");
            return "FALSE";
        }

    }
    public String changePass(String staffId, String pass) {
        try {
            return service.changePass(staffId, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update fail");
            return "FALSE";
        }
    }
    public String changePass1(String staffId, String pass, String pass1) {
        try {
            return service.changePass1(staffId, pass, pass1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Update fail");
            return "FALSE";
        }
    }
}
