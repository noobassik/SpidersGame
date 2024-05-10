package Events;

public interface GameActionListener {
    void gameEnded(GameActionEvent event);
    void gameStepHappened(GameActionEvent event);
    void insectsCreated(GameActionEvent event);

    void playerChanged(GameActionEvent event);
}
