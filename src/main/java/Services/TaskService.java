package Services;

import Model.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    public List<Task> getTaskByStaffId();
    public List<Task> getTaskByManager();
    public Boolean updateTaskS(Task task);
    public Boolean deleteTaskById(Integer id);
    public Map<String, String> getStaffByManager(String id);
    public void addTask(Task task, List<String> staff);
}
