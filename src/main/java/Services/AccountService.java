package Services;

import Model.Account;

import java.util.List;

public interface AccountService {
    public String login(String account, String password);
    public Account loadDataAccount(String account);
    public List<Account> loadDataAllAccount();
    public Boolean updateAccount(Account account);
    public Boolean deleteAccount(String id);
    public Boolean checkStaffId(String id);
    public String updateInfoAccount(Account account);
    public String checkStaffEmail(String staffId);
    public String changePass(String staffId, String pass);
    public String changePass1(String staffId, String pass, String pass1);
}
