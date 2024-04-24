package Events;

public interface InsectActionListener {
    void insectDied(InsectActionEvent event);
    void insectWasEaten(InsectActionEvent event);
}
