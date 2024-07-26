import library.action.BookAction;

import library.book.Book;
import library.entry.Entry;
import library.member.Member;
import library.entry.Record;

import java.util.*;


public class LibraryNelson {

    private List<Member> membersOfLibrary;
    private Member createMember;
    private Entry createEntry;

    private List<Book> booksAvailable;

    public Scanner s ;
    private Record membersRecord;
    private BookAction bookAction;
    private ArrayList<String> titleAndAuthor;

    public LibraryNelson() {
        this.membersOfLibrary = new ArrayList<>();
        this.s = new Scanner(System.in);
        this.membersRecord = new Record("Member01");
        this.createMember = null;
        this.bookAction = null;
        this.createEntry = new Entry();
        this.booksAvailable = new ArrayList<>();
        this.titleAndAuthor = new ArrayList<>();
    }
    public void showLoginAndExit() {
        System.out.println("Hello, Welcome to Nelson Library Management Software!");
        System.out.format("1. Login\n2. Exit\n");
        try {
            int answer = s.nextInt();
            switch(answer) {
                case 1:
                    showHomePage();
                    break;
                case 2:
                    System.out.println("Exiting the application....");
                    break;
                default:
                    System.out.println("Enter a no. 1 to Login or 2 to exit");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number 1 to Login or 2 to exit.");
            s.nextLine();
            showLoginAndExit();
        }

    }

    public void showHomePage() {
        System.out.println("Choose an option");
        System.out.format("1. Borrow and return a book\n" +
                "2. Search for a book in catalog\n" +
                "3. Add books to catalog\n" +
                "4. Get Record\n" +
                "5. Register new Member\n");
        int answer = 0;

        try {
            answer = s.nextInt();
            switch (answer) {
                case 1:
                    borrowReturnBook();
                    break;
                case 2:
                    searchBookInCatalog();
                    break;
                case 3:
                    addBookToCatalog();
                    break;
                case 4:
                    getRecord();
                    break;
                case 5:
                    registerNewMember();
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 4");
                    showHomePage();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
            s.nextLine(); // consume the invalid input
            showHomePage();

        }

    }

    public void borrowReturnBook() {
        System.out.println("Borrow and return a book - Enter member id");

        try {
            int answer = s.nextInt();
            boolean doesMemberExist = checkIfAMemberExists(answer);
            if (!doesMemberExist) {
                System.out.println("I am sorry, the member does not exist please try again!....");
                borrowReturnBook();
            }
            System.out.format("Select the service you want?\n" +
                    "\t1. Borrow book\n" +
                    "\t2. Return book\n" +
                    "\t3. Exit\n");
            int anotherAnswer = s.nextInt();
            switch(anotherAnswer) {
                case 1:
                    borrowABook(answer);
                    break;
                case 2:
                    returnABook(answer);
                    break;
                case 3:
                    showHomePage();
                    break;
                default:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
            s.nextLine(); //Consume the invalid input
            borrowReturnBook();

        }
    }



    public void searchBookInCatalog() {
        this.titleAndAuthor = helperCheckForAuthorAndTitleIfEmpty();
        System.out.println(searchForABook(titleAndAuthor.get(0), titleAndAuthor.get(1)));
        showHomePage();
    }

    public void addBookToCatalog() {
        this.titleAndAuthor = helperCheckForAuthorAndTitleIfEmpty();
        booksAvailable.add(new Book(titleAndAuthor.get(0), titleAndAuthor.get(1)));
        System.out.format("The book %s by %s is now in the catalog\n", titleAndAuthor.get(0), titleAndAuthor.get(1));
        showHomePage();
    }

    private ArrayList<String> helperCheckForAuthorAndTitleIfEmpty() {
        ArrayList<String> answer = new ArrayList<>();
        s.nextLine();

        String titleOfBook = "";
        System.out.println("Enter the title of the book:");
        while (titleOfBook.isEmpty()) {
            titleOfBook = s.nextLine();
            if (titleOfBook.isEmpty()) {
                System.out.println("Be serious and enter a title of a book!");
            }
        }
        answer.add(titleOfBook);

        String authorOfBook = "";
        System.out.println("Enter the author of the book:");
        while (authorOfBook.isEmpty()) {
            authorOfBook = s.nextLine();
            if (authorOfBook.isEmpty()) {
                System.out.println("Be serious and enter the author of the book!");
            }
        }
        answer.add(authorOfBook);
        titleAndAuthor.clear();
        return answer;
    }

    public String searchForABook(String title, String author) {
        if (this.booksAvailable != null && !this.booksAvailable.isEmpty()) {
            for (Book book : this.booksAvailable) {
                if (Objects.equals(book.getTitle(), title) && Objects.equals(book.getAuthor(), author)) {
                    return String.format("The book %s by %s exists", title, author);
                }
            }
            return "The book does not exist";
        }
        return "Catalog is empty!";
    }

    public void getRecord() {
        System.out.println("Here lies the record");
        System.out.println(membersRecord);
        showHomePage();
    }

    public void registerNewMember() {
        s.nextLine();
        try {
            String answer = "";
            while(answer.isEmpty()) {
                System.out.println("Register new member - Enter a name");
                answer = s.nextLine();
                if(answer.isEmpty()) {
                    System.out.println("Be serious and enter a name!");
                }
            }
            System.out.println("Register new member - Enter an id");
            int anotherAnswer = s.nextInt();
            createMember = new Member(answer, anotherAnswer);
            membersOfLibrary.add(createMember);
            System.out.format("A new member is created: %s, %d\n", answer, anotherAnswer);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input " + e.getMessage());
            s.nextLine();  //consume the invalid input
            registerNewMember();
            return;
        }
        showHomePage();
    }

    private boolean checkIfAMemberExists(int memberId) {
        return getLibraryMemberFromList(memberId) != null;
    }

    private Member getLibraryMemberFromList(int memberId) {
        if (!membersOfLibrary.isEmpty()){
            for(Member m: membersOfLibrary) {
                if(m.getId() == memberId) {
                    return m;
                }
            }
        } else {
            System.out.println("Well this is awkward ... The Library has no members!");
        }
        return null;
    }

    public void borrowABook(int memberId) {

        this.titleAndAuthor = helperCheckForAuthorAndTitleIfEmpty();

        bookAction = new BookAction(getLibraryMemberFromList(memberId));
        createEntry = bookAction.borrowBook(new Book(titleAndAuthor.get(0), titleAndAuthor.get(1)));
        membersRecord.addEntry(createEntry);
        borrowReturnBook();

    }
    public void returnABook(int memberId) {
        try {
            System.out.println("Enter entry number when book borrowed");
            int entryNumber = s.nextInt();

            //bookAction = new BookAction(getLibraryMemberFromList(memberId));
            bookAction.setMember(getLibraryMemberFromList(memberId));
            String s = bookAction.returnBook(entryNumber, membersRecord);
            System.out.println(s);

        } catch(InputMismatchException e) {
            System.out.println("Invalid input " + e.getMessage());
            s.nextLine(); // consume the invalid input
            returnABook(memberId);
            return;
        }

        borrowReturnBook();
    }

}
