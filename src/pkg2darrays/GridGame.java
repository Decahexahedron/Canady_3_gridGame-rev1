package pkg2darrays;

import java.util.Scanner;
import java.util.ArrayList;

public class GridGame {

    public static int maxX, maxY, score, counter;
    static String dinput, level;
    static String direction;
    static Scanner sc = new Scanner(System.in);
    public static boolean game, stun;
    public static Player player;
    public static Enemy enemya, enemyb, enemyc;
    String save = "░░";
    public static ArrayList<Enemy> enemylist = new ArrayList();

    public static void main(String[] args) {
        game = true;
        score = 0;
        maxX = 30;
        maxY = 30;
        counter = 0;
        stun = true;
        player = new Player(4, 4);

        String[][] map = new String[maxX][maxY];
        boolean[][] trap = new boolean[maxX][maxY];
        boolean[][] wall = new boolean[maxX][maxY];
        boolean[][] treasure = new boolean[maxX][maxY];
        int[][] enemy = new int[maxX][maxY];
        setup(trap, treasure, wall);
        begin();
        map[player.getX()][player.getY()] = "██";
        grid(map, trap, treasure, enemy, wall);

        while (game) {
            input(map, trap, treasure, enemy, wall);
            enemies(map, trap, treasure, enemy, wall);
            grid(map, trap, treasure, enemy, wall);
            if (stun) {
                counter++;
            }
            clear(map, trap);
            if (score == 10) {
                win();
            }
        }
        if (!game && score < 10) {
            lose();
        }
    } // end of main

    public static void begin() {
        System.out.println("Traps: ##, Enemies: XK, Treasure: %%" + "\n" + "Collect 10 points to win.");
        System.out.println("Choose your first ability! Jump, attack, or stun (type j, a, or s).");
        player.setLevel(0);
        level = sc.nextLine().toLowerCase();
        if (level.equals("j")) {
            player.setHasJump(true);
        } else if (level.equals("a")) {
            player.setHasAtk(true);
        } else if (level.equals("s")) {
            player.setHasStun(true);
        } else {
            System.out.println("Try an input I asked for, nerd");
            begin();
        }
    }

    public static void levelup() {
        System.out.println("You leveled up!");
        
        if (player.isHasAtk()) {
            System.out.println("You can now unlock stun or jump.");
        } else if (player.isHasStun()) {
            System.out.println("You can unlock attack or jump, or upgrade your stun.");
        } else if (player.isHasJump()) {
            System.out.println("You can unlock attack or stun, or upgrade your jump");
        }
    }

    public static void win() {
        System.out.println("__   _____  _   _  __        _____ _   _ _ \n"
                + "\\ \\ / / _ \\| | | | \\ \\      / /_ _| \\ | | |\n"
                + " \\ V / | | | | | |  \\ \\ /\\ / / | ||  \\| | |\n"
                + "  | || |_| | |_| |   \\ V  V /  | || |\\  |_|\n"
                + "  |_| \\___/ \\___/     \\_/\\_/  |___|_| \\_(_)");
        game = false;
    }

    public static void lose() {
        System.out.println("__   _____  _   _   _     ___  ____ _____ \n"
                + "\\ \\ / / _ \\| | | | | |   / _ \\/ ___|_   _|\n"
                + " \\ V / | | | | | | | |  | | | \\___ \\ | |  \n"
                + "  | || |_| | |_| | | |__| |_| |___) || |  \n"
                + "  |_| \\___/ \\___/  |_____\\___/|____/ |_|  \n" + "Your score was: " + score);
    }

    public static void setup(boolean[][] trap, boolean[][] treasure, boolean[][] wall) {
        wall[4][2] = true;
        if (trap[player.getY()][player.getY()] == true) {
            player.setX((int) Math.floor(Math.random() * maxX));
            player.setY((int) Math.floor(Math.random() * maxY));
        }
        for (int t = 0; t < 50; t++) {
            int trapx = (int) Math.floor(Math.random() * maxX);
            int trapy = (int) Math.floor(Math.random() * maxY);
            trap[trapx][trapy] = true;
        }
        for (int t = 0; t < 11; t++) {
            int treasurex = (int) Math.floor(Math.random() * maxX);
            int treasurey = (int) Math.floor(Math.random() * maxY);
            treasure[treasurex][treasurey] = true;
        }
        for (int e = 0; e < 3; e++) {
            int enemyx = (int) Math.floor(Math.random() * maxX);
            int enemyy = (int) Math.floor(Math.random() * maxY);
            if (e == 0) {
                enemya = new Enemy(enemyx, enemyy, 1);
                enemylist.add(enemya);
            } else if (e == 1) {
                enemyb = new Enemy(enemyx, enemyy, 1);
                enemylist.add(enemyb);
            } else if (e == 2) {
                enemyc = new Enemy(enemyx, enemyy, 1);
                enemylist.add(enemyc);
            }
        }
        player.setNx(player.getX());
        player.setNy(player.getX());
        enemya.setAlive(true);
        enemyb.setAlive(true);
        enemyc.setAlive(true);
    }

    public static void help() {
        System.out.println("Pressing g will stun (if stun is unlocked) in a 3x3 around the player.\n"
                + "It only stuns on the first turn it is active, but will stun an enemy for 3 turns.\n"
                + "Upgrading stun will increase the turns an enemy is stunned.\n"
                + "Attacking by just typing f will attack in the direction you just moved.\n"
                + "Typing a direction and f in one input will pivot-attack, or attack in that direction without moving.\n"
                + "Diagonal pivot-attacks only work if you type the vertical direction first.");
    }

    public static void grid(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy, boolean[][] wall) {

        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (trap[j][i]
                        && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                        && map[i][j] != "<^" && map[i][j] != "//") {
                    map[i][j] = "##";
                }
                if (treasure[j][i]) {
                    map[i][j] = "%%";
                }
                if (trap[j][i] && treasure[j][i]) {
                    trap[j][i] = false;
                }
                if (wall[j][i]) {
                    map[i][j] = "░░";
                }
                if (j < map[1].length - 1) {
                    if (map[i][j] != "██" && map[i][j] != "##" && map[i][j] != "%%" && map[i][j] != "XK"
                            && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                            && map[i][j] != "<^" && map[i][j] != "//" && map[i][j] != "**" && map[i][j] != "░░") {
                        System.out.print("∶∶");
                    } else {
                        System.out.print(map[i][j]);
                    }

                } else if (map[i][j] != "██" && map[i][j] != "##" && map[i][j] != "%%" && map[i][j] != "XK"
                        && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                        && map[i][j] != "<^" && map[i][j] != "//" && map[i][j] != "**" && map[i][j] != "░░") {
                    System.out.println("∶∶");
                } else {
                    System.out.println(map[i][j]);
                }
            }
        }

    } //end of grid

    public static void enemies(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy, boolean[][] wall) {

        for (Enemy e : enemylist) {
            if (trap[e.getY()][e.getX()]) {
                e.setAlive(false);
            }
            if (e.getX() > player.getX() && !e.isStun() && e.isAlive()) {
                map[e.getY()][e.getX()] = "∶∶";
                e.setX(e.getX() - 1);
                if (wall[e.getX()][e.getY()]) {
                    e.setX(e.getX() + 1);
                    map[e.getY()][e.getX()] = "XK";
                }
            }
            if (e.getX() < player.getX() && !e.isStun() && e.isAlive()) {
                map[e.getY()][e.getX()] = "∶∶";
                e.setX(e.getX() + 1);
                if (wall[e.getX()][e.getY()]) {
                    e.setX(e.getX() - 1);
                    map[e.getY()][e.getX()] = "XK";
                }
            }
            if (e.getY() > player.getY() && !e.isStun() && e.isAlive()) {
                map[e.getY()][e.getX()] = "∶∶";
                e.setY(e.getY() - 1);
                if (wall[e.getX()][e.getY()]) {
                    e.setY(e.getY() + 1);
                    map[e.getY()][e.getX()] = "XK";
                }
            }
            if (e.getY() < player.getY() && !e.isStun() && e.isAlive()) {
                map[e.getY()][e.getX()] = "∶∶";
                e.setY(e.getY() + 1);
                if (wall[e.getX()][e.getY()]) {
                    e.setY(e.getY() - 1);
                    map[e.getY()][e.getX()] = "XK";
                }
            }
            if (map[e.getY()][e.getX()] == "**" && stun) {
                e.setStun(true);
            }
            if (e.isAlive()) {
                map[e.getY()][e.getX()] = "XK";
            } else if (!e.isAlive()) {
                map[e.getY()][e.getX()] = "∶∶";
            }
            if (e.getY() == player.getY() && e.getX() == player.getX() && e.isAlive()) {
                game = false;
            }
            if (trap[e.getY()][e.getX()] && e.isAlive()) {
                e.setAlive(false);
                levelup();
            }
        }
    }

    public static void move(String[][] map, int movex, int movey, boolean[][] trap, boolean[][] treasure, int[][] enemy, boolean[][] wall) {
        map[player.getY()][player.getX()] = "∶∶";
        player.setNx(player.getNx() + movex);
        player.setNy(player.getNy() + movey);
        if (trap[player.getNx()][player.getNy()]) {
            game = false;
        }
        if (treasure[player.getNx()][player.getNy()]) {
            map[player.getNx()][player.getNy()] = "∶∶";
            score++;
            treasure[player.getNx()][player.getNy()] = false;
        }
        if (wall[player.getNx()][player.getNy()]) {
            movex = 0;
            movey = 0;
            player.setNx(player.getX());
            player.setNy(player.getY());
        }
        player.setX(player.getX() + movex);
        player.setY(player.getY() + movey);
    }

    public static void clear(String[][] map, boolean[][] trap) {
        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (map[i][j] == "^^" || map[i][j] == "vv" || map[i][j] == ">>" || map[i][j] == "<<"
                        || map[i][j] == "<^" || map[i][j] == "//") {
                    trap[i][j] = false;
                    map[i][j] = "∶∶";
                }
                if (map[i][j] == "**" && counter == 3) {
                    map[i][j] = "∶∶";
                    stun = false;
                    enemya.setStun(false);
                    enemyb.setStun(false);
                    enemyc.setStun(false);
                }
            }
        }
    }

    public static void input(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy, boolean[][] wall) {
        System.out.print("Enter direction (W, A, S, D or diagonals), F to attack, G to stun, your score is " + score + ": ");
        dinput = sc.nextLine().toLowerCase();
        if (dinput.equalsIgnoreCase("help")) {
            help();
        }
        if (dinput.equalsIgnoreCase("w") && player.getY() != maxX - 1) {
            move(map, 0, -1, trap, treasure, enemy, wall);
            direction = "w";
        } else if (dinput.equalsIgnoreCase("d") && player.getX() != maxX - 1) {
            move(map, 1, 0, trap, treasure, enemy, wall);
            direction = "d";
        } else if (dinput.equalsIgnoreCase("s") && player.getY() != maxX - 1) {
            move(map, 0, 1, trap, treasure, enemy, wall);
            direction = "s";
        } else if (dinput.equalsIgnoreCase("a") && player.getX() != 0) {
            move(map, -1, 0, trap, treasure, enemy, wall);
            direction = "a";
        } else if (dinput.equalsIgnoreCase("wd") && player.getY() != 0 && player.getX() != maxX - 1
                || dinput.equalsIgnoreCase("dw") && player.getY() != 0 && player.getX() != maxX - 1) {
            move(map, 1, -1, trap, treasure, enemy, wall);
            direction = "wd";
        } else if (dinput.equalsIgnoreCase("wa") && player.getY() != 0 && player.getX() != 0
                || dinput.equalsIgnoreCase("aw") && player.getY() != 0 && player.getX() != 0) {
            move(map, -1, -1, trap, treasure, enemy, wall);
            direction = "wa";
        } else if (dinput.equalsIgnoreCase("sd") && player.getY() != maxX - 1 && player.getX() != maxX - 1
                || dinput.equalsIgnoreCase("ds") && player.getY() != maxX - 1 && player.getX() != maxX - 1) {
            move(map, 1, 1, trap, treasure, enemy, wall);
            direction = "sd";
        } else if (dinput.equalsIgnoreCase("sa") && player.getY() != maxX - 1 && player.getX() != 0
                || dinput.equalsIgnoreCase("as") && player.getY() != maxX - 1 && player.getX() != 0) {
            move(map, -1, 1, trap, treasure, enemy, wall);
            direction = "sa";
        } else if (dinput.equalsIgnoreCase("f") && direction == "w" && player.isHasAtk()) {
            map[player.getY() - 1][player.getX()] = "^^";
            trap[player.getY() - 1][player.getX()] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "s" && player.isHasAtk()) {
            map[player.getY() + 1][player.getX()] = "vv";
            trap[player.getY() + 1][player.getX()] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "d" && player.isHasAtk()) {
            map[player.getY()][player.getX() + 1] = ">>";
            trap[player.getY()][player.getX() + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "a" && player.isHasAtk()) {
            map[player.getY()][player.getX() - 1] = "<<";
            trap[player.getY()][player.getX() - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wd" && player.isHasAtk()) {
            map[player.getY() - 1][player.getX() + 1] = "//";
            trap[player.getY() - 1][player.getX() + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wa" && player.isHasAtk()) {
            map[player.getY() - 1][player.getX() - 1] = "<^";
            trap[player.getY() - 1][player.getX() - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sd" && player.isHasAtk()) {
            map[player.getY() + 1][player.getX() + 1] = "<^";
            trap[player.getY() + 1][player.getX() + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sa" && player.isHasAtk()) {
            map[player.getY() + 1][player.getX() - 1] = "//";
            trap[player.getY() + 1][player.getX() - 1] = true;
        } else if (dinput.contains("f") && dinput.contains("wd") && player.isHasAtk()) {
            map[player.getY() - 1][player.getX() + 1] = "//";
            trap[player.getY() - 1][player.getX() + 1] = true;
        } else if (dinput.contains("f") && dinput.contains("wa") && player.isHasAtk()) {
            map[player.getY() - 1][player.getX() - 1] = "<^";
            trap[player.getY() - 1][player.getX() - 1] = true;
        } else if (dinput.contains("f") && dinput.contains("sd") && player.isHasAtk()) {
            map[player.getY() + 1][player.getX() + 1] = "<^";
            trap[player.getY() + 1][player.getX() + 1] = true;
        } else if (dinput.contains("f") && dinput.contains("sa") && player.isHasAtk()) {
            map[player.getY() + 1][player.getX() - 1] = "//";
            trap[player.getY() + 1][player.getX() - 1] = true;
        } else if (dinput.contains("f") && dinput.contains("w") && player.isHasAtk()) {
            map[player.getY() - 1][player.getX()] = "^^";
            trap[player.getY() - 1][player.getX()] = true;
        } else if (dinput.contains("f") && dinput.contains("s") && player.isHasAtk()) {
            map[player.getY() + 1][player.getX()] = "vv";
            trap[player.getY() + 1][player.getX()] = true;
        } else if (dinput.contains("f") && dinput.contains("d") && player.isHasAtk()) {
            map[player.getY()][player.getX() + 1] = ">>";
            trap[player.getY()][player.getX() + 1] = true;
        } else if (dinput.contains("f") && dinput.contains("a") && player.isHasAtk()) {
            map[player.getY()][player.getX() - 1] = "<<";
            trap[player.getY()][player.getX() - 1] = true;
        } else if (dinput.equalsIgnoreCase("g") && player.isHasStun()) {
            counter = 0;
            map[player.getY() - 1][player.getX()] = "**";
            map[player.getY() + 1][player.getX()] = "**";
            map[player.getY()][player.getX() + 1] = "**";
            map[player.getY()][player.getX() - 1] = "**";
            map[player.getY() - 1][player.getX() + 1] = "**";
            map[player.getY() - 1][player.getX() - 1] = "**";
            map[player.getY() + 1][player.getX() + 1] = "**";
            map[player.getY() + 1][player.getX() - 1] = "**";
        } else {
            System.out.println("Sorry, wrong input or reached map edge");
            input(map, trap, treasure, enemy, wall);
        }
        if (game) {
            map[player.getY()][player.getX()] = "██";
        } else {
            System.out.println("Game over");
        }
    } //end of input
}
