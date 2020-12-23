package assignment.program2;

import java.util.*;

public class BattleEngine {

    // data
    static Node<BattleEvent> head;
    static Node<BattleEvent> tail;

    // constructor
    public BattleEngine() {
        head = tail = null;
    }

    public static boolean isEmpty() {
        return (head == null);
    }

    public static int count() {
        int theCount = 0;
        Node<BattleEvent> pointer = head;

        while(pointer !=null) {
            theCount++;
            pointer = pointer.next;
        }
        return theCount;
    }

    public static void print() {
        String contents = "Battle Event queue is: \n";
        Node<BattleEvent> pointer = head;
        while(pointer != null) {
            contents = contents + pointer.data.toString() + "\n";
            pointer = pointer.next;
        }
        System.out.print(contents);
    }


    public static void addFirst(BattleEvent theData) {
        Node<BattleEvent> temp = new Node<BattleEvent>(theData);

        if(head == null) {
            head = tail = temp;
        } else {
            temp.next = head;
            head = temp;
        }
    }

    public static void addLast(BattleEvent theData) {
        Node<BattleEvent> temp = new Node<BattleEvent>(theData);

        if(head == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
    }

    public static void removeFirst() {
        if(head == null) {
            throw new NoSuchElementException("There is no event next.");
        } else if(head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
    }

    public static void removeLast() {
        if(head == null) {
            throw new NoSuchElementException("There is no element to remove");
        } else if(head == tail) {
            head = tail = null;
        } else {
            Node<BattleEvent> pointer = head;
            while(pointer.next != tail) {
                pointer = pointer.next;
            }
            pointer.next = null;
            tail = pointer;
        }
    }


    public static void add(BattleEvent theData) {
        Node<BattleEvent> temp = new Node<BattleEvent>(theData);
        Node<BattleEvent> pointer = head;
        boolean found = false;

        if(head == null) {
            addFirst(theData);
        } else if(head == tail) {
            if(theData.priority < head.data.priority) {
                addFirst(theData);
            } else if(theData.priority >= head.data.priority) {
                addLast(theData);
            }
        } else if(theData.priority < head.data.priority) {
            addFirst(theData);
        } else {
            while(!found) {
                if(theData.priority >= pointer.data.priority) {
                    if(pointer.next != null) {
                        pointer = pointer.next;
                    } else {
                        found = true;
                        // reached tail
                    }
                } else found = true; // found position
            }
            if(pointer == tail) {
                if(theData.priority < pointer.data.priority) {
                    temp.next = head.next;
                    head.next = temp;
                } else if(theData.priority >= pointer.data.priority) {
                    addLast(theData);
                }
            }
        }
    }

    public static Actor executeNext() {
        if(head == null) {
            return null;
        }
        Node<BattleEvent> pointer = head;
        pointer.data.doEvent();
        head = pointer.next;
        return pointer.data.target; // return target actor
    }

    public static boolean contains(Actor act) {
        Node<BattleEvent> pointer = head;
        while(pointer != null) {
            if(pointer.data.target.getName().equals(act.getName())||pointer.data.owner.getName().equals(act.getName()))
            {
                return true;
            } else {
                pointer = pointer.next;
            }
        } return false;
    }

    // Need to get a boolean for checking if the engine even contains the dead actor
    public static void remove(Actor deadActor) {
        if(contains(deadActor)) {
            Node<BattleEvent> doomed = head;
            if (head == null) { // do nothing if empty

            } else if (deadActor == doomed.data.owner || deadActor == doomed.data.target) {
                removeFirst();
            } else {
                while (!(deadActor == doomed.data.owner || deadActor == doomed.data.target)) {
                    doomed = doomed.next;
                }
                Node<BattleEvent> inFront = head;
                while (inFront.next != doomed) {
                    inFront = inFront.next;
                }
                inFront.next = doomed.next;

                if (doomed == tail) {
                    tail = inFront;
                }
            }
        }
    }
}
