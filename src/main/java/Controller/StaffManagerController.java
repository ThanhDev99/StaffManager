package Controller;

import Model.Staff;
import Services.ServicesImpl.StaffManagerServiceImpl;
import Services.StaffManagerService;

import java.util.ArrayList;
import java.util.List;

public class StaffManagerController {
    private StaffManagerService service;
    private List<Staff> list = new ArrayList<>();
    public StaffManagerController() {
        service = new StaffManagerServiceImpl();
    }
    public List<Staff> getAllStaff() {
        try {
            list = service.getAllStaff();
        } catch (Exception ex) {
            ex.printStackTrace();
            list = null;
        }
        return list;
    }
    public String deleteStaffById(String id) {
        try {
            return service.deleteStaffById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FALSE";
        }
    }
    public String updateStaff(Staff staff) {
        try {
            return service.updateStaff(staff);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "FALSE";
        }
    }
}
