package assignment.program2;

public class Attack extends BattleEvent {


    public Attack(Actor target, Actor owner) {
        this.target = target;
        this.owner = owner;
        damage = 30;
        priority = 0;
    }

    @Override
    public String toString() {
        return "Target: " + this.target.getName() + " Damage: " + this.damage + " Priority: " + this.priority;
    }

    //@Override
    public void doEvent() {
        System.out.println(owner.getName() + " attacks " + target.getName() + " for " + damage + " damage!");
        target.takeDamage(damage);
        if(target.currentHealth <= 0) {
            System.out.println(target.name + " is defeated!");
        }
    }
}
