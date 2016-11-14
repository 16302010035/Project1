import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pj {
    public static void combine(char[][] s5, char[][] s6, String[][] s7) {
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 9; j++) {
                if (s5[i][j] != '0') {
                    switch (s5[i][j]) {
                        case '1':
                            if (j < 4)
                                s7[i][j] = "1鼠 1";
                            else s7[i][j] = " 鼠12";//用1和2区分敌我双方。
                            break;
                        case '2':
                            if (j < 4)
                                s7[i][j] = "2猫 1";
                            else s7[i][j] = " 猫22";
                            break;
                        case '3':
                            if (j < 4)
                                s7[i][j] = "3狼 1";
                            else s7[i][j] = " 狼32";
                            break;
                        case '4':
                            if (j < 4)
                                s7[i][j] = "4狗 1";
                            else s7[i][j] = " 狗42";
                            break;
                        case '5':
                            if (j < 4)
                                s7[i][j] = "5豹 1";
                            else s7[i][j] = " 豹52";
                            break;
                        case '6':
                            if (j < 4)
                                s7[i][j] = "6虎 1";
                            else s7[i][j] = " 虎62";
                            break;
                        case '7':
                            if (j < 4)
                                s7[i][j] = "7狮 1";
                            else s7[i][j] = " 狮72";
                            break;
                        case '8':
                            if (j < 4)
                                s7[i][j] = "8象 1";
                            else s7[i][j] = " 象82";
                            break;

                    }
                } else if (s6[i][j] != '0') {
                    switch (s6[i][j]) {
                        case '1':
                            s7[i][j] = " 水 5";//多一个空格，避免数组出界。5表示水。
                            break;
                        case '2':
                            s7[i][j] = " 阱 7";//7代表左方陷阱
                            break;
                        case '3':
                            s7[i][j] = " 家 左";
                            break;
                        case '4':
                            s7[i][j] = " 阱 8";//8代表右方陷阱
                            break;
                        case '5':
                            s7[i][j] = " 家 右";
                    }
                } else s7[i][j] = " 　 3";

            }
    }

    public static void print(int a) {
        if (a % 2 == 0)
            System.out.print("左方玩家行动： ");
        else System.out.print("右方玩家行动： ");
    }

    public static Boolean outside(String s3, int i, int j) {

        switch (s3.charAt(1)) {
            case 'a':
                if (j - 1 < 0) return true;
                break;
            case 'w':
                if (i - 1 < 0) return true;
                break;
            case 's':
                if (i + 1 > 6) return true;
                break;
            case 'd':
                if (j + 1 > 8) return true;
                break;

        }
        return false;
    }

    public static void printfirst() {
        System.out.println("斗兽棋\n" +
                "\n" +
                "指令介绍:\n" +
                "\n" +
                "1.移动指令:\n" +
                "         移动指令由两个部分组成\n" +
                "         第一个部分是数字1-8，根据战斗力分别对应鼠(1),猫（2），狼（3）；狗（4）；豹（5）；虎（6）；狮（7）；象（8）\n" +
                "         第二个部分是字母wasd中的一个，w对应上方向，a对应左方向，s对应下方向，d对应右方向\n" +
                "         比如指令1d表示鼠向右走，4w表示狗向左走\n" +
                "\n" +
                "2.游戏指令:\n" +
                "          输入 restart 重新开始\n" +
                "          输入 help 查看帮助\n" +
                "          输入 undo  悔棋\n" +
                "          输入 redo  取消悔棋\n" +
                "          输入 exit  退出游戏\n");
    }

    public static Boolean eat(String[][] s3, String s4, int a, int b, char s5) {
        switch (s4.charAt(1)) {
            case 'a':
                if (s3[a][b - 1].charAt(3) == s5) return false;
                break;
            case 'w':
                if (s3[a - 1][b].charAt(3) == s5) return false;
                break;
            case 's':
                if (s3[a + 1][b].charAt(3) == s5) return false;
                break;
            case 'd':
                if (s3[a][b + 1].charAt(3) == s5) return false;
                break;
        }
        return true;
    }

    public static Boolean eat2(String[][] s3, String[][][] s5, String s4, int a, int b) {
        if (s3[a][b].charAt(1) == '鼠' && s5[0][a][b].charAt(1) == '水') {
            switch (s4.charAt(1)) {
                case 'a':
                    if (s3[a][b - 1].charAt(1) == '象') return false;
                    break;
                case 'w':
                    if (s3[a - 1][b].charAt(1) == '象') return false;
                    break;
                case 's':
                    if (s3[a + 1][b].charAt(1) == '象') return false;
                    break;
                case 'd':
                    if (s3[a][b + 1].charAt(1) == '象') return false;
                    break;
            }
        }
        return true;
    }

    public static Boolean eat1(String[][] s3, String[][][] s5, String s4, int a, int b, int c, int d, char e, char f) {//board string  c=2 d=0
        switch (s4.charAt(1)) {
            case 'a':
                if (s5[0][a][b - 1].charAt(3) == e) return true;
                if (s3[a][b - 1].charAt(3) == f) return true;
                if (s3[a][b - 1].charAt(3) == '5') return true;
                if (s3[a][b - 1].charAt(3) == '3') return true;
                if (s3[a][b].charAt(d) == '1' && s3[a][b - 1].charAt(c) == '8') return true;
                if (s3[a][b].charAt(d) == '8' && s3[a][b - 1].charAt(c) == '1') return false;
                if (s3[a][b - 1].charAt(c) <= s3[a][b].charAt(d)) return true;
                if (s3[a][b - 1].charAt(3) == '3') return true;
                break;
            case 'w':
                if (s5[0][a - 1][b].charAt(3) == e) return true;
                if (s3[a - 1][b].charAt(3) == f) return true;
                if (s3[a - 1][b].charAt(3) == '5') return true;
                if (s3[a - 1][b].charAt(3) == '3') return true;
                if (s3[a][b].charAt(d) == '1' && s3[a - 1][b].charAt(c) == '8') return true;
                if (s3[a][b].charAt(d) == '8' && s3[a - 1][b].charAt(c) == '1') return false;
                if (s3[a - 1][b].charAt(c) <= s3[a][b].charAt(d)) return true;
                if (s3[a - 1][b].charAt(3) == '3') return true;
                break;
            case 's':
                if (s5[0][a + 1][b].charAt(3) == e) return true;
                if (s3[a + 1][b].charAt(3) == f) return true;
                if (s3[a + 1][b].charAt(3) == '5') return true;
                if (s3[a + 1][b].charAt(3) == '3') return true;
                if (s3[a][b].charAt(d) == '1' && s3[a + 1][b].charAt(c) == '8') return true;
                if (s3[a][b].charAt(d) == '8' && s3[a + 1][b].charAt(c) == '1') return false;
                if (s3[a + 1][b].charAt(c) <= s3[a][b].charAt(d)) return true;
                if (s3[a + 1][b].charAt(3) == '3') return true;
                break;
            case 'd':
                if (s5[0][a][b + 1].charAt(3) == e) return true;
                if (s3[a][b + 1].charAt(3) == f) return true;
                if (s3[a][b + 1].charAt(3) == '5') return true;
                if (s3[a][b + 1].charAt(3) == '3') return true;
                if (s3[a][b].charAt(d) == '1' && s3[a][b + 1].charAt(c) == '8') return true;
                if (s3[a][b].charAt(d) == '8' && s3[a][b + 1].charAt(c) == '1') return false;
                if (s3[a][b + 1].charAt(c) <= s3[a][b].charAt(d)) return true;
                if (s3[a][b + 1].charAt(3) == '3') return true;
                break;
        }
        return false;
    }

    public static Boolean crossing(String[][] s3, String[][][] s5, String s4, int a, int b, int c) {
        switch (s4.charAt(1)) {
            case 'a':
                if ((s3[a][b].charAt(c) != '1') && (s5[0][a][b - 1].charAt(3) == '5')) return false;
                break;
            case 'w':
                if ((s3[a][b].charAt(c) != '1') && (s5[0][a - 1][b].charAt(3) == '5')) return false;
                break;
            case 's':
                if ((s3[a][b].charAt(c) != '1') && (s5[0][a + 1][b].charAt(3) == '5')) return false;
                break;
            case 'd':
                if ((s3[a][b].charAt(c) != '1') && (s5[0][a][b + 1].charAt(3) == '5')) return false;
                break;

        }
        return true;
    }

    public static Boolean den(String[][] s3, String s4, int a, int b) {
        switch (s4.charAt(1)) {
            case 'a':
                if (s3[a][b - 1].charAt(3) == '左') return false;
                break;
            case 'w':
                if (s3[a - 1][b].charAt(3) == '左') return false;
                break;
            case 's':
                if (s3[a + 1][b].charAt(3) == '左') return false;
                break;
            case 'd':
                if (s3[a][b + 1].charAt(3) == '左') return false;
                break;

        }
        return true;
    }

    public static Boolean den1(String[][] s3, String s4, int a, int b) {
        switch (s4.charAt(1)) {
            case 'a':
                if (s3[a][b - 1].charAt(3) == '右') return false;
                break;
            case 'w':
                if (s3[a - 1][b].charAt(3) == '右') return false;
                break;
            case 's':
                if (s3[a + 1][b].charAt(3) == '右') return false;
                break;
            case 'd':
                if (s3[a][b + 1].charAt(3) == '右') return false;
                break;

        }
        return true;
    }

    public static void trap(String[][] s3) {
        if (s3[2][0].charAt(3) == '3') s3[2][0] = " 阱 7";
        if (s3[4][0].charAt(3) == '3') s3[4][0] = " 阱 7";
        if (s3[3][1].charAt(3) == '3') s3[3][1] = " 阱 7";
        if (s3[2][8].charAt(3) == '3') s3[2][8] = " 阱 8";
        if (s3[3][7].charAt(3) == '3') s3[3][7] = " 阱 8";
        if (s3[4][8].charAt(3) == '3') s3[4][8] = " 阱 8";
    }

    public static Boolean victory(String[][] s1) {
        if (s1[3][0].charAt(3) == '2') return true;
        if (s1[3][8].charAt(3) == '1') return true;
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 9; j++) {
                if (s1[i][j].charAt(3) == '1') sum1++;
                if (s1[i][j].charAt(3) == '2') sum2++;
            }
        if (sum1 == 0 || sum2 == 0) return true;
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        printfirst();
        Scanner scanner = new Scanner(new File("tile.txt"));
        Scanner scanner1 = new Scanner(new File("animal.txt"));
        String[] s1 = new String[7];
        String[] s2 = new String[7];
        for (int i = 0; i < 7; i++) {
            s1[i] = scanner.nextLine();
            s2[i] = scanner1.nextLine();
        }
        char[][] shuzi = new char[10][10];//地形地图
        char[][] animal = new char[10][10];//动物地图
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 9; j++)
                shuzi[i][j] = s1[i].charAt(j);
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 9; j++)
                animal[i][j] = s2[i].charAt(j);
        String[][] board = new String[10][10];//存储当前棋盘状态
        String[][][] historyboard = new String[1000][10][10];//存储悔棋过程的棋盘状态
        String[][][] historyboard1 = new String[1000][10][10];//存储取消悔棋的棋盘状态
        combine(animal, shuzi, board);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(board[i][j].substring(0, 3));
            System.out.println();
        }
        historyboard[0] = copyArray(board);
        historyboard1[0] = copyArray(board);//初始化
        int sum = 0, laststep = 0, currentstep = 0, alive = 0;
        Boolean stop = false;
        Boolean printboard = true;
        Boolean undo = false, redo = false;
        Boolean bug = false; //为了sum  减一
        String string;
        Scanner input = new Scanner(System.in);
        do {
            print(sum);
            string = input.nextLine();
            System.out.println();
            if (string.equals("restart")) {
                board = copyArray(historyboard[0]);
                laststep = 0;
                currentstep = 0;
                sum = 1;
                stop = true;
            }
            if (string.equals("redo")) {
                if (laststep >= currentstep) {
                    System.out.println("已经回到最后的记录,不能再取消悔棋了!请重新输入：");
                    printboard = false;
                    sum--;
                    stop = true;
                } else {
                    currentstep--;
                    laststep++;
                    board = copyArray(historyboard1[currentstep]);
                    historyboard[currentstep] = copyArray(historyboard[laststep]);
                    stop = true;
                    redo = true;
                }
            }
            if (string.equals("undo")) {
                if (laststep < 1) {
                    System.out.println("已经退回到开局,不能再悔棋了!请重新输入：");
                    sum--;
                    printboard = false;
                    stop = true;
                } else {
                    laststep--;
                    currentstep++;
                    board = copyArray(historyboard[laststep]);
                    historyboard1[currentstep] = copyArray(historyboard[laststep]);
                    stop = true;
                    undo = true;
                }
            }
            if (string.equals("exit")) {
                System.out.println("结束游戏");
                System.exit(0);
            }
            if (string.equals("help")) {
                printfirst();
                printboard = false;
                sum--;
                stop = true;
            }

            if (!stop) {
                if (string.length() == 2 && string.charAt(0) >= '0' && string.charAt(0) <= '8' && (string.charAt(1) == 'a' || string.charAt(1) == 'w' || string.charAt(1) == 's' || string.charAt(1) == 'd'))
                    bug = false;
                else {
                    System.out.println("不能识别指令" + "'" + string + "'" + "请重新输入：");
                    printboard = false;
                    bug = true;
                    stop = true;
                }
                if ( bug ) sum--;
            }
            if (!stop) {
                if (sum % 2 == 0) {
                    for (int i = 0; i < 7; i++)
                        for (int j = 0; j < 9; j++)
                            if (board[i][j].charAt(0) == string.charAt(0)) alive++;
                    if (alive == 0) {
                        System.out.println("棋子已死，请重新输入：");
                        sum--;
                        printboard = false;
                        stop = true;
                    }
                } else {
                    for (int i = 0; i < 7; i++)
                        for (int j = 0; j < 9; j++)
                            if (board[i][j].charAt(2) == string.charAt(0)) alive++;
                    if (alive == 0) {
                        System.out.println("棋子已死，请重新输入：");
                        sum--;
                        stop = true;
                        printboard = false;
                    }
                }
            }
            alive = 0;
            if (!stop) {
                for (int i = 0; i < 7; i++)
                    for (int j = 0; j < 9; j++) {    //find the right one
                        if ((sum % 2 == 0) && (board[i][j].charAt(0) == string.charAt(0))) {//左方下棋
                            if (!stop && outside(string, i, j)) {
                                System.out.println("棋子出界，请重新输入： ");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!eat2(board, historyboard, string, i, j)) {
                                System.out.println("小河中的鼠也不能吃陆地上的象,请重新输入：");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!stop && !eat1(board, historyboard, string, i, j, 2, 0, '7', '8')) {
                                System.out.println("不能吃比自己大的棋子，请重新输入： ");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!stop && !den(board, string, i, j)) {
                                System.out.println("己方不能进己方的家，请重新输入：");
                                printboard = false;
                                sum--;
                                stop = true;
                            }
                            if (!stop) {//棋子跳河判断。
                                switch (string.charAt(1)) {
                                    case 'a':
                                        if (((string.charAt(0) == '6' && historyboard[0][i][j - 1].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i][j - 1].charAt(3) == '5')) && (board[i][j - 4].charAt(2) <= board[i][j].charAt(0) || board[i][j - 4].charAt(3) == '3')) {
                                            if (!board[i][j - 1].substring(1, 4).equals("鼠12") && !board[i][j - 2].substring(1, 4).equals("鼠12") && !board[i][j - 3].substring(1, 4).equals("鼠12")) {
                                                board[i][j - 4] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);//存储每步棋的棋盘状态
                                                stop = true;
                                                if (undo) {//如果上一步用过悔棋，这一步currentstep的数值应该和laststep相同
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                printboard = false;
                                                sum--;
                                                stop = true;
                                            }
                                        }
                                        break;
                                    case 'w':
                                        if (((string.charAt(0) == '6' && historyboard[0][i - 1][j].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i - 1][j].charAt(3) == '5')) && (board[i - 3][j].charAt(2) <= board[i][j].charAt(0) || board[i - 3][j].charAt(3) == '3')) {
                                            if (!board[i - 1][j].substring(1, 4).equals("鼠12") && !board[i - 2][j].substring(1, 4).equals("鼠12")) {
                                                board[i - 3][j] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);
                                                stop = true;
                                                if (undo) {
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                printboard = false;
                                                sum--;
                                                stop = true;

                                            }
                                        }
                                        break;
                                    case 's':
                                        if (((string.charAt(0) == '6' && historyboard[0][i + 1][j].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i + 1][j].charAt(3) == '5')) && (board[i + 3][j].charAt(2) <= board[i][j].charAt(0) || board[i + 3][j].charAt(3) == '3')) {
                                            if (!board[i + 1][j].substring(1, 4).equals("鼠12") && !board[i + 2][j].substring(1, 4).equals("鼠12")) {
                                                board[i + 3][j] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);
                                                stop = true;
                                                if (undo) {
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                printboard = false;
                                                sum--;
                                                stop = true;
                                            }
                                        }
                                        break;
                                    case 'd':
                                        if (((string.charAt(0) == '6' && historyboard[0][i][j + 1].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i][j + 1].charAt(3) == '5')) && (board[i][j + 4].charAt(2) <= board[i][j].charAt(0) || board[i - 3][j].charAt(3) == '3')) {
                                            if (!board[i][j + 1].substring(1, 4).equals("鼠12") && !board[i][j + 2].substring(1, 4).equals("鼠12") && !board[i][j + 3].substring(1, 4).equals("鼠12")) {
                                                board[i][j + 4] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);
                                                stop = true;
                                                if (undo) {
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                printboard = false;
                                                sum--;
                                                stop = true;
                                            }
                                        }
                                        break;


                                }
                            }
                            if (!stop && !crossing(board, historyboard, string, i, j, 0)) {
                                System.out.println("该棋子不能过河，请重新输入：");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!stop && !eat(board, string, i, j, '1')) {
                                System.out.println("不能吃自己的棋子,请重新输入：");
                                printboard = false;
                                stop = true;
                                sum--;
                            }

                            if (!stop) {
                                switch (string.charAt(1)) {//正常的移动
                                    case 'a':
                                        board[i][j - 1] = board[i][j];
                                        board[i][j] = " 　 3";//多一个空格，避免数组出界。
                                        break;
                                    case 'w':
                                        board[i - 1][j] = board[i][j];
                                        board[i][j] = " 　 3";
                                        break;
                                    case 's':
                                        board[i + 1][j] = board[i][j];
                                        board[i][j] = " 　 3";
                                        break;
                                    case 'd':
                                        board[i][j + 1] = board[i][j];
                                        board[i][j] = " 　 3";
                                        break;
                                }
                                laststep++;
                                currentstep++;
                                historyboard[laststep] = copyArray(board);
                                historyboard1[currentstep] = copyArray(board);
                                stop = true;
                                if (undo) {
                                    currentstep = laststep;
                                    undo = false;
                                }
                            }
                        }
                        if ((sum % 2 == 1) && (board[i][j].charAt(2) == string.charAt(0))) {
                            if (!stop && outside(string, i, j)) {
                                System.out.println("棋子出界，请重新输入： ");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!eat2(board, historyboard, string, i, j)) {
                                System.out.println("小河中的鼠也不能吃陆地上的象,请重新输入：");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!stop && !eat1(board, historyboard, string, i, j, 0, 2, '8', '7')) {
                                System.out.println("不能吃比自己大的棋子，请重新输入： ");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!stop && !den1(board, string, i, j)) {
                                System.out.println("己方不能进己方的家，请重新输入：");
                                printboard = false;
                                sum--;
                                stop = true;
                            }
                            if (!stop) {
                                switch (string.charAt(1)) {
                                    case 'a':
                                        if (((string.charAt(0) == '6' && historyboard[0][i][j - 1].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i][j - 1].charAt(3) == '5')) && (board[i][j - 4].charAt(0) <= board[i][j].charAt(2) || board[i][j - 4].charAt(3) == '3')) {
                                            if (!board[i][j - 1].substring(0, 2).equals("1鼠") && !board[i][j - 2].substring(0, 2).equals("1鼠") && !board[i][j - 3].substring(0, 2).equals("1鼠")) {
                                                board[i][j - 4] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);
                                                stop = true;
                                                if (undo) {
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                printboard = false;
                                                sum--;
                                                stop = true;
                                            }
                                        }
                                        break;
                                    case 'w':
                                        if (((string.charAt(0) == '6' && historyboard[0][i - 1][j].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i - 1][j].charAt(3) == '5')) && (board[i - 3][j].charAt(0) <= board[i][j].charAt(2) || board[i - 3][j].charAt(3) == '3')) {
                                            if (!board[i - 1][j].substring(0, 2).equals("1鼠") && !board[i - 2][j].substring(0, 2).equals("1鼠")) {
                                                board[i - 3][j] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);
                                                stop = true;
                                                if (undo) {
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                sum--;
                                                stop = true;
                                                printboard = false;
                                            }
                                        }
                                        break;
                                    case 's':
                                        if (((string.charAt(0) == '6' && historyboard[0][i + 1][j].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i + 1][j].charAt(3) == '5')) && (board[i + 3][j].charAt(0) <= board[i][j].charAt(2) || board[i + 3][j].charAt(3) == '3')) {
                                            if (!board[i + 1][j].substring(0, 2).equals("1鼠") && !board[i + 2][j].substring(0, 2).equals("1鼠")) {
                                                board[i + 3][j] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);
                                                stop = true;
                                                if (undo) {
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                sum--;
                                                stop = true;
                                                printboard = false;
                                            }
                                        }
                                        break;
                                    case 'd':
                                        if (((string.charAt(0) == '6' && historyboard[0][i][j + 1].charAt(3) == '5') || (string.charAt(0) == '7' && historyboard[0][i][j + 1].charAt(3) == '5')) && (board[i][j + 4].charAt(0) <= board[i][j].charAt(2) || board[i][j + 4].charAt(3) == '3')) {
                                            if (!board[i][j + 1].substring(0, 2).equals("1鼠") && !board[i][j + 2].substring(0, 2).equals("1鼠") && !board[i][j + 3].substring(0, 2).equals("1鼠")) {
                                                board[i][j + 4] = board[i][j];
                                                board[i][j] = " 　 3";
                                                laststep++;
                                                currentstep++;
                                                historyboard[laststep] = copyArray(board);
                                                historyboard1[currentstep] = copyArray(board);
                                                stop = true;
                                                if (undo) {
                                                    currentstep = laststep;
                                                    undo = false;
                                                }
                                            } else {
                                                System.out.println("水中有敌鼠，不能跳河，请重新输入：");
                                                sum--;
                                                stop = true;
                                                printboard = false;
                                            }
                                        }
                                        break;
                                }

                            }
                            if (!stop && !crossing(board, historyboard, string, i, j, 2)) {
                                System.out.println("该棋子不能过河，请重新输入：");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!stop && !eat(board, string, i, j, '2')) {
                                System.out.println("不能吃自己的棋子,请重新输入：");
                                printboard = false;
                                stop = true;
                                sum--;
                            }
                            if (!stop) {
                                switch (string.charAt(1)) {
                                    case 'a':
                                        board[i][j - 1] = board[i][j];
                                        board[i][j] = " 　 3";
                                        break;
                                    case 'w':
                                        board[i - 1][j] = board[i][j];
                                        board[i][j] = " 　 3";
                                        break;
                                    case 's':
                                        board[i + 1][j] = board[i][j];
                                        board[i][j] = " 　 3";
                                        break;
                                    case 'd':
                                        board[i][j + 1] = board[i][j];
                                        board[i][j] = " 　 3";
                                        break;
                                }
                                stop = true;
                                laststep++;
                                currentstep++;
                                historyboard[laststep] = copyArray(board);
                                historyboard1[currentstep] = copyArray(board);
                                if (undo) {
                                    currentstep = laststep;
                                    undo = false;
                                }
                            }
                        }
                    }
            }
            if (!undo && !redo) {
                for (int i = 1; i < 3; i++)
                    for (int j = 3; j < 6; j++)
                        if (board[i][j].charAt(3) == '3') {
                            board[i][j] = " 水 5";
                        }
                for (int i = 4; i < 6; i++)
                    for (int j = 3; j < 6; j++)
                        if (board[i][j].charAt(3) == '3') {
                            board[i][j] = " 水 5";
                        }
                trap(board);
                historyboard[laststep] = copyArray(board);
                historyboard1[currentstep] = copyArray(board);
            }
            if (printboard) {
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 9; j++)
                        System.out.print(board[i][j].substring(0, 3));
                    System.out.println();
                }
            }
            sum++;
            stop = false;
            printboard = true;
            redo = false;
            bug = false;
        } while (!victory(board));
        if (sum % 2 == 1) System.out.println("游戏结束，左方获胜");
        if (sum % 2 == 0) System.out.println("游戏结束，右方获胜");
    }

    private static String[][] copyArray(String[][] array) {
        String[][] newArray = new String[10][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }
}

