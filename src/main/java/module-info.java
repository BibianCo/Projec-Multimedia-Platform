module co.edu.uptc {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires com.google.gson;

    opens co.edu.uptc to javafx.fxml;
    opens co.edu.uptc.view.movies to javafx.fxml;
    opens co.edu.uptc.view.seasons to javafx.fxml;
    opens co.edu.uptc.view.serie.episode to javafx.fxml;
    opens co.edu.uptc.view.categories to javafx.fxml;
    opens co.edu.uptc.view.users to javafx.fxml;
    opens co.edu.uptc.model to com.google.gson;

    exports co.edu.uptc;
    exports co.edu.uptc.controller;
    exports co.edu.uptc.model;
    exports co.edu.uptc.persistence;
    exports co.edu.uptc.view.movies;
    exports co.edu.uptc.view.users;
    exports co.edu.uptc.view.seasons;
    exports co.edu.uptc.view.serie.episode;
    exports co.edu.uptc.view.categories;
}
