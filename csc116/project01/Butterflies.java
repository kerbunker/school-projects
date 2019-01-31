public class Butterflies {
    public static final int BUTTERFLY = 6;
    public static void main(String[] args) {
        line01();
        line02();
        line03();
        line04();
        flowersFirstLine();
        flowersSecondLine();
    }
    
    public static void line01() {
        for(int i = 0; i < BUTTERFLY; i++) {
            System.out.print(" _    _   ");
        }
        System.out.println(" ");
    }
    
    public static void line02() {
        for(int i = 0; i < BUTTERFLY; i++) {
            System.out.print("(.\\\\//.)  ");
        }
        System.out.println(" ");
    }
    public static void line03() {
        for(int i = 0; i < BUTTERFLY; i++) {
            System.out.print(" \\ () /   ");
        }
        System.out.println(" ");
    }
    public static void line04() {
        for(int i = 0; i < BUTTERFLY; i++) {
            System.out.print(" (_/\\_)   ");
        }
        System.out.println(" ");
    }
    
    public static void flowersFirstLine() {
        for(int i = 0; i < 17; i++) {
            System.out.print(" ");
        }
        System.out.println("_");
        for(int i = 0; i < 15; i++) {
            System.out.print(" ");
        }
        System.out.print("_(_)_");
        for(int i = 0; i < 26; i++) {
            System.out.print(" ");
        }
        System.out.println("wWWWw   _");
    }

    public static void flowersSecondLine() {
        for(int i = 0; i < 3; i++) {
            System.out.print(" ");
        }
        System.out.print("@@@@");
        for(int i = 0; i < 7; i++) {
            System.out.print(" ");
        }
        System.out.println("(_)@(_)   vVVVv     _     @@@@  (___) _(_)_");
        System.out.println("  @@()@@ wWWWw  (_)\\    (___)   _(_)_  @@()@@   Y  (_)@(_)");
        System.out.println("   @@@@  (___)     `|/    Y    (_)@(_)  @@@@   \\|/   (_)\\");
        System.out.println("    /      Y       \\|    \\|/    /(_)    \\|      |/      |");
        System.out.println(" \\ |     \\ |/       | / \\ | /  \\|/       |/    \\|      \\|/");
        System.out.println("  \\|//   \\\\|//   \\\\\\|//\\\\\\|/// \\|///  \\\\\\|//  \\\\|//  \\\\\\|//");
        for(int i = 0; i < 61; i++) {
            System.out.print("^");
        }
        System.out.println(" ");
    }
}