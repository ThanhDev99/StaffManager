package Services.ServicesImpl;

import DAO.CheckInDAO;
import Services.CheckInService;

import java.util.List;

public class CheckInServiceImpl implements CheckInService {
    private CheckInDAO dao;
    public CheckInServiceImpl() {
        dao = new CheckInDAO();
    }
    @Override
    public List<String> getCheckin(String staffId) {
        return dao.getCheckin(staffId);
    }

    @Override
    public void setCheckIn(String staffId) {
        dao.setCheckIn(staffId);
    }

    @Override
    public void setCheckOut(String staffId) {
        dao.setCheckOut(staffId);
    }
}
