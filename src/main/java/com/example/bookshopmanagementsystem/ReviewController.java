package com.example.bookshopmanagementsystem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ReviewController {

    @FXML
    private TableView<Book> reviewTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        reviewTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showReviews(newSelection);
            }
        });
    }

    @FXML
    protected void onLoadReviewsButtonClick() {
        String url = "https://api.myjson.online/v1/records/fdffbc1d-0077-4714-bad0-707ae87ca8e8";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonParse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jsonParse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject bookJson = jsonArray.get(i).getAsJsonObject();
            String genre = bookJson.get("genre").getAsString();
            String title = bookJson.get("title").getAsString();
            String author = bookJson.get("author").getAsString();
            JsonArray reviewsJson = bookJson.get("reviews").getAsJsonArray();
            List<String> reviews = new ArrayList<>();
            for (int j = 0; j < reviewsJson.size(); j++) {
                reviews.add(reviewsJson.get(j).getAsString());
            }
            books.add(new Book(genre, title, author, reviews));
        }

        reviewTable.getItems().setAll(books);
    }

    private void showReviews(Book book) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Book Reviews");
        alert.setHeaderText("Reviews for " + book.getTitle());
        alert.setContentText(String.join("\n", book.getReviews()));
        alert.showAndWait();
    }

    @FXML
    public void handleBackButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1100, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}