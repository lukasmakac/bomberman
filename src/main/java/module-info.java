module bomberman {
    requires transitive javafx.controls;
    requires javafx.fxml;
    opens bomberman to javafx.fxml;
    exports bomberman;
  exports bomberman.character;
  opens bomberman.character to javafx.fxml;
  exports bomberman.solid;
  opens bomberman.solid to javafx.fxml;
  exports bomberman.common;
  opens bomberman.common to javafx.fxml;
  exports bomberman.constant;
  opens bomberman.constant to javafx.fxml;
}