package Services.ServicesImpl;

import DAO.RequestDAO;
import Model.Request;
import Services.RequestService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class RequestServiceImpl implements RequestService {
    private List<Request> list = new ArrayList<>();
    private RequestDAO dao;
    public  RequestServiceImpl() {
        dao = new RequestDAO();
    }
    @Override
    public List<Request> getRequestById() {
        list = dao.getAllRequestById();

        for (Request r : list) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date sendDate = simpleDateFormat.parse(r.getDateSend());

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

                r.setDateSend(simpleDateFormat1.format(sendDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public List<Request> getRequestByManager() {
        list = dao.getAllRequestByManager();

        for (Request r : list) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date sendDate = simpleDateFormat.parse(r.getDateSend());

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

                r.setDateSend(simpleDateFormat1.format(sendDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public Boolean addRequest(Request request) {
        List<Integer> listId = dao.getAllRequest();
        if (listId.size() == 0 || listId.size() == 1) {
            request.setId(listId.size() + 1);
        }

        for (int i = 0; i < listId.size() - 1; i++) {
            if (listId.get(i + 1) - listId.get(i) != 1) {
                request.setId(listId.get(i) + 1);
                break;
            }
            request.setId(listId.get(i + 1) + 1);
        }

        System.out.println(request.getId());
        return dao.addRequest(request);
    }

    @Override
    public Boolean updateStatus(Request request) {
        return dao.updateStatus(request);
    }
}
