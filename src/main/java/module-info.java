module lab01 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    opens bomberman to javafx.fxml;
    exports bomberman;
  exports bomberman.character;
  opens bomberman.character to javafx.fxml;
}