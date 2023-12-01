package Controller;

import Model.Task;
import Services.ServicesImpl.TaskServiceImpl;
import Services.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskController {
    private TaskService taskService;
    private List<Task> listTask = new ArrayList<>();
    public TaskController() {
        taskService = new TaskServiceImpl();
    }
    public List<Task> getTaskByStaffId() {
        try {
            listTask = taskService.getTaskByStaffId();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getTaskBySTaffId fail");
        }

        return listTask;
    }
    public Boolean updateTaskS(Task task) {
        try {
            return taskService.updateTaskS(task);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getTaskBySTaffId fail");
            return false;
        }
    }
    public Boolean deleteTaskById(Integer id) {
        try {
            return taskService.deleteTaskById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("deleteTaskById fail");
            return false;
        }
    }
    public Map<String, String> getStaffByManager(String id) {
        try {
            return taskService.getStaffByManager(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getStaff fail");
            return null;
        }
    }
    public Boolean addTask(Task task, List<String> staff) {
        try {
            taskService.addTask(task, staff);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("addTask fail");
            return false;
        }
    }
    public List<Task> getTaskByManager() {
        try {
            listTask = taskService.getTaskByManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getTaskBySTaffManager fail");
        }

        return listTask;
    }
}
