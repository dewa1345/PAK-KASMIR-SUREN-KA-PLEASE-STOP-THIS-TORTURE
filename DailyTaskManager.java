import java.util.Scanner;

class TaskStack {
    private String[] stack;
    private int top;

    public TaskStack(int capacity) {
        stack = new String[capacity];
        top = -1;
    }

    public void push(String task) {
        if (top == stack.length - 1) {
            System.out.println("Task Full.");
            return;
        }
        stack[++top] = task;
    }

    public String pop() {
        if (top == -1) {
            System.out.println("You haven't done any task yet. wake up. do something");
            return null;
        }
        return stack[top--];
    }

    public String peek() {
        if (top == -1) {
            return "You've done nothing for this day. really?";
        }
        return stack[top];
    }
}

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    public void insert(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void delete(String key) {
        if (head == null) {
            System.out.println("Empty Task List.");
            return;
        }
        if (head.data.equalsIgnoreCase(key)) {
            head = head.next;
            System.out.println("Task \"" + key + "\" Has been deleted");
            return;
        }
        Node temp = head;
        while (temp.next != null && !temp.next.data.equalsIgnoreCase(key)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
            System.out.println("Task \"" + key + "\" Has been deleted");
        } else {
            System.out.println("Tugas \"" + key + "\" Not found please type the name of the Task");
        }
    }

    public void display() {
        if (head == null) {
            System.out.println("Empty Task List. Good Job Wonderful Job keep getting that dopamine from 1 mins videos");
            return;
        }
        Node temp = head;
        int count = 1;
        while (temp != null) {
            System.out.println(count + ". " + temp.data);
            temp = temp.next;
            count++;
        }
    }
}

public class DailyTaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your task management method: 1. Array (Stack), 2. Linked List");
        int method = scanner.nextInt();
        scanner.nextLine();

        if (method == 1) {
            runArrayMethod(scanner);
        } else if (method == 2) {
            runLinkedListMethod(scanner);
        } else {
            System.out.println("Invalid choice. Exiting program.");
        }
        scanner.close();
    }

    public static void runArrayMethod(Scanner scanner) {
        String[] Task = {
            "Go to college",
            "Turn on my laptop",
            "Open roblox",
            "Grinding roblox while listening to lecturers",
            "Pesan Grab pulang"
        };

        TaskStack completedTasks = new TaskStack(Task.length);
        int choice;
        String input;
        do {
            while (true) {
                System.out.println("Pick a number: 1. See your task, 2. Update/Change task, 3. See how many task you have, 4. Complete a task, 5. See last completed task, 6. Exit");
                input = scanner.next();
                if (input.equalsIgnoreCase("R")) {   
                    String undoneTask = completedTasks.pop();
                    if (undoneTask != null) {
                        System.out.println("Undo completed: " + undoneTask);
                    }
                    continue;
                }
                try {
                    choice = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid. Please enter a number between 1~6");
                }
            }
            if (choice == 1) {
                for (int i = 0; i < Task.length; i++) {
                    System.out.println((i + 1) + "." + Task[i]);
                }
            } else if (choice == 2) {
                System.out.println("Enter which task you want to change (1-5)");
                int index = scanner.nextInt();
                scanner.nextLine();
                if (index >= 1 && index <= Task.length) {
                    System.out.print("Enter your new task: ");
                    String newValue = scanner.nextLine();
                    Task[index - 1] = newValue;
                }
            } else if (choice == 3) {
                System.out.println("Total task you must complete today: " + Task.length);
            } else if (choice == 4) {
                System.out.println("Enter which task you have completed (1-5)");
                int index = scanner.nextInt();
                scanner.nextLine();
                if (index >= 1 && index <= Task.length) {
                    completedTasks.push(Task[index - 1]);
                    System.out.println("Task \"" + Task[index - 1] + "\" marked as completed!");
                }
            } else if (choice == 5) {
                System.out.println("Last completed task: " + completedTasks.peek());
            } else if (choice == 6) {
                System.out.println("Dont forget to complete your task!");
                break;
            }
        } while (choice != 7);
    }

    public static void runLinkedListMethod(Scanner scanner) {
        SinglyLinkedList taskList = new SinglyLinkedList();
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task to list");
            System.out.println("2. Remove Task from list");
            System.out.println("3. Display Task list");
            System.out.println("4. End");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("Enter the task you want to add: ");
                String task = scanner.nextLine();
                taskList.insert(task);
                System.out.println("Task added.");
            } else if (choice == 2) {
                System.out.print("Enter task name you want to delete: ");
                String task = scanner.nextLine();
                taskList.delete(task);
            } else if (choice == 3) {
                System.out.println("Display Task list:");
                taskList.display();
            } else if (choice == 4) {
                System.out.println("Ending");
                break;
            }
        }
    }
}