package Services.ServicesImpl;

import DAO.TaskDAO;
import Model.Task;
import Services.TaskService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements TaskService {
    private TaskDAO taskDAO;
    private List<Task> listTask = new ArrayList<>();
    public TaskServiceImpl() {
        taskDAO = new TaskDAO();
    }
    @Override
    public List<Task> getTaskByStaffId() {
        listTask = taskDAO.getTaskByStaffId();
        return listTask;
    }

    @Override
    public List<Task> getTaskByManager() {
        listTask = taskDAO.getTaskByManager();
        return listTask;
    }

    @Override
    public Boolean updateTaskS(Task task) {
        return taskDAO.updateTaskS(task);
    }

    @Override
    public Boolean deleteTaskById(Integer id) {
        return taskDAO.deleteTaskById(id);
    }

    @Override
    public Map<String, String> getStaffByManager(String id) {
        return taskDAO.getStaffByManager(id);
    }

    @Override
    public void addTask(Task task, List<String> staff) {
        if (listTask.size() == 0) {
            task.setId(1);
        }

        for (int i = 0; i < listTask.size() - 1; i++) {
            if (listTask.get(i + 1).getId() - listTask.get(i).getId() != 1) {
                task.setId(listTask.get(i).getId() + 1);
                break;
            }
            task.setId(listTask.get(i + 1).getId() + 1);
        }

        for (String s : staff) {
            task.setStaff(s);
            taskDAO.addTask(task);
        }
    }
}
