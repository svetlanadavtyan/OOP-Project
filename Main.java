import core.SetConsole;
import ui.GameFrame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please input the number of players:");
            int numberOfPlayers = sc.nextInt();
            new GameFrame(numberOfPlayers);
        } else if(args[0].equals("-console")) {
            SetConsole setConsole = new SetConsole();
            setConsole.play();
        }
    }
}
