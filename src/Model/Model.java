package Model;

import Model.GameItems.FieldStatus;

public abstract class Model {
    public abstract FieldStatus checkFinished();
    public abstract int getPlayer();
    public abstract int getTurns();
    public abstract void increaseTurns();
    public abstract void setFieldStatus(int x, int y) throws Exception;
    public abstract void switchPlayer();
    public abstract FieldStatus getFieldStatus(int x, int y);
}
