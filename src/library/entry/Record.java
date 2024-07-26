package library.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Record {
    private List<Entry> entries;

    public Record(String recordName) {
        this.entries = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public List<Entry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        if(!this.entries.isEmpty()) {
            for (Entry e: this.entries) {
                return String.format("%d\t%s\t%s\t%s\t%s", e.getEntryId(),
                        e.getIssuedToMember().getName(),
                        e.getIssueDate().toString(),
                        e.getReturnDate(),
                        e.getBookBorrowed().getTitle());
            }
        }
        return "There is no entries";
    }
}
