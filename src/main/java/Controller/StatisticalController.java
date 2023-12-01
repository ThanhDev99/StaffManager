package Controller;

import Model.Statistical;
import Services.ServicesImpl.StatisticalServiceImpl;
import Services.StatisticalService;

import java.util.ArrayList;
import java.util.List;

public class StatisticalController {
    private List<Statistical> listSalary = new ArrayList<>();
    private StatisticalService service;
    public  StatisticalController() {
        service = new StatisticalServiceImpl();
    }
    public List<Statistical> getSalary() {
        try {
            listSalary = service.getSalary();
        } catch (Exception ex) {
            ex.printStackTrace();
            listSalary = null;
        }
        return listSalary;
    }
    public Boolean exportXls(String path){
        try {
            service.exportXls(path);
            System.out.println("Xuất thành công");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Xuất không thành công");
            return false;
        }
    }
}
