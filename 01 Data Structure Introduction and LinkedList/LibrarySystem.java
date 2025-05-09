class BookNode {
    String title, author, genre;
    int bookID;
    boolean isAvailable;
    BookNode next, prev;

    public BookNode(String title, String author, String genre, int bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }

    public void printDetails() {
        System.out.println("Book ID = " + bookID + ", Title = " + title + ", Author = " + author + ", Genre = " + genre + ", Available = " + (isAvailable ? "Yes" : "No"));
    }
}

class LibraryManager {
    private BookNode head = null;
    private BookNode tail = null;

    public void addAtBegin(BookNode book) {
        if (head == null) {
            head = tail = book;
        } else {
            book.next = head;
            head.prev = book;
            head = book;
        }
    }

    public void addAtEnd(BookNode book) {
        if (head == null) {
            head = tail = book;
        } else {
            tail.next = book;
            book.prev = tail;
            tail = book;
        }
    }

    public void addAtPos(BookNode book, int pos) {
        if (pos <= 0 || head == null) {
            addAtBegin(book);
            return;
        }

        BookNode temp = head;
        for (int i = 0; i < pos - 1 && temp.next != null; i++) {
            temp = temp.next;
        }

        book.next = temp.next;
        book.prev = temp;

        if (temp.next != null) temp.next.prev = book;
        else tail = book;

        temp.next = book;
    }

    public void removeByID(int bookID) {
        BookNode current = head;

        while (current != null) {
            if (current.bookID == bookID) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;

                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev;

                System.out.println("Book ID " + bookID + " removed");
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID " + bookID + " not found");
    }

    public void search(String keyword) {
        BookNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.title.equalsIgnoreCase(keyword) || current.author.equalsIgnoreCase(keyword)) {
                current.printDetails();
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No book found for keyword = " + keyword);
    }

    public void updateAvailability(int bookID, boolean status) {
        BookNode current = head;
        while (current != null) {
            if (current.bookID == bookID) {
                current.isAvailable = status;
                System.out.println("Availability updated for Book ID = " + bookID);
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID = " + bookID + " not found");
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("Library is empty");
            return;
        }

        BookNode current = head;
        System.out.println("Books (Forward):");
        while (current != null) {
            current.printDetails();
            current = current.next;
        }
    }

    public void displayBackward() {
        if (tail == null) {
            System.out.println("Library is empty");
            return;
        }

        BookNode current = tail;
        System.out.println("Book (Reverse):");
        while (current != null) {
            current.printDetails();
            current = current.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        BookNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        System.out.println("Total Books = " + count);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        library.addAtEnd(new BookNode("The Alchemist", "Paulo Coelho", "Fiction", 1001, true));
        library.addAtBegin(new BookNode("1984", "George Orwell", "Dystopian", 1002, true));
        library.addAtPos(new BookNode("A Brief History of Time", "Stephen Hawking", "Science", 1003, false), 1);

        library.displayForward();
        System.out.println();

        System.out.println("Searching by keyword:");
        library.search("George Orwell");
        library.updateAvailability(1003, true);

        library.displayBackward();

        library.removeByID(1001);
        library.displayForward();

        library.countBooks();
    }
}
