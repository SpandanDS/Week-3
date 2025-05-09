class Student {
    int rollNo;
    String name;
    int age;
    String grade;
    Student next;

    public Student(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    private Student head;

    public void addAtBeginning(Student newStud) {
        newStud.next = head;
        head = newStud;
    }

    public void addAtEnd(Student newStud) {
        if (head == null) {
            head = newStud;
            return;
        }
        Student current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newStud;
    }

    public void addAtPosition(Student newStud,int position) {
        if (position == 0) {
            addAtBeginning(newStud);
            return;
        }

        Student current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Invalid position");
            return;
        }
        newStud.next = current.next;
        current.next = newStud;
    }

    public void deleteByRollNumber(int rollNo) {
        if (head == null) return;

        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Record deleted");
            return;
        }

        Student current = head;
        while (current.next != null && current.next.rollNo != rollNo) {
                current = current.next;
        }

        if (current.next == null) {
            System.out.println("Record not found");
        } else {
            current.next = current.next.next;
            System.out.println("Record deleted");
        }
    }

    public void searchByRollNumber(int rollNo) {
        Student current = head;
        while (current != null) {
            if (current.rollNo == rollNo) {
                System.out.println("Found = " + current.name + ", Age = " + current.age + ", Grade = " + current.grade);
                return;
            }
            current = current.next;
        }
        System.out.println("Record not found");
    }

    public void updateGrade(int rollNo, String newGrade) {
        Student current = head;
        while (current != null) {
            if (current.rollNo == rollNo) {
                current.grade = newGrade;
                System.out.println("Grade updated");
                return;
            }
            current = current.next;
        }
        System.out.println("Record not found");
    }

    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Student current = head;
        while (current != null) {
            System.out.println("Roll No = " + current.rollNo + ", Name = " + current.name + ", Age = " + current.age + ", Grade = " + current.grade);
            current = current.next;
        }
    }
}

public class StudentRecordManager {
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();

        list.addAtEnd(new Student(387, "Ruchi", 22, "A"));
        list.addAtBeginning(new Student(205, "Vandit", 17, "B"));
        list.addAtPosition(new Student(176, "Ami", 20, "C"), 1);

        list.display();

        System.out.println("Searching by roll number = 387");
        list.searchByRollNumber(387);

        System.out.println("Updating grade for roll number = 176");
        list.updateGrade(176, "D");

        System.out.println("All records after updating:");
        list.display();

        System.out.println("Deleting by roll number = 205");
        list.deleteByRollNumber(205);

        System.out.println("All records after deleting:");
        list.display();
    }
}
