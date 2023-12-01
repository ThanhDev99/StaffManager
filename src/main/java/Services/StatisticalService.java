package Services;

import Model.Statistical;

import java.util.List;

public interface StatisticalService {
    public List<Statistical> getSalary();
    public Boolean exportXls(String path);
}
