package Services.ServicesImpl;

import DAO.AccountDAO;
import Model.Account;
import Services.AccountService;
import View.Help.Dialog;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class AccountServiceImpl implements AccountService {
    private List<Account> accountList = new ArrayList<>();
    private AccountDAO dao;
    public AccountServiceImpl() {
        dao = new AccountDAO();
    }
    @Override
    public String login(String account, String password) {
        String result = dao.login(account, password);

        return result;
    }
    @Override
    public Account loadDataAccount(String account) {
        Account result = dao.loadDataAccount(account);

        return result;
    }

    @Override
    public List<Account> loadDataAllAccount() {
        accountList = dao.loadDataAllAccount();

        return accountList;
    }

    @Override
    public Boolean updateAccount(Account account) {
        for (Account acc : accountList) {
            if (acc.getStaffId().equals(account.getStaffId())) {
                dao.updateAccount(account);
                return true;
            }
        }
        return false;
    }
    @Override
    public Boolean deleteAccount(String id) {
        for (Account acc : accountList) {
            if (acc.getStaffId().equals(id)) {
                dao.deleteAccount(id);
                return true;
            }
        }
        return false;
    }
    @Override
    public Boolean checkStaffId(String id) {
        return dao.checkStaffId(id);
    }

    @Override
    public String updateInfoAccount(Account account) {
        for (Account acc : accountList) {
            if (acc.getStaffId().equals(account.getStaffId())) {
                dao.updateInfoAccount(account);
                return "UPDATE";
            }
        }
        dao.addAccount(account);
        return "ADD";
    }

    @Override
    public String checkStaffEmail(String staffId) {
        String result = dao.checkStaffEmail(staffId);

        if (result.equals("NO_ID")) {
            return result;
        }
        if (result.equals("FALSE")) {
            return result;
        }

        String code = createCode().toUpperCase();

        sendMail(result, code);

        return result + " " + code;
    }

    @Override
    public String changePass(String staffId, String pass) {
        return dao.changePass(staffId, pass);
    }

    @Override
    public String changePass1(String staffId, String pass, String pass1) {
        return dao.changePass1(staffId, pass, pass1);
    }

    private void sendMail(String mail, String c) {
        try {
            // Cấu hình thông tin email của bạn
            final String username = "thanhpthps36328@fpt.edu.vn";
            final String password = "qami joow dzhe ceak";
            String to = mail;
            String subject = "FengYang.Co - Quên mật khẩu";
            String messageText = "Mã xác nhận của bạn là:\n\t\t\t" + c;

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);

            // Thiết lập thông tin người gửi và người nhận
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Thiết lập nội dung email
            message.setText(messageText);

            // Gửi email
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private String createCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}