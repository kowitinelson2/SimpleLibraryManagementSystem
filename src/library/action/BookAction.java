package library.action;

import library.book.Book;
import library.entry.Entry;
import library.entry.Record;
import library.member.Member;

import java.time.LocalDate;
import java.util.List;


public class BookAction {

    private Member member;
    LocalDate currentDate;
    public BookAction(Member member) {
        this.member = member;
        this.currentDate = LocalDate.now();
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Entry borrowBook(Book book) {
        if (book.isBookAvailable()) {
            this.member.getBooksCurrentlyBorrowed().add(book);
            book.setCurrentBorrowedStatus(true);
            Entry e = createEntry(book);
            int i = e.getEntryId();
            System.out.format("[Entry number %d] You have borrowed %s by %s\n", i, book.getTitle(), book.getAuthor());
            return e;
        } else {
            System.out.format("The book is not available for borrowing!");
            return null;
        }
    }

    public String returnBook(int entryNo, Record r) {
        Book b = findBookBorrowedUsingEntry(entryNo, r);
        if (this.member.getBooksCurrentlyBorrowed().remove(b)) {
            member.getBooksPreviouslyBorrowed().add(b);
            b.setCurrentBorrowedStatus(false);
            return updateEntryReturnDate(entryNo, r) ? "Book successfully returned": "The entry in the record does not exist";
        } else {
            return "This book was not borrowed";
        }
    }

    public Book findBookBorrowedUsingEntry(int entryNo, Record r) {
        for (Entry e: r.getEntries()){
            if(e.getEntryId() == entryNo) {
                return e.getBookBorrowed();
            }
        }
        System.out.println("The entry is not there");
        return null;
    }

    private Entry createEntry(Book book) {
        Entry n = new Entry();
        n.setBookBorrowed(book);
        n.setIssueDate(currentDate);
        n.setIssuedToMember(this.member);
        return n;
    }

    private boolean updateEntryReturnDate(int entryNo, Record r) {
        if (r != null) {
            List<Entry> list = r.getEntries();
            for(int i = 0; i < list.size(); i++) {
                Entry e = list.get(i);
                int a = e.getEntryId();
                if (a == (entryNo)) {
                    e.setReturnDate(currentDate);
                    list.set(i, e);
                    return true;
                }
            }
        } else {
            System.out.format("The Record has no entries");
        }
        return false;
    }
}
