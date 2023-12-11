package bomberman.constant;

public enum PlayerStatus {
  ALIVE("You're playing"),
  DEAD("You were killed"),
  WINNER("You win");

  private String label;

  PlayerStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
