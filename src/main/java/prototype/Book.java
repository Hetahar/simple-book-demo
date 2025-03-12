package prototype;

public class Book implements Cloneable {
    private String title;
    private String author;
    private String genre;
    private int year;

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public Book clone() {
        return new Book(this.title, this.author, this.genre, this.year);
    }

    @Override
    public String toString() {
        return title + " written by " + author + " (" + genre + ", " + year + ")";
    }
}
