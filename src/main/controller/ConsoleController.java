package main.controller;

import java.util.List;
import main.model.MenuEntry;
import main.model.Task;
import main.service.MessageService;
import main.service.TaskManagerService;
import main.util.OutputUtilities;
import main.util.TaskUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleController {

    private ArrayList<MenuEntry>  initializeMainMenu() {
        ArrayList<MenuEntry> menuEntries = new ArrayList<>();
        menuEntries.add(new MenuEntry(1, "main_menu_add_task") {
            @Override
            public void run() throws IOException {
                TaskManagerService.getInstance().addTask();
            }
        });
        menuEntries.add(new MenuEntry(2, "main_menu_show_task") {
            @Override
            public void run() throws IOException {
                if (TaskManagerService.getInstance().getTasks().size() == 0) {
                    System.out.println(MessageService.getInstance().getMessageByKey("no_task_created").getMessage());
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println(MessageService.getInstance().getMessageByKey("show_task_dialog_question1").getMessage());
                    displayTaskList(TaskManagerService.getInstance().getTasks());
                    System.out.println(MessageService.getInstance().getMessageByKey("show_task_dialog_question2").getMessage());
                    int number = Integer.parseInt(reader.readLine());
                    Task task = TaskUtilities.getTaskByNumber(number);

                    if (task == null) {
                        System.out.println(MessageService.getInstance().getMessageByKey("no_task_found").getMessage());
                        this.run();
                    } else {
                        displayTaskDetails(task);
                    }
                }
            }
        });
        menuEntries.add(new MenuEntry(3, "main_menu_edit_task") {
            @Override
            public void run() throws IOException {
                if (TaskManagerService.getInstance().getTasks().size() == 0) {
                    System.out.println(MessageService.getInstance().getMessageByKey("no_task_created").getMessage());
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println(MessageService.getInstance().getMessageByKey("edit_task_dialog_question1").getMessage());
                    displayTaskList(TaskManagerService.getInstance().getTasks());
                    System.out.println(MessageService.getInstance().getMessageByKey("edit_task_dialog_question2").getMessage());
                    int number = Integer.parseInt(reader.readLine());
                    Task task = TaskUtilities.getTaskByNumber(number);

                    if (task == null) {
                        System.out.println(MessageService.getInstance().getMessageByKey("no_task_found").getMessage());
                        this.run();
                    } else {
                        System.out.println(MessageService.getInstance().getMessageByKey("edit_task_dialog_question3").getMessage());
                        displayMenu(initializeTaskEditMenu(task));
                    }
                }
            }
        });
        menuEntries.add(new MenuEntry(4, "main_menu_delete_task") {
            @Override
            public void run() throws IOException {
                if (TaskManagerService.getInstance().getTasks().size() == 0) {
                    System.out.println(MessageService.getInstance().getMessageByKey("no_task_created").getMessage());
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println(MessageService.getInstance().getMessageByKey("delete_task_dialog_question1").getMessage());
                    displayTaskList(TaskManagerService.getInstance().getTasks());
                    System.out.println(MessageService.getInstance().getMessageByKey("delete_task_dialog_question2").getMessage());
                    int number = Integer.parseInt(reader.readLine());
                    Task task = TaskUtilities.getTaskByNumber(number);

                    if (task == null) {
                        System.out.println(MessageService.getInstance().getMessageByKey("no_task_found").getMessage());
                        this.run();
                    } else {
                        TaskManagerService.getInstance().removeTaskFromTaskList(task);
                        System.out.println(MessageService.getInstance().getMessageByKey("delete_task_dialog_success").getMessage());
                    }
                }
            }
        });
        menuEntries.add(new MenuEntry(5, "main_menu_show_all_tasks") {
            @Override
            public void run() throws IOException {
                if (TaskManagerService.getInstance().getTasks().size() == 0) {
                    System.out.println(MessageService.getInstance().getMessageByKey("no_task_created").getMessage());
                } else {
                    displayTaskList(TaskUtilities.getAllTasks());
                }

            }
        });
        menuEntries.add(new MenuEntry(6, "main_menu_exit") {
            @Override
            public void run() throws IOException {
                System.exit(1);
            }
        });

        return menuEntries;
    }

    private ArrayList<MenuEntry> initializeTaskEditMenu(Task task) {
        ArrayList<MenuEntry> menuEntries = new ArrayList<>();
        menuEntries.add(new MenuEntry(1, "short_description") {
            @Override
            public void run() throws IOException {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.printf("%s (%s): ", MessageService.getInstance().getMessageByKey("short_description").getMessage(), task.getShortDescription());
                task.setShortDescription(reader.readLine());
            }
        });
        menuEntries.add(new MenuEntry(2, "description") {
            @Override
            public void run() throws IOException {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.printf("%s (%s): ", MessageService.getInstance().getMessageByKey("description").getMessage(), task.getDescription());
                task.setDescription(reader.readLine());
            }
        });
        menuEntries.add(new MenuEntry(3, "priority") {
            @Override
            public void run() throws IOException {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.printf("%s (%s): ", MessageService.getInstance().getMessageByKey("priority").getMessage(), task.getPriority());
                task.setPriority(Integer.parseInt(reader.readLine()));
            }
        });
        menuEntries.add(new MenuEntry(4, "state") {
            @Override
            public void run() throws IOException {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.printf("%s (%s): ", MessageService.getInstance().getMessageByKey("state").getMessage(), task.getState());
                task.setState(Integer.parseInt(reader.readLine()));
            }
        });
        menuEntries.add(new MenuEntry(5, "back") {
            @Override
            public void run() throws IOException {
                getMainMenu();
            }
        });
        return menuEntries;
    }

    private void displayMenu(ArrayList<MenuEntry> menuEntries) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> headerList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();


        headerList.add("Nr.");
        headerList.add("Name");
        menuEntries.forEach((menuEntry) -> {
            //System.out.printf("|%-2d- %-30s|\n", menuEntry.getId(), menuEntry.getMessage());
            List<String> col = new ArrayList<>();

            col.add(Integer.toString(menuEntry.getId()));
            col.add(menuEntry.getMessage());
            rows.add(col);
        });

        System.out.println(OutputUtilities.generateTable(headerList, rows));
        System.out.println(
                MessageService
                    .getInstance()
                    .getMessageByKey("menu_selection")
                    .getMessage());

        int selection = validateSelection(reader.readLine());
        if (isValidMenuEntry(selection, menuEntries)) {
            menuEntries
                    .stream()
                    .filter(m -> m.getId() == selection)
                    .findFirst()
                    .get()
                    .run();
        } else {
            System.out.println(MessageService.getInstance().getMessageByKey("wrong_menu_selection").getMessage());
        }
        displayMenu(menuEntries);
        reader.close();
    }

    private void displayTaskDetails(Task task) {
        //number, short Description, description, state, priority
        List<String> headerList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();
        headerList.add(MessageService.getInstance().getMessageByKey("number").getMessage());
        headerList.add(MessageService.getInstance().getMessageByKey("short_description").getMessage());
        headerList.add(MessageService.getInstance().getMessageByKey("description").getMessage());
        headerList.add(MessageService.getInstance().getMessageByKey("state").getMessage());
        headerList.add(MessageService.getInstance().getMessageByKey("priority").getMessage());

        List<String> col = new ArrayList<>();

        col.add(Integer.toString(task.getNumber()));
        col.add(task.getShortDescription());
        col.add(task.getDescription());
        col.add(task.getState());
        col.add(task.getPriority());
        rows.add(col);

        System.out.println(OutputUtilities.generateTable(headerList, rows));

        //System.out.printf("|%10d|%10s|%10s|%10s|%10s|\n", task.getNumber(), task.getShortDescription(), task.getDescription(), task.getState(), task.getPriority());
    }

    private void displayTaskList(ArrayList<Task> taskList) {
        List<String> headerList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();
        headerList.add(MessageService.getInstance().getMessageByKey("number").getMessage());
        headerList.add(MessageService.getInstance().getMessageByKey("short_description").getMessage());
        headerList.add(MessageService.getInstance().getMessageByKey("state").getMessage());
        headerList.add(MessageService.getInstance().getMessageByKey("priority").getMessage());

        taskList.forEach((Task task) -> {
            List<String> col = new ArrayList<>();
            col.add(Integer.toString(task.getNumber()));
            col.add(task.getShortDescription());
            col.add(task.getState());
            col.add(task.getPriority());
            rows.add(col);
            //System.out.printf("|%10d|%10s|%10s|%10s|\n", task.getNumber(), task.getShortDescription(), task.getState(), task.getPriority());
        });

        System.out.println(OutputUtilities.generateTable(headerList, rows));
    }

    private boolean isValidMenuEntry (int selection, ArrayList<MenuEntry> menuEntries) {
       return menuEntries
                .stream()
               .anyMatch(m -> m.getId() == selection);

    };

    public void getMainMenu () throws IOException {
        displayMenu(initializeMainMenu());
    }

    public Task getAddTaskDialog(Task task) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.printf("%s: ", MessageService.getInstance().getMessageByKey("short_description").getMessage());
        task.setShortDescription(reader.readLine());

        System.out.printf("%s: ", MessageService.getInstance().getMessageByKey("description").getMessage());
        task.setDescription(reader.readLine());

        System.out.printf("%s: ", MessageService.getInstance().getMessageByKey("priority").getMessage());
        task.setPriority(Integer.parseInt(reader.readLine()));

        return task;
    }

    private int validateSelection (String selection) {
        return Integer.parseInt(selection);
    }

}
