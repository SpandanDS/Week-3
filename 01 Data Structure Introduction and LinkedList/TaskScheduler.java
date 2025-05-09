class Task {
    int taskID;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskID, String taskName, int priority, String dueDate) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }

    public void printTask() {
        System.out.println("Task ID = " + taskID + ", Task Name = " + taskName + ", Priority = " + priority + ", Due Date = " + dueDate);
    }
}

class CircularTaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;

    public void addAtBegin(Task newTask) {
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addAtEnd(Task newTask) {
        if (head == null) {
            addAtBegin(newTask);
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    public void addAtPosition(Task newTask, int position) {
        if (position == 0 || head == null) {
            addAtBegin(newTask);
            return;
        }

        Task temp = head;
        for (int i = 0; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }
    }

    public void removeByID(int taskID) {
        if (head == null) return;

        Task current = head, prev = tail;
        do {
            if (current.taskID == taskID) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                    if (current == tail) tail = prev;
                }
                System.out.println("Task ID = " + taskID + " removed");
                return;
            }
            prev = current;
            current = current.next;
        }  while (current != head);

        System.out.println("Task ID = " + taskID + " not found");
    }

    public void viewAndMoveToNext() {
        if (current == null) {
            current = head;
        }

        if (current != null) {
            System.out.println("Current Task: ");
            current.printTask();
            current = current.next;
        } else {
            System.out.println("No task available");
        }
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks to display");
            return;
        }

        Task temp = head;
        System.out.println("All tasks: ");
        do {
            temp.printTask();
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search");
            return;
        }

        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                temp.printTask();
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) System.out.println("No tasks found with priority = " + priority);
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        CircularTaskScheduler scheduler = new CircularTaskScheduler();

        scheduler.addAtEnd(new Task(1, "Finish Report", 2, "2025-04-12"));
        scheduler.addAtBegin(new Task(2, "Email Client", 1, "2025-04-11"));
        scheduler.addAtPosition(new Task(3, "Team Meeting", 3, "2025-04-13"), 1);

        scheduler.displayAllTasks();

        scheduler.viewAndMoveToNext();
        scheduler.viewAndMoveToNext();
        scheduler.viewAndMoveToNext();

        scheduler.searchByPriority(2);
        scheduler.removeByID(1);
        scheduler.displayAllTasks();
    }
}
