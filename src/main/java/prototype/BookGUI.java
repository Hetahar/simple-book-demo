package prototype;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BookGUI extends Application {

    private List<Recommendation> recommendations = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        Recommendation sciFi = new Recommendation("SciFi fans");
        sciFi.addBook(new Book("Dune", "Frank Herbert", "SciFi", 1965));
        sciFi.addBook(new Book("Foundation", "Isaac Asimov", "SciFi", 1951));
        sciFi.addBook(new Book("Neuromancer", "William Gibson", "SciFi", 1984));

        Recommendation romance = new Recommendation("Hopeless romantics");
        romance.addBook(new Book("Pride and Prejudice", "Jane Austen", "Romance", 1813));
        romance.addBook(new Book("Jane Eyre", "Charlotte Bronte", "Romance", 1847));
        romance.addBook(new Book("Wuthering Heights", "Emily Bronte", "Romance", 1847));

        Recommendation horror = new Recommendation("Horror fans");
        horror.addBook(new Book("Dracula", "Bram Stoker", "Horror", 1897));
        horror.addBook(new Book("Frankenstein", "Mary Shelley", "Horror", 1818));
        horror.addBook(new Book("The Shining", "Stephen King", "Horror", 1977));

        recommendations.add(sciFi);
        recommendations.add(romance);
        recommendations.add(horror);

        refreshUI(root);

        primaryStage.setTitle("Book Recommendations");
        Scene scene = new Scene(root, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshUI(VBox root) {
        root.setStyle("-fx-background-color: #FAEBD7; -fx-padding: 20px;");
        root.getChildren().setAll();

        for (Recommendation recommendation : recommendations) {
            showRecommendation(recommendation, root);
        }

        if (root.getChildren().stream().noneMatch(node -> node instanceof Button && ((Button) node).getText().equals("Add Recommendation"))) {
            Button addNew = new Button("Add Recommendation");
            addNew.setStyle("-fx-background-color: #8B4513; -fx-text-fill: #FAEBD7; -fx-font-size: 14px; -fx-font-weight: bold;");
            addNew.setOnAction(e -> createNewRecommendation(root));
            root.getChildren().add(addNew);
        }
    }

    private void createNewRecommendation(VBox root) {
        Stage newRecStage = new Stage();
        VBox newRecRoot = new VBox(15);
        newRecRoot.setStyle("-fx-background-color: #FAEBD7; -fx-padding: 20px; -fx-border-color: #8B4513; -fx-border-radius: 10px;");

        Label audienceLabel = new Label("Target Audience:");
        audienceLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold; -fx-font-size: 14px;");

        TextField targetAudience = new TextField();
        targetAudience.setPromptText("Enter audience name");
        targetAudience.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px; -fx-font-size: 12px;");

        Button addBooks = new Button("Add Books");
        addBooks.setDisable(true);
        addBooks.setStyle("-fx-background-color: #D2691E; -fx-text-fill: #FAEBD7; -fx-border-radius: 5px; -fx-font-size: 12px;");

        VBox bookContainer = new VBox(10);
        bookContainer.setPadding(new Insets(5, 10, 10, 10));
        List<Book> newBooks = new ArrayList<>();

        Button done = new Button("Done");
        done.setDisable(true);
        done.setStyle("-fx-background-color: #8B4513; -fx-text-fill: #FAEBD7; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-font-size: 14px;");

        targetAudience.textProperty().addListener((obs, oldText, newText) -> {
            addBooks.setDisable(newText.trim().isEmpty());
        });

        addBooks.setOnAction(event -> {
            Stage addBookStage = new Stage();
            VBox bookRoot = new VBox(15);
            bookRoot.setStyle("-fx-background-color: #FAEBD7; -fx-padding: 20px; -fx-border-color: #8B4513; -fx-border-radius: 10px;");

            Label titleLabel = new Label("Title:");
            Label authorLabel = new Label("Author:");
            Label genreLabel = new Label("Genre:");
            Label yearLabel = new Label("Year:");

            titleLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");
            authorLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");
            genreLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");
            yearLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");

            TextField title = new TextField();
            title.setPromptText("Enter title");
            TextField author = new TextField();
            author.setPromptText("Enter author");
            TextField genre = new TextField();
            genre.setPromptText("Enter genre");
            TextField year = new TextField();
            year.setPromptText("Enter year");

            title.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");
            author.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");
            genre.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");
            year.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");

            Button addBook = new Button("Add Book");
            addBook.setDisable(true);
            addBook.setStyle("-fx-background-color: #D2691E; -fx-text-fill: #FAEBD7; -fx-border-radius: 5px; -fx-font-size: 14px;");

            title.textProperty().addListener((obs, oldText, newText) -> validateBookInputs(title, author, genre, year, addBook));
            author.textProperty().addListener((obs, oldText, newText) -> validateBookInputs(title, author, genre, year, addBook));
            genre.textProperty().addListener((obs, oldText, newText) -> validateBookInputs(title, author, genre, year, addBook));
            year.textProperty().addListener((obs, oldText, newText) -> validateBookInputs(title, author, genre, year, addBook));

            addBook.setOnAction(ev -> {
                try {
                    int yearValue = Integer.parseInt(year.getText().trim());
                    Book book = new Book(title.getText().trim(), author.getText().trim(), genre.getText().trim(), yearValue);
                    newBooks.add(book);

                    Label bookLabel = new Label(book.toString());
                    bookLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-size: 12px;");
                    bookContainer.getChildren().add(bookLabel);

                    title.clear();
                    author.clear();
                    genre.clear();
                    year.clear();

                    done.setDisable(newBooks.isEmpty());
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid year");
                }
            });

            bookRoot.getChildren().addAll(titleLabel, title, authorLabel, author, genreLabel, genre, yearLabel, year, addBook);
            addBookStage.setScene(new Scene(bookRoot, 350, 400));
            addBookStage.setTitle("Add Book");
            addBookStage.show();
        });

        done.setOnAction(event -> {
            Recommendation newRecommendation = new Recommendation(targetAudience.getText().trim());
            newRecommendation.setBooks(new ArrayList<>(newBooks));
            recommendations.add(newRecommendation);

            refreshUI(root);

            newRecStage.close();
        });

        newRecRoot.getChildren().addAll(audienceLabel, targetAudience, addBooks, bookContainer, done);
        newRecStage.setScene(new Scene(newRecRoot, 450, 550));
        newRecStage.setTitle("New Recommendation");
        newRecStage.show();
    }

    private void showRecommendation(Recommendation recommendation, VBox root) {
        Button button = new Button(recommendation.getTargetAudience());
        button.setMinWidth(200);
        button.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-font-size: 16px; -fx-font-weight: bold;");

        Button cloneButton = new Button("Clone");
        cloneButton.setStyle("-fx-background-color: #D2691E; -fx-text-fill: #5C4033; -fx-font-size: 12px;");

        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #D2691E; -fx-text-fill: #5C4033; -fx-font-size: 12px;");

        Button removeButton = new Button("Remove");
        removeButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #FAEBD7; -fx-font-size: 12px; -fx-border-radius: 5px;");

        VBox bookContainer = new VBox(5);
        bookContainer.setPadding(new Insets(5, 10, 10, 10));

        button.setOnAction(e -> {
            bookContainer.getChildren().clear();
            recommendation.getBooks().forEach(book -> {
                Label bookLabel = new Label(book.toString());
                bookLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-size: 12px;");
                bookContainer.getChildren().add(bookLabel);
            });

            Button clear = new Button("Clear");
            clear.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033;");
            clear.setOnAction(event -> bookContainer.getChildren().clear());
            bookContainer.getChildren().add(clear);
        });

        cloneButton.setOnAction(e -> {
            Recommendation cloned = recommendation.clone();
            recommendations.add(cloned);
            refreshUI(root);
        });

        editButton.setOnAction(e -> editRecommendation(recommendation, root));

        removeButton.setOnAction(e -> {
            recommendations.remove(recommendation);
            refreshUI(root);
        });

        HBox buttonContainer = new HBox(15, button, cloneButton, editButton, removeButton);
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setStyle("-fx-background-color: #FAEBD7; -fx-border-color: #8B4513; -fx-border-radius: 10px; -fx-padding: 10px;");

        root.getChildren().addAll(buttonContainer, bookContainer);
    }

    private void editRecommendation(Recommendation recommendation, VBox root) {
        Stage editStage = new Stage();
        VBox editRoot = new VBox(15);
        editRoot.setStyle("-fx-background-color: #FAEBD7; -fx-padding: 20px; -fx-border-color: #8B4513; -fx-border-radius: 10px;");

        Label audienceLabel = new Label("Audience Name:");
        audienceLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold; -fx-font-size: 14px;");

        TextField audienceField = new TextField(recommendation.getTargetAudience());
        audienceField.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px; -fx-font-size: 12px;");

        ListView<Book> bookList = new ListView<>();
        bookList.getItems().addAll(recommendation.getBooks());
        bookList.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");

        Button addBook = new Button("Add Book");
        Button removeBook = new Button("Remove Selected");
        Button save = new Button("Save");

        addBook.setStyle("-fx-background-color: #D2691E; -fx-text-fill: #FAEBD7; -fx-border-radius: 5px; -fx-font-size: 12px;");
        removeBook.setStyle("-fx-background-color: #D2691E; -fx-text-fill: #FAEBD7; -fx-border-radius: 5px; -fx-font-size: 12px;");
        save.setStyle("-fx-background-color: #8B4513; -fx-text-fill: #FAEBD7; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-font-size: 14px;");

        addBook.setOnAction(e -> addBookToRecommendation(recommendation, bookList));
        removeBook.setOnAction(e -> {
            Book selected = bookList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                recommendation.removeBook(selected);
                bookList.getItems().remove(selected);
            }
        });

        save.setOnAction(e -> {
            recommendation.setTargetAudience(audienceField.getText());
            refreshUI(root);
            editStage.close();
        });

        editRoot.getChildren().addAll(audienceLabel, audienceField, bookList, addBook, removeBook, save);
        editStage.setScene(new Scene(editRoot, 450, 550));
        editStage.setTitle("Edit Recommendation");
        editStage.show();
    }


    private void addBookToRecommendation(Recommendation recommendation, ListView<Book> bookList) {
        Stage addBookStage = new Stage();
        VBox bookRoot = new VBox(15);
        bookRoot.setStyle("-fx-background-color: #FAEBD7; -fx-padding: 20px; -fx-border-color: #8B4513; -fx-border-radius: 10px;");

        Label titleLabel = new Label("Title:");
        Label authorLabel = new Label("Author:");
        Label genreLabel = new Label("Genre:");
        Label yearLabel = new Label("Year:");

        titleLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");
        authorLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");
        genreLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");
        yearLabel.setStyle("-fx-text-fill: #5C4033; -fx-font-weight: bold;");

        TextField titleField = new TextField();
        titleField.setPromptText("Enter title");
        TextField authorField = new TextField();
        authorField.setPromptText("Enter author");
        TextField genreField = new TextField();
        genreField.setPromptText("Enter genre");
        TextField yearField = new TextField();
        yearField.setPromptText("Enter year");

        titleField.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");
        authorField.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");
        genreField.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");
        yearField.setStyle("-fx-background-color: #F5DEB3; -fx-text-fill: #5C4033; -fx-border-color: #8B4513; -fx-border-radius: 5px;");

        Button saveBook = new Button("Add Book");
        saveBook.setStyle("-fx-background-color: #D2691E; -fx-text-fill: #FAEBD7; -fx-border-radius: 5px; -fx-font-size: 14px;");

        saveBook.setOnAction(e -> {
            try {
                int year = Integer.parseInt(yearField.getText().trim());
                Book newBook = new Book(titleField.getText().trim(), authorField.getText().trim(), genreField.getText().trim(), year);
                recommendation.addBook(newBook);
                bookList.getItems().add(newBook);
                addBookStage.close();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid year format.");
            }
        });

        bookRoot.getChildren().addAll(
                titleLabel, titleField,
                authorLabel, authorField,
                genreLabel, genreField,
                yearLabel, yearField,
                saveBook
        );

        addBookStage.setScene(new Scene(bookRoot, 450, 550));
        addBookStage.setTitle("Add Book");
        addBookStage.show();
    }

    private void validateBookInputs(TextField title, TextField author, TextField genre, TextField year, Button addBook) {
        boolean allFilled = !title.getText().trim().isEmpty() &&
                !author.getText().trim().isEmpty() &&
                !genre.getText().trim().isEmpty() &&
                isValidYear(year.getText().trim());

        addBook.setDisable(!allFilled);
    }

    private boolean isValidYear(String yearText) {
        try {
            Integer.parseInt(yearText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
