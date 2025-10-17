// ====== Tester.java ======
public class Tester {
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
