package Controller;

import Services.CheckInService;
import Services.ServicesImpl.CheckInServiceImpl;

import java.util.List;

public class CheckInController {
    private CheckInService service;
    public CheckInController() {
        service = new CheckInServiceImpl();
    }
    public List<String> getCheckin(String staffId) {
        return service.getCheckin(staffId);
    }
    public void setCheckIn(String staffId) {
        service.setCheckIn(staffId);
    }
    public void setCheckOut(String staffId) {
        service.setCheckOut(staffId);
    }
}
