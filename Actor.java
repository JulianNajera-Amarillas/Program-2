package assignment.program2;

public class Actor {

    final int STARTINGHEALTH = 100;
    String name;
    int currentHealth;

    public Actor(String name) {
        this.name = name;
        this.currentHealth = STARTINGHEALTH;
    }

    public void takeDamage(int damage) {
        this.currentHealth = currentHealth - damage;
        if(this.currentHealth >= 100) {
            this.currentHealth = 100;
        }
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public boolean isDead() {
        return (currentHealth <= 0);
    }
}
