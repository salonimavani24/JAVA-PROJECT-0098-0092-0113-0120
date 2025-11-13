package org.sda.todolist;

import java.util.Scanner;

/**
 * This is main class of the project
 *
 * @author Imtiaz
 * @version 1.0
 * @since 2019-10-11
 **/


public class Main {
    // A string to hold the data file name which contains all tasks and their details
    public static String filename = "tasks.obj";

    /**
     * main method to run the command line based "To Do List" application
     * @param args array of String holding command line parameters
     */
    public static void main(String args[]) {
        // An object of TodoList to hold all tasks and their data
        TodoList todoList = new TodoList();

//added
int completed = todoList.completedCount();
int pending = todoList.notCompletedCount();
int overdue = todoList.overdueCount();

// show progress + overdue summary
Messages.showDashboard(
    todoList.completedCount(), 
    todoList.notCompletedCount(), 
    todoList.overdueCount()
);
//Messages.showDashboard(completed, pending, overdue);  // if you added 3 parameters version
// or if not, just use:
//Messages.showOverdueSummary(overdue);
//

        //A string to hold the choice that will be entered by the user
        String menuChoice = "-17";

        try {
            Scanner input = new Scanner(System.in);

            // reading the date from task data file
            // if this is the first time, a message will be shown that no data file is found
            todoList.readFromFile(filename);

            Messages.showMessage("Welcome to ToDoList", false);

           //added // inside Main, after creating todoList and showing welcome:

Messages.showMessage("Welcome to ToDoList", false);

// ADD this line to show dashboard
Messages.showDashboard(todoList.completedCount(), todoList.notCompletedCount(), todoList.overdueCount());

// then proceed to show menu
Messages.mainMenu(todoList.notCompletedCount(), todoList.completedCount());
//

            while (!menuChoice.equals("4")) {
                Messages.mainMenu(todoList.notCompletedCount(), todoList.completedCount());
                menuChoice = input.nextLine();

                switch (menuChoice) {
                    case "1":
                        Messages.listAllTasksMenu();
                        todoList.listAllTasks(input.nextLine());
                        break;
                    case "2":
                        todoList.readTaskFromUser();
                        break;
                    case "3":
                        todoList.listAllTasksWithIndex();
                        Messages.editTaskSelection();
                        todoList.editTask(input.nextLine());
                        break;
                    case "4":
                        break;

                    default:
                        Messages.unknownMessage();
                }
            }

            // saving the task details in data file
            // if this is the first time, a new task file will be created
            todoList.saveToFile(filename);
            Messages.byeMessage();

        } catch (Exception e) {
            Messages.showMessage("UNCAUGHT EXCEPTION THROWN", true);
            System.out.println("Trying to write the unsaved data of all tasks in data file");
            todoList.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}

