package assignment.program2;

public class QuickAttack extends Attack {

    public QuickAttack(Actor target, Actor owner) {
        super(target, owner); // Call parent constructor
        damage = 10;
        priority = -1;
    }

}
