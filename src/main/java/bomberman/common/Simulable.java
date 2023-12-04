package bomberman.common;

import javafx.scene.canvas.GraphicsContext;

public interface Simulable {
  void simulate(GraphicsContext gc, double deltaT);
}
