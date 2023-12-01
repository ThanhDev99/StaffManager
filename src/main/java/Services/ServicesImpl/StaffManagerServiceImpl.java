package Services.ServicesImpl;

import DAO.StaffManagerDAO;
import Model.Staff;
import Services.StaffManagerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffManagerServiceImpl implements StaffManagerService {
    private List<Staff> list = new ArrayList<>();
    private StaffManagerDAO dao;
    public StaffManagerServiceImpl() {
        dao = new StaffManagerDAO();
    }

    @Override
    public List<Staff> getAllStaff() {
        list = dao.getAllStaff();

        for (Staff s : list) {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date workDate = input.parse(s.getDateStart());
                Date birthday = input.parse(s.getBirthday());

                SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");

                s.setDateStart(output.format(workDate));
                s.setBirthday(output.format(birthday));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }

    @Override
    public String deleteStaffById(String id) {
        for (Staff s : list) {
            if (s.getId().equals(id)) {
                if (dao.deleteStaffById(id)) {
                    return "TRUE";
                } else {
                    return "FALSE";
                }
            }
        }
        return "NOID";
    }

    @Override
    public String updateStaff(Staff staff) {
        for (Staff s : list) {
            if (s.getId().equals(staff.getId())) {
                if (dao.updateStaff(staff)) {
                    return "UPDATE";
                }
            }
        }
        dao.addNewStaff(staff);
        return "ADD";
    }
}
