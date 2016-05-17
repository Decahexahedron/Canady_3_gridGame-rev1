package pkg2darrays;

public class Enemy {

    private int x, y, hp;
    private boolean alive, stun;

    public boolean isAlive() {
        return alive;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    Enemy(int a, int b, int c) {
        this.x = a;
        this.y = b;
        this.hp = c;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

}
