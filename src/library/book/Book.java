package library.book;

public class Book {

    private String title;
    private String author;
    private boolean currentBorrowedStatus;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.currentBorrowedStatus = false;
    }

    public void setCurrentBorrowedStatus(boolean currentBorrowedStatus) {
        this.currentBorrowedStatus = currentBorrowedStatus;
    }
    public boolean isBookAvailable() {
        return !currentBorrowedStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
