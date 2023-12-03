package Services;

import java.util.List;

public interface CheckInService {
    public List<String> getCheckin(String staffId);
    public void setCheckIn(String staffId);
    public void setCheckOut(String staffId);
}
