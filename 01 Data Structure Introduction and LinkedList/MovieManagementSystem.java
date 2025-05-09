class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieDoublyLL {
    private Movie head;
    private Movie tail;

    public void addAtBeginning(Movie newMovie) {
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addAtEnd(Movie newMovie) {
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addAtPosition(Movie newMovie, int position) {
        if (position == 0) {
            addAtBeginning(newMovie);
            return;
        }

        Movie current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null || current.next == null) {
            addAtEnd(newMovie);
            return;

        }

        newMovie.next = current.next;
        newMovie.prev = current;
        current.next.prev = newMovie;
        current.next = newMovie;
    }

    public void removeByTitle(String title) {
        Movie current = head;

        while (current != null && !current.title.equalsIgnoreCase(title)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Movie not found");
            return;
        }

        if (current == head) {
            head = current.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (current == tail) {
            tail = current.prev;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        System.out.println("Movie \"" + title + "\" removed");
    }

    public void searchByDirector(String director) {
        Movie current = head;
        boolean found = false;

        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                printMovie(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movies found for director = " + director);
    }

    public void searchByRating(double rating) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating == rating) {
                printMovie(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movies found for rating = " + rating);
    }

    public void updateRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                System.out.println("Rating updated for movie \"" + title + "\"");
                return;
            }
            current = current.next;
        }
        System.out.println("Movie \"" + title + "\" not found");
    }

    public void displayForward() {
        Movie current = head;
        if (current == null) {
            System.out.println("No movies to display");
            return;
        }

        System.out.println("Movies (Forward):");
        while (current != null) {
            printMovie(current);
            current = current.next;
        }
    }

    public void displayBackward() {
        Movie current = tail;
        if (current == null) {
            System.out.println("No movies to display");
            return;
        }

        System.out.println("Movies (Backward):");
        while (current != null) {
            printMovie(current);
            current = current.prev;
        }
    }

    public void printMovie(Movie m) {
        System.out.println("Title = " + m.title + ", Director = " + m.director + ", Year = " + m.year + ", Rating = " + m.rating);
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieDoublyLL list = new MovieDoublyLL();

        list.addAtEnd(new Movie("Inception", "Christopher Nolan", 2010, 8.8));
        list.addAtBeginning(new Movie("The Matrix", "Lana Wachowskis", 1999, 8.7));
        list.addAtPosition(new Movie("Interstellar", "Christopher Nolan", 2014, 8.6), 1);

        list.displayForward();

        list.updateRating("Interstellar", 9.0);
        list.searchByDirector("Christopher Nolan");

        list.removeByTitle("The Matrix");
        list.displayForward();

        list.displayBackward();

    }
}
