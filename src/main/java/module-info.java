module co.edu.uptc {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens co.edu.uptc to javafx.fxml;
    opens co.edu.uptc.view.movies to javafx.fxml;
    opens co.edu.uptc.view.seasons to javafx.fxml;
    opens co.edu.uptc.view.serie.episode to javafx.fxml;
    opens co.edu.uptc.view.categories to javafx.fxml;
    opens co.edu.uptc.view.users to javafx.fxml;
    opens co.edu.uptc.view.user to javafx.fxml;

    opens co.edu.uptc.view.reproduce to javafx.fxml;

    opens co.edu.uptc.model to com.google.gson;
    opens co.edu.uptc.view.series to javafx.fxml;

    exports co.edu.uptc;
    exports co.edu.uptc.controller;
    exports co.edu.uptc.model;
    exports co.edu.uptc.persistence;
    exports co.edu.uptc.view.movies;
    exports co.edu.uptc.view.users;
    exports co.edu.uptc.view.seasons;
    exports co.edu.uptc.view.serie.episode;
    exports co.edu.uptc.view.categories;
    exports co.edu.uptc.view.reproduce;
    exports co.edu.uptc.view.user;
    exports co.edu.uptc.view.series;

}
