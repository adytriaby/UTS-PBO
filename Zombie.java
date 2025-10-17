// ====== Zombie.java ======
abstract class Zombie {
    protected int level;
    protected double health;
    protected final double maxHealth;

    public Zombie(int level, double health) {
        this.level = level;
        this.health = health;
        this.maxHealth = Math.max(health, 100);
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    protected void clamp() {
        if (health < 0) health = 0;
        if (health > maxHealth) health = maxHealth;
    }

    public abstract void heal();
    public abstract void destroyed();

    public void printStatus(String name) {
        System.out.printf("%s (Lv%d) -> Health: %.2f%n", name, level, health);
        if (isDefeated()) {
            System.out.printf("%s kalah!%n", name);
        }
    }

    public String getZombieInfo() {
        String className = getClass().getSimpleName().replace("Zombie", " Zombie");
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(" Data =\n");
        sb.append("Health = ").append((int) health).append("\n");
        sb.append("Level = ").append(level);
        if (isDefeated()) sb.append("\n\n").append("zombie telah dikalahkan");
        return sb.toString();
    }
}
