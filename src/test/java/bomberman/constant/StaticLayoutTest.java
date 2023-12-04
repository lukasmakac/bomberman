package bomberman.constant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StaticLayoutTest {

  @Test
  void wallGeneratorTest() {
    assertEquals(92, StaticLayout.WALLS.size());
  }

}