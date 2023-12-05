package bomberman.common;

import javafx.animation.PathTransition;
import javafx.scene.canvas.GraphicsContext;

public interface Transitionable {
  void apply(GraphicsContext gc, PathTransition transition);
}
