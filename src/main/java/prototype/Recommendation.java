package prototype;

import java.util.ArrayList;
import java.util.List;

public class Recommendation implements Cloneable {

    private String targetAudience;
    private List<Book> books;

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
        this.books = new ArrayList<>();
    }

    @Override
    public Recommendation clone() {
        Recommendation clonedRecommendation = new Recommendation(this.targetAudience);
        for (Book book : this.books) {
            clonedRecommendation.addBook(book.clone());
        }
        return clonedRecommendation;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public void clearBooks() {
        this.books.clear();
    }

    @Override
    public String toString() {
        return "Recommendation for " + targetAudience + ": " + books;
    }
}
