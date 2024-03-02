module co.edu.uptc {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires com.google.gson;

    opens co.edu.uptc to javafx.fxml;
    opens co.edu.uptc.model to com.google.gson;

    exports co.edu.uptc;
}
