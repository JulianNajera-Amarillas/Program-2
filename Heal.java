package assignment.program2;

public class Heal extends BattleEvent {

    public Heal(Actor target, Actor owner) {
        this.target = target; // set target and owner objects
        this.owner = owner;
        damage = -30;
        priority = 0;
    }

    @Override
    public String toString() {
        return "Target: " + this.target.getName() + " Damage: " + this.damage + " Priority: " + this.priority;
    }

    //@Override
    public void doEvent() {
        System.out.println(owner.name + " heals " + target.name + " for " + -1*damage + " health!");
        target.takeDamage(damage);
        // remove from linked list
        // removing done in executeNext method
    }
}
