package assignment.program2;

import java.util.ArrayList;
import java.util.Scanner;

public class Program2 {


    static Scanner kb = new Scanner(System.in);
    static ArrayList<Actor> actors = new ArrayList<Actor>();
    static Actor player = new Actor("Player");
    static boolean fightOngoing = true;
    static boolean newEnemyAddedYet = false;
    static int numberOfTurnsOccurred = 0;
    static String choice = "";

    public static void main(String[] args) {

        actors.add(player);
        actors.add(new Actor("Goblin"));
        do {
            menu();
            switch(choice) {
                case "a":
                    for(int i = 0; i < actors.size(); i++) {
                        System.out.println((actors.get(i).getName() + " has " + (actors.get(i).getCurrentHealth())) +
                                " health left.");
                    }
                    break;

                case "b":
                    QuickAttack quick = new QuickAttack(getEnemy(), player);
                    BattleEngine.add(quick);
                    runTurn();
                    break;
                case "c":
                    Attack att = new Attack(getEnemy(), player);
                    BattleEngine.add(att);
                    runTurn();
                    break;
                case "d":
                    PreciseAttack precise = new PreciseAttack(getEnemy(), player);
                    BattleEngine.add(precise);
                    runTurn();
                    break;
                case "e":
                    QuickHeal healQuick = new QuickHeal(player, player);
                    BattleEngine.add(healQuick);
                    runTurn();
                    break;
                case "f":
                    Heal normal = new Heal(player, player);
                    BattleEngine.add(normal);
                    runTurn();
                    break;
                case "g":
                    LongHeal lh = new LongHeal(player, player);
                    BattleEngine.add(lh);
                    runTurn();
                    break;
                case "h":
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while(!choice.equalsIgnoreCase("h") && fightOngoing);
    }


    static public String menu() {
        System.out.println("What do you want to do?");
        System.out.println("A. Check Status of the Combatants");
        System.out.println("B. Quick Attack");
        System.out.println("C. Normal Attack");
        System.out.println("D. Precise Attack");
        System.out.println("E. Quick Heal");
        System.out.println("F. Normal Heal");
        System.out.println("G. Long Heal");
        System.out.println("H. Quit");

        choice = kb.nextLine();
        choice = choice.toLowerCase();
        return choice;
    }

    static public Actor getEnemy() {
        String enemy = "";
        Actor target = null;
        boolean found = false;
        System.out.println("Which enemy do you want to attack? (Type their name)");

        while(!found) {
            enemy = kb.nextLine();
            for(Actor actor : actors) {
                if(actor.getName().strip().equals(enemy.strip())) {
                    target = actor;
                    found = true;
                    break;
                }
            }
            if(!found) {
                System.out.print("No enemy by that name!\n");
            }
        }
        return target;
    }

    static public void runTurn() {
        Actor nextActor;
        numberOfTurnsOccurred++;

        if(!newEnemyAddedYet && numberOfTurnsOccurred >= 3) {
            actors.add(new Actor("Imp"));
            System.out.println("A wandering Imp sneaks up on you and joins the battle against you!");
            newEnemyAddedYet = true;
        }
        for(Actor actor: actors) {
            if(!actor.isDead() && actor.getName().equals("Goblin")) {
                BattleEngine.add(new Attack(player, actor));
            } else if(!actor.isDead() && actor.getName().equals("Imp")) {
                BattleEngine.add(new QuickAttack(player, actor));
            }
        }

        BattleEngine.print();
        while(!BattleEngine.isEmpty()) {
            nextActor = BattleEngine.executeNext();
            if(player.isDead()) {
                BattleEngine.remove(player);
                actors.remove(player);
                fightOngoing = false;
                break;
            }
            if(nextActor.isDead()) {
                BattleEngine.remove(nextActor);
                actors.remove(nextActor);
            }
            if(!actors.contains(player) || actors.size() < 2) {
                fightOngoing = false;
            }
        }
    }
}
