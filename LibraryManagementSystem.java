import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }
}

class Patron {
    private String name;
    private String contactInfo;
    private List<Book> borrowedBooks;

    public Patron(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (!book.isAvailable()) {
            System.out.println("Book is not available for borrowing.");
            return;
        }
        borrowedBooks.add(book);
        book.borrow();
        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("You didn't borrow this book.");
        }
    }
}

public class LibraryManagementSystem {
    private static Map<String, Book> books = new HashMap<>();
    private static Map<String, Patron> patrons = new HashMap<>();

    public static void main(String[] args) {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "Classic");
        Book book3 = new Book("1984", "George Orwell", "Dystopian");
        books.put(book1.getTitle(), book1);
        books.put(book2.getTitle(), book2);
        books.put(book3.getTitle(), book3);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Patron");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Print Books");
            System.out.println("6. Print Patrons");
            System.out.println("7. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    addPatron(scanner);
                    break;
                case 3:
                    borrowBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    printBooks();
                    break;
                case 6:
                    printPatrons();
                    break;
                case 7:
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

Book book = new Book(title, author, genre);
        books.put(title, book);
        System.out.println("Book added successfully.");
    }

    private static void addPatron(Scanner scanner) {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();

        System.out.print("Enter contact information: ");
        String contactInfo = scanner.nextLine();

        Patron patron = new Patron(name, contactInfo);
        patrons.put(name, patron);
        System.out.println("Patron added successfully.");
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter patron name: ");
        String patronName = scanner.nextLine();

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        if (patrons.containsKey(patronName) && books.containsKey(bookTitle)) {
            Patron patron = patrons.get(patronName);
            Book book = books.get(bookTitle);
            patron.borrowBook(book);
        } else {
            System.out.println("Patron or book not found.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter patron name: ");
        String patronName = scanner.nextLine();

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        if (patrons.containsKey(patronName) && books.containsKey(bookTitle)) {
            Patron patron = patrons.get(patronName);
            Book book = books.get(bookTitle);
            patron.returnBook(book);
        } else {
            System.out.println("Patron or book not found.");
        }
    }

    private static void printBooks() {
        System.out.println("Books in the library:");
        for (Book book : books.values()) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
            System.out.println();
        }
    }

    private static void printPatrons() {
        System.out.println("Patrons in the library:");
        for (Patron patron : patrons.values()) {
            System.out.println("Name: " + patron.getName());
            System.out.println("Contact Information: " + patron.getContactInfo());
            System.out.println("Borrowed Books:");
            for (Book book : patron.getBorrowedBooks()) {
                System.out.println("  " + book.getTitle());
            }
            System.out.println();
        }
    }
}