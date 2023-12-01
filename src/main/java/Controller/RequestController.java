package Controller;

import Model.Request;
import Services.RequestService;
import Services.ServicesImpl.RequestServiceImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import java.util.ArrayList;
import java.util.List;

public class RequestController {
    private List<Request> list = new ArrayList<>();
    private RequestService service;
    public RequestController() {
        service = new RequestServiceImpl();
    }
    public List<Request> getRequestById() {
        try {
            list = service.getRequestById();
        } catch (Exception ex) {
            ex.printStackTrace();
            list = null;
        }
        return list;
    }
    public Boolean addRequest(Request request) {
        try {
            return service.addRequest(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public List<Request> getRequestByManager() {
        try {
            list = service.getRequestByManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            list = null;
        }
        return list;
    }
    public Boolean updateStatus(Request request) {
        try {
            return service.updateStatus(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
