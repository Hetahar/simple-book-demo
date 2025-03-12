package prototype;

public class Book implements Cloneable {
    private final String title;
    private final String author;
    private final String genre;
    private final int year;

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // should not happen
        }
    }

    @Override
    public String toString() {
        return title + " written by " + author + " (" + genre + ", " + year + ")";
    }
}
