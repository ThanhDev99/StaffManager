package Services;

import Model.Request;

import java.util.List;

public interface RequestService {
    public List<Request> getRequestById();
    public List<Request> getRequestByManager();
    public Boolean addRequest(Request request);
    public Boolean updateStatus(Request request);
}
