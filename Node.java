package assignment.program2;

public class Node<BattleEvent> {

    // data
    BattleEvent data;
    Node<BattleEvent> next;

    // constructor
    public Node(BattleEvent theData) {
        this.data = theData;
        next = null;
    }


    //@Override
    public String toString() {
        return this.data.toString();
    }

}
