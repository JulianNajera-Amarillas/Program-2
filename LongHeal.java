package assignment.program2;

public class LongHeal extends Heal {


    public LongHeal(Actor target, Actor owner) {
        super(target,owner); // call parent constructor from Heal class
        damage = -80;
        priority = 1;
    }
}
