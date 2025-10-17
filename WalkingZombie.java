// ====== WalkingZombie.java ======
public class WalkingZombie extends Zombie {
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
