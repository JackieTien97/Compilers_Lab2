package entity;

public class ActionItem {
    public ActionType actionType;
    public int appendix;

    public ActionItem(ActionType actionType, int appendix) {
        this.actionType = actionType;
        this.appendix = appendix;
    }
}
