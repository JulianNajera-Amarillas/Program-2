package assignment.program2;

public class PreciseAttack extends Attack {

    public PreciseAttack(Actor target, Actor owner) {
        super(target, owner); // call constructor from parent Attack class
        damage = 70;
        priority = 1;
    }
}
