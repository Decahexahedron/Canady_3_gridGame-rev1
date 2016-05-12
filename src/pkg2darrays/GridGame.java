package pkg2darrays;

import java.util.Scanner;

public class GridGame {

    public static int maxX, maxY, score, counter;
    static String dinput;
    static String direction;
    static Scanner sc = new Scanner(System.in);
    public static boolean game, enemy1, enemy2, enemy3, stun, stun1, stun2, stun3;
    public static Player player;
    String save = "░░";
    
    public static void main(String[] args) {
        game = true;
        enemy1 = true;
        enemy2 = true;
        enemy3 = true;
        score = 0;
        maxX = 30;
        maxY = 30;
        counter = 0;
        stun = true;
        player = new Player();

        String[][] map = new String[maxX][maxY];
        boolean[][] trap = new boolean[maxX][maxY];
        boolean[][] treasure = new boolean[maxX][maxY];
        int[][] enemy = new int[maxX][maxY];

        player.setX(4);
        player.setY(4);

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
        for (int e = 0; e < 2; e++) {
            int enemyx = (int) Math.floor(Math.random() * maxX);
            int enemyy = (int) Math.floor(Math.random() * maxY);
            enemy[e][1] = enemyx;
            enemy[e][0] = enemyy;
        }
        player.setNx(player.getX());
        player.setNy(player.getX());
        System.out.println("Traps: ##, Enemies: XK, Treasure: %%" + "\n" + "Collect 10 points to win.");
        map[player.getX()][player.getY()] = "██";
        grid(map, trap, treasure, enemy);
        while (game) {
            input(map, trap, treasure, enemy);
            enemies(map, trap, treasure, enemy);
            grid(map, trap, treasure, enemy);
            if (stun) {
                counter++;
            }
            clear(map, trap);
            if (score == 10) {
                System.out.println("__   _____  _   _  __        _____ _   _ _ \n\\ \\ / / _ \\| | | | \\ \\      / /_ _| \\ | | |\n"
                        + " \\ V / | | | | | |  \\ \\ /\\ / / | ||  \\| | |\n"
                        + "  | || |_| | |_| |   \\ V  V /  | || |\\  |_|\n"
                        + "  |_| \\___/ \\___/     \\_/\\_/  |___|_| \\_(_)");
                game = false;
            }
        }
        if (!game && score < 10) {
            System.out.println("__   _____  _   _   _     ___  ____ _____ \n\\ \\ / / _ \\| | | | | |   / _ \\/ ___|_   _|\n"
                    + " \\ V / | | | | | | | |  | | | \\___ \\ | |  \n"
                    + "  | || |_| | |_| | | |__| |_| |___) || |  \n"
                    + "  |_| \\___/ \\___/  |_____\\___/|____/ |_|  \n" + "Your score was: " + score);
        }
    } // end of main

    public static void grid(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {

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

                if (j < map[1].length - 1) {
                    if (map[i][j] != "██" && map[i][j] != "##" && map[i][j] != "%%" && map[i][j] != "XK"
                            && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                            && map[i][j] != "<^" && map[i][j] != "//" && map[i][j] != "**") {
                        System.out.print("∶∶");
                    } else {
                        System.out.print(map[i][j]);
                    }

                } else if (map[i][j] != "██" && map[i][j] != "##" && map[i][j] != "%%" && map[i][j] != "XK"
                        && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                        && map[i][j] != "<^" && map[i][j] != "//" && map[i][j] != "**") {
                    System.out.println("∶∶");
                } else {
                    System.out.println(map[i][j]);
                }
            }
        }

    } //end of grid

    public static void enemies(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        if (trap[enemy[0][1]][enemy[0][0]]) {
            enemy1 = false;
        }
        if (trap[enemy[1][1]][enemy[1][0]]) {
            enemy2 = false;
        }
        if (trap[enemy[2][1]][enemy[2][0]]) {
            enemy3 = false;
        }

        if (enemy[0][0] > player.getX() && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "∶∶";
            enemy[0][0]--;
        }
        if (enemy[0][0] < player.getX() && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "∶∶";
            enemy[0][0]++;
        }
        if (enemy[0][1] > player.getY() && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "∶∶";
            enemy[0][1]--;
        }
        if (enemy[0][1] < player.getY() && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "∶∶";
            enemy[0][1]++;
        }
        if (enemy[1][0] > player.getX() && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "∶∶";
            enemy[1][0]--;
        }
        if (enemy[1][0] < player.getX() && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "∶∶";
            enemy[1][0]++;
        }
        if (enemy[1][1] > player.getY() && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "∶∶";
            enemy[1][1]--;
        }
        if (enemy[1][1] < player.getY() && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "∶∶";
            enemy[1][1]++;
        }
        if (enemy[2][0] > player.getX() && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "∶∶";
            enemy[2][0]--;
        }
        if (enemy[2][0] < player.getX() && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "∶∶";
            enemy[2][0]++;
        }
        if (enemy[2][1] > player.getY() && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "∶∶";
            enemy[2][1]--;
        }
        if (enemy[2][1] < player.getY() && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "∶∶";
            enemy[2][1]++;
        }
        if (map[enemy[0][1]][enemy[0][0]] == "**" && stun) {
            stun1 = true;
        }
        if (map[enemy[1][1]][enemy[1][0]] == "**" && stun) {
            stun2 = true;
        }
        if (map[enemy[2][1]][enemy[2][0]] == "**" && stun) {
            stun3 = true;
        }
        if (enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "XK";
        } else if (!enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "∶∶";
        }
        if (enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "XK";
        } else if (!enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "∶∶";
        }
        if (enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "XK";
        } else if (!enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "∶∶";
        }

        if (enemy[0][1] == player.getY() && enemy[0][0] == player.getX() && enemy1
                || enemy[1][1] == player.getY() && enemy[1][0] == player.getX() && enemy2
                || enemy[2][1] == player.getY() && enemy[2][0] == player.getX() && enemy2) {
            game = false;
        }

        if (trap[enemy[0][1]][enemy[0][0]]) {
            enemy1 = false;
        }
        if (trap[enemy[1][1]][enemy[1][0]]) {
            enemy2 = false;
        }
        if (trap[enemy[2][1]][enemy[2][0]]) {
            enemy3 = false;
        }

    }

    public static void move(String[][] map, int movex, int movey, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
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
                    stun1 = false;
                    stun2 = false;
                    stun3 = false;
                }
            }
        }
    }

    public static void input(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        System.out.print("Enter direction (W, A, S, D or diagonals), F to attack, G to stun, your score is " + score + ": ");
        dinput = sc.nextLine().toLowerCase();

        if (dinput.equalsIgnoreCase("w") && player.getY() != maxX - 1) {
            move(map, 0, -1, trap, treasure, enemy);
            direction = "w";
        } else if (dinput.equalsIgnoreCase("d") && player.getX() != maxX - 1) {
            move(map, 1, 0, trap, treasure, enemy);
            direction = "d";
        } else if (dinput.equalsIgnoreCase("s") && player.getY() != maxX - 1) {
            move(map, 0, 1, trap, treasure, enemy);
            direction = "s";
        } else if (dinput.equalsIgnoreCase("a") && player.getX() != 0) {
            move(map, -1, 0, trap, treasure, enemy);
            direction = "a";
        } else if (dinput.equalsIgnoreCase("wd") && player.getY() != 0 && player.getX() != maxX - 1
                || dinput.equalsIgnoreCase("dw") && player.getY() != 0 && player.getX() != maxX - 1) {
            move(map, 1, -1, trap, treasure, enemy);
            direction = "wd";
        } else if (dinput.equalsIgnoreCase("wa") && player.getY() != 0 && player.getX() != 0
                || dinput.equalsIgnoreCase("aw") && player.getY() != 0 && player.getX() != 0) {
            move(map, -1, -1, trap, treasure, enemy);
            direction = "wa";
        } else if (dinput.equalsIgnoreCase("sd") && player.getY() != maxX - 1 && player.getX() != maxX - 1
                || dinput.equalsIgnoreCase("ds") && player.getY() != maxX - 1 && player.getX() != maxX - 1) {
            move(map, 1, 1, trap, treasure, enemy);
            direction = "sd";
        } else if (dinput.equalsIgnoreCase("sa") && player.getY() != maxX - 1 && player.getX() != 0
                || dinput.equalsIgnoreCase("as") && player.getY() != maxX - 1 && player.getX() != 0) {
            move(map, -1, 1, trap, treasure, enemy);
            direction = "sa";
        } else if (dinput.equalsIgnoreCase("f") && direction == "w") {
            map[player.getY() - 1][player.getX()] = "^^";
            trap[player.getY() - 1][player.getX()] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "s") {
            map[player.getY() + 1][player.getX()] = "vv";
            trap[player.getY() + 1][player.getX()] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "d") {
            map[player.getY()][player.getX() + 1] = ">>";
            trap[player.getY()][player.getX() + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "a") {
            map[player.getY()][player.getX() - 1] = "<<";
            trap[player.getY()][player.getX() - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wd") {
            map[player.getY() - 1][player.getX() + 1] = "//";
            trap[player.getY() - 1][player.getX() + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wa") {
            map[player.getY() - 1][player.getX() - 1] = "<^";
            trap[player.getY() - 1][player.getX() - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sd") {
            map[player.getY() + 1][player.getX() + 1] = "<^";
            trap[player.getY() + 1][player.getX() + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sa") {
            map[player.getY() + 1][player.getX() - 1] = "//";
            trap[player.getY() + 1][player.getX() - 1] = true;
        } else if (dinput.contains("f") && dinput.contains("wd")) {
            map[player.getY() - 1][player.getX() + 1] = "//";
            trap[player.getY() - 1][player.getX() + 1] = true;
        } else if (dinput.contains("f") && dinput.contains("wa")) {
            map[player.getY() - 1][player.getX() - 1] = "<^";
            trap[player.getY() - 1][player.getX() - 1] = true;
        } else if (dinput.contains("f") && dinput.contains("sd")) {
            map[player.getY() + 1][player.getX() + 1] = "<^";
            trap[player.getY() + 1][player.getX() + 1] = true;
        } else if (dinput.contains("f") && dinput.contains("sa")) {
            map[player.getY() + 1][player.getX() - 1] = "//";
            trap[player.getY() + 1][player.getX() - 1] = true;
        } else if (dinput.contains("f") && dinput.contains("w")) {
            map[player.getY() - 1][player.getX()] = "^^";
            trap[player.getY() - 1][player.getX()] = true;
        } else if (dinput.contains("f") && dinput.contains("s")) {
            map[player.getY() + 1][player.getX()] = "vv";
            trap[player.getY() + 1][player.getX()] = true;
        } else if (dinput.contains("f") && dinput.contains("d")) {
            map[player.getY()][player.getX() + 1] = ">>";
            trap[player.getY()][player.getX() + 1] = true;
        } else if (dinput.contains("f") && dinput.contains("a")) {
            map[player.getY()][player.getX() - 1] = "<<";
            trap[player.getY()][player.getX() - 1] = true;
        } else if (dinput.equalsIgnoreCase("g")) {
//            stun = true;
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
            input(map, trap, treasure, enemy);
        }
        if (game) {
            map[player.getY()][player.getX()] = "██";
        } else {
            System.out.println("Game over");
        }
    } //end of input
}
