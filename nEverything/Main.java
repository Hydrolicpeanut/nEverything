import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        nEverything e = new nEverything();
        int n = 0;
        int king = 0;
        int queen = 0;
        int rook = 0;
        int knights = 0;
        int bishop = 0;
        int pawn = 0;
        Scanner input=new Scanner(System.in);
        System.out.println("What are the dimesions of the board you want to test?(Enter a number greater than 0)");
        n = input.nextInt();
        System.out.println("How may Kings do you want on the board?");
        king = input.nextInt();
        System.out.println("How may Queens do you want on the board?");
        queen = input.nextInt();
        System.out.println("How may Rooks do you want on the board?");
        rook = input.nextInt();
        System.out.println("How may Knights do you want on the board?");
        knights = input.nextInt();
        System.out.println("How may Bishop do you want on the board?");
        bishop = input.nextInt();
        System.out.println("How may Pawn do you want on the board?");
        pawn = input.nextInt();
        input.close();
        e.comboWorks(n,king,queen,rook,knights,bishop,pawn);
        
    }
}
