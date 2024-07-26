package library.librarian;

public class Librarian {
    private String name;
    private String id;

    public Librarian(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
