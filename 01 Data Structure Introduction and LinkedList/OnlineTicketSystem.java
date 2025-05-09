class Ticket {
    int ticketID;
    String custName;
    String movieName;
    String seatNum;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String custName, String movieName, String seatNum, String bookingTime){
        this.ticketID = ticketID;
        this.custName = custName;
        this.movieName = movieName;
        this.seatNum = seatNum;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservation {
    private Ticket head = null;
    private Ticket tail = null;

    public void addTicket(int id, String name, String movie, String seat, String time) {
        Ticket newTicket = new Ticket(id, name, movie, seat, time);
        if (head == null) {
            head = tail = newTicket;
            tail.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
    }

    public void removeTicket(int id) {
        if (head == null) {
            System.out.println("No tickets to remove");
            return;
        }

        Ticket current = head, prev = tail;
        boolean found = false;

        do {
            if (current.ticketID == id) {
                found = true;
                if (current == head && current == tail) {
                    head= tail = null;
                } else {
                    prev.next = current.next;
                    if (current == head) head = head.next;
                    if (current == tail) tail = prev;
                }
                System.out.println("Ticket ID " + id + " removed");
                break;
            }
            prev= current;
            current = current.next;
        } while (current != head);

        if (!found) System.out.println("Ticket ID " + id + " not found");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked");
            return;
        }
        Ticket temp = head;
        System.out.println("Booked tickets:");
        do {
            System.out.println("ID = " + temp.ticketID + ", Name = " + temp.custName + ", Movie = " + temp.movieName + ", Seat = " + temp.seatNum + ", Time = " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets booked");
            return;
        }

        Ticket temp = head;
        boolean found = false;

        do {
            if (temp.custName.equalsIgnoreCase(keyword) || temp.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Found Ticket -> ID = " + temp.ticketID + ", Name = " + temp.custName + ", Movie = " + temp.movieName + ", Seat = " + temp.seatNum + ", Time = " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) System.out.println("No ticket found for = " + keyword);
    }

    public int countTickets() {
        if (head == null) return 0;
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}

public class OnlineTicketSystem {
    public static void main(String[] args) {
        TicketReservation system = new TicketReservation();

        system.addTicket(101, "Ruchi", "Oppenheimer", "A1", "10:00 PM");
        system.addTicket(102, "Vandit", "Barbie", "C4", "7:00 PM");
        system.addTicket(103, "Ami", "Barbie", "B3", "5:00 PM");

        system.displayTickets();

        System.out.println("Searching:");
        system.searchTicket("Barbie");

        System.out.println("Removing ticket:");
        system.removeTicket(102);

        system.displayTickets();

        System.out.println("Total Tickets = " + system.countTickets());
    }
}
