package library.entry;

import library.book.Book;
import library.librarian.Librarian;
import library.member.Member;

import java.time.LocalDate;

public class Entry {
    private static int nextId = 1;
    private int entryId;
    private Book bookBorrowed = null;
    private LocalDate issueDate = null;
    private LocalDate returnDate = null;
    private Member issuedToMember = null;

    public Entry() {
        this.entryId = nextId;
        nextId++;
    }

    public Book getBookBorrowed() {
        return bookBorrowed;
    }

    public void setBookBorrowed(Book bookBorrowed) {
        this.bookBorrowed = bookBorrowed;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Member getIssuedToMember() {
        return issuedToMember;
    }

    public void setIssuedToMember(Member issuedToMember) {
        this.issuedToMember = issuedToMember;
    }

    public int getEntryId() {
        return entryId;
    }
}
