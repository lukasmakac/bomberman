package bomberman.common;

import java.util.List;
import javafx.geometry.Rectangle2D;

public interface Collisional {

  Rectangle2D getBoundingBox();

  default boolean hitBy(Collisional other) {
    return getBoundingBox().intersects(other.getBoundingBox());
  }

  default boolean hitBy(List<? extends Collisional> others) {
    return others.stream().anyMatch(this::hitBy);
  }

}
