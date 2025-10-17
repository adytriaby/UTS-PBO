// ====== Plant.java ======
public class Plant {
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
