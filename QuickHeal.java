package assignment.program2;

public class QuickHeal extends Heal {

    public QuickHeal(Actor target, Actor owner) {
        super(target, owner); // Call parent constructor
        damage = -10;
        priority = -1;
    }
}
