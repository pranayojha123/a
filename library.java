import java.util.*;


class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
    }
}


class User {
    private String name;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return name + " (Borrowed: " + borrowedBooks.size() + " books)";
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public boolean issueBook(String title, User user) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title) && !b.isIssued()) {
                b.issue();
                user.borrowBook(b);
                System.out.println("Book issued: " + b.getTitle() + " to " + user.getName());
                return true;
            }
        }
        System.out.println("Book not available: " + title);
        return false;
    }

    public boolean returnBook(String title, User user) {
        for (Book b : user.getBorrowedBooks()) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.returnBook();
                user.returnBook(b);
                System.out.println("Book returned: " + b.getTitle() + " by " + user.getName());
                return true;
            }
        }
        System.out.println("This book was not borrowed by " + user.getName());
        return false;
    }
}
v

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

        
        library.addBook(new Book("Java Basics", "James Gosling"));
        library.addBook(new Book("C++ Fundamentals", "Bjarne Stroustrup"));
        library.addBook(new Book("Python Guide", "Guido van Rossum"));

        
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        System.out.println("\n📚 Available Books:");
        library.showBooks();

        // Issue books
        library.issueBook("Java Basics", user1);
        library.issueBook("Python Guide", user2);

        System.out.println("\n📚 After issuing:");
        library.showBooks();

        // Return a book
        library.returnBook("Java Basics", user1);

        System.out.println("\n📚 After returning:");
        library.showBooks();
    }
}
