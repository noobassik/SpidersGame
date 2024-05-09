package Events.Controllers;

public interface SpiderControllerActionListener {
    void spiderMoved(SpiderControllerActionEvent event);
    void spiderDied(SpiderControllerActionEvent event);
}
