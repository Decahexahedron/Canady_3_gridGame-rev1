package pkg2darrays;

public class Player {

    private int x, nx, y, ny;
    public int level = 0, hp, jumps;
    private boolean isAlive, hasAtk, hasJump, hasStun;
    Player(int a, int b){
        this.x = a;
        this.y = b;
    }
    public int getX() {
        return x;
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
