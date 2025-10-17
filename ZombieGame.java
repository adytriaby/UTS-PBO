// ====== ZombieGame.java ======
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

class WalkingZombie extends Zombie {
    public WalkingZombie(int level, double health) { super(level, health); }

    @Override
    public void heal() {
        double pct;
        switch (level) {
            case 1: pct = 0.20; break;
            case 2: pct = 0.30; break;
            case 3: pct = 0.40; break;
            default: pct = 0.20;
        }
        health += health * pct;
        clamp();
    }

    @Override
    public void destroyed() {
        health -= maxHealth * 0.20;
        clamp();
    }
}

class JumpingZombie extends Zombie {
    public JumpingZombie(int level, double health) { super(level, health); }

    @Override
    public void heal() {
        double pct;
        switch (level) {
            case 1: pct = 0.30; break;
            case 2: pct = 0.40; break;
            case 3: pct = 0.50; break;
            default: pct = 0.30;
        }
        health += health * pct;
        clamp();
    }

    @Override
    public void destroyed() {
        health -= maxHealth * 0.10;
        clamp();
    }
}

class Barrier {
    private int strength;

    public Barrier(int strength) { this.strength = Math.max(0, strength); }

    public void destroyed() {
        strength -= 9;
        if (strength < 0) strength = 0;
    }

    public boolean isDefeated() { return strength <= 0; }

    public void printStatus() {
        System.out.printf("Barrier -> Strength: %d%n", strength);
        if (isDefeated()) {
            System.out.println("Barrier kalah!");
        }
    }

    public String getBarrierInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Barrier Strength = ").append(strength);
        if (isDefeated()) sb.append("\n\nbarrier telah dikalahkan");
        return sb.toString();
    }
}

class Plant {
    public void attack(WalkingZombie z) {
        z.destroyed();
        System.out.println("Plant menyerang WalkingZombie (-20%)");
        z.printStatus("WalkingZombie");
    }

    public void attack(JumpingZombie z) {
        z.destroyed();
        System.out.println("Plant menyerang JumpingZombie (-10%)");
        z.printStatus("JumpingZombie");
    }

    public void attack(Barrier b) {
        b.destroyed();
        System.out.println("Plant menyerang Barrier (-9 strength)");
        b.printStatus();
    }

    public void doDestroy(WalkingZombie z) { z.destroyed(); }
    public void doDestroy(JumpingZombie z) { z.destroyed(); }
    public void doDestroy(Barrier b) { b.destroyed(); }
}

class Tester {
    public static void main(String[] args) {
        WalkingZombie wz = new WalkingZombie(1, 100);
        JumpingZombie jz = new JumpingZombie(2, 100);
        Barrier b = new Barrier(100);
        Plant p = new Plant();

        System.out.println("" + wz.getZombieInfo());
        System.out.println();
        System.out.println("" + jz.getZombieInfo());
        System.out.println();
        System.out.println("" + b.getBarrierInfo());
        System.out.println("-------------------------");

        for (int i = 0; i < 4; i++) {
            p.doDestroy(wz);
            p.doDestroy(jz);
            p.doDestroy(b);
        }

        System.out.println();
        System.out.println("" + wz.getZombieInfo());
        System.out.println();
        System.out.println("" + jz.getZombieInfo());
        System.out.println();
        System.out.println("" + b.getBarrierInfo());

        while (!wz.isDefeated()) p.doDestroy(wz);
        while (!jz.isDefeated()) p.doDestroy(jz);
        while (!b.isDefeated()) p.doDestroy(b);

        System.out.println();
        System.out.println("-- Setelah dilanjutkan sampai kalah --");
        System.out.println("" + wz.getZombieInfo());
        System.out.println();
        System.out.println("" + jz.getZombieInfo());
        System.out.println();
        System.out.println("" + b.getBarrierInfo());
    }
}

public class ZombieGame {
    public static void main(String[] args) {
        WalkingZombie wz = new WalkingZombie(2, 100); // Lv2: heal +30%, destroy -20%
        JumpingZombie jz = new JumpingZombie(1, 100); // Lv1: heal +30%, destroy -10%
        Barrier br = new Barrier(30);

        Plant peashooter = new Plant();

        wz.printStatus("WalkingZombie");
        jz.printStatus("JumpingZombie");
        br.printStatus();
        System.out.println("==== Aksi Dimulai ====");

        peashooter.attack(wz);
        peashooter.attack(jz);
        peashooter.attack(br);

        System.out.println("-- Zombie menyembuhkan diri --");
        wz.heal();
        jz.heal();
        wz.printStatus("WalkingZombie");
        jz.printStatus("JumpingZombie");

        System.out.println("-- Serangan lanjutan --");
        while (!wz.isDefeated()) peashooter.attack(wz);
        while (!jz.isDefeated()) peashooter.attack(jz);
        while (!br.isDefeated()) peashooter.attack(br);
    }
}
