package Events;

public interface PlayerActionListener {
    void playerDied(PlayerActionEvent event);
    void playerMoved(PlayerActionEvent event);
}
