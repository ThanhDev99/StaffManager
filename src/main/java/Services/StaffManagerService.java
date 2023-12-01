package Services;

import Model.Staff;

import java.util.ArrayList;
import java.util.List;

public interface StaffManagerService {
    public List<Staff> getAllStaff();
    public String deleteStaffById(String id);
    public String updateStaff(Staff staff);
}
