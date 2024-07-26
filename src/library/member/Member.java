package library.member;

import library.book.Book;
import library.entry.Entry;
import library.entry.Record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Member {

    private String name;
    private int id;
    private List<Book> booksPreviouslyBorrowed;
    private List<Book> booksCurrentlyBorrowed;


    public Member(String name, int id) {
        this.name = name;
        this.id = id;
        this.booksCurrentlyBorrowed = new ArrayList<>();
        this.booksPreviouslyBorrowed = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Book> getBooksCurrentlyBorrowed() {
        return this.booksCurrentlyBorrowed;
    }

    public List<Book> getBooksPreviouslyBorrowed() {
        return this.booksPreviouslyBorrowed;
    }

}
