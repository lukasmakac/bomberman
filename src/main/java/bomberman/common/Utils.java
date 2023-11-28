package bomberman.common;

import java.util.Objects;
import javafx.scene.image.Image;

public final class Utils {

  private static final String IMAGE_PATH_PREFIX = "/bomberman/pictures/";

  private Utils() {
  }

  public static Image getImage(String path) {
    return new Image(Objects.requireNonNull(Utils.class.getResourceAsStream(IMAGE_PATH_PREFIX + path)));
  }
}
