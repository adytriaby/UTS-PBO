// ====== ZombieGame.java ======
// main class kept here; other classes moved to their own files.

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
