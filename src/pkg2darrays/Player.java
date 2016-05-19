package pkg2darrays;

public class Player {

    private int x, nx, y, ny;
    private int level, hp, jumps, stunLevel, jumpLevel;
    private boolean isAlive, hasAtk, hasJump, hasStun;

    public int getStunLevel() {
        return stunLevel;
    }

    public int getJumpLevel() {
        return jumpLevel;
    }

    Player(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public void setStunLevel(int stunLevel) {
        this.stunLevel = stunLevel;
    }

    public void setJumpLevel(int jumpLevel) {
        this.jumpLevel = jumpLevel;
    }

    public int getX() {
        return x;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setHasAtk(boolean hasAtk) {
        this.hasAtk = hasAtk;
    }

    public void setHasJump(boolean hasJump) {
        this.hasJump = hasJump;
    }

    public void setHasStun(boolean hasStun) {
        this.hasStun = hasStun;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getJumps() {
        return jumps;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public boolean isHasAtk() {
        return hasAtk;
    }

    public boolean isHasJump() {
        return hasJump;
    }

    public boolean isHasStun() {
        return hasStun;
    }

    public int getNx() {
        return nx;
    }

    public int getY() {
        return y;
    }

    public int getNy() {
        return ny;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }

}
