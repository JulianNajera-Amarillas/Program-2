package assignment.program2;

public abstract class BattleEvent {

    Actor owner;
    Actor target;
    int damage;
    int priority;

    public abstract void doEvent();

}
