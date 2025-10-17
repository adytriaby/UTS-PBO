// ====== Barrier.java ======
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
