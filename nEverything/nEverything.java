import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
class nEverything {
  public static final int EMPTY = -1;
  public static final int KING = 0;
  public static final int QUEEN = 1;
  public static final int ROOK = 2;
  public static final int KNIGHT = 3;
  public static final int BISHOP = 4;
  public static final int PAWN = 5;
  private static int n = 8;
  private static int[][] board;
  
  public nEverything(){}


  public void comboWorks(int newN, int numKings, int numQueens, int numRooks, int numKnights, int numBishops,
      int numPawns) throws IOException {
    n = newN;
    board = new int[n][n];
    initializeBoard(board);
    int[] pieceList = pieceList(numKings, numQueens, numRooks, numKnights, numBishops, numPawns);
    boolean b = recursion(pieceList, 0);
    if (b) {
      System.out.println("It works!");
      Visualizer v = new Visualizer(chessBoard(), n);
    }
    else
      System.out.println("No solution");
  }

  private static void initializeBoard(int[][] board) {
    for (int[] ints : board)
      Arrays.fill(ints, EMPTY);
  }

  private static int[] pieceList(int numKings, int numQueens, int numRooks, int numKnights, int numBishops,
      int numPawns) {
    int[] arr = new int[numKings + numQueens + numRooks + numKnights + numBishops + numPawns];
    int i = 0;
    for (int j = 0; j < numKings; j++) {
      arr[i] = KING;
      i++;
    }
    for (int j = 0; j < numQueens; j++) {
      arr[i] = QUEEN;
      i++;
    }
    for (int j = 0; j < numRooks; j++) {
      arr[i] = ROOK;
      i++;
    }
    for (int j = 0; j < numKnights; j++) {
      arr[i] = KNIGHT;
      i++;
    }
    for (int j = 0; j < numBishops; j++) {
      arr[i] = BISHOP;
      i++;
    }
    for (int j = 0; j < numPawns; j++) {
      arr[i] = PAWN;
      i++;
    }
    return arr;
  }

  private static boolean recursion(int[] pieceList, int idx) {
    int piece = pieceList[idx];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] != EMPTY)
          continue;
        board[i][j] = piece;
        if (isBoardValid()) {
          if (idx == pieceList.length - 1)
            return true;
          else if (recursion(pieceList, idx + 1))
            return true;
        }
        board[i][j] = EMPTY;
      }
    }
    return false;
  }

  private static boolean isBoardValid() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        int piece = board[i][j];
        if (!((piece == KING && kingCheck(i, j))
            || (piece == QUEEN && queenCheck(i, j))
            || (piece == ROOK && rookCheck(i, j))
            || (piece == KNIGHT && knightCheck(i, j))
            || (piece == BISHOP && bishopCheck(i, j))
            || (piece == PAWN && pawnCheck(i, j))
            || piece == EMPTY))
          return false;

      }
    }
    return true;
  }

  private static boolean notOccupied(int x, int y) {
    if (x < 0 || y < 0 || x > n - 1 || y > n - 1) {
      return true;
    }
    return board[x][y] == EMPTY;
  }

  private static boolean pawnCheck(int x, int y) {
    return notOccupied(x + 1, y + 1) && notOccupied(x + 1, y - 1) &&
        notOccupied(x - 1, y + 1) && notOccupied(x - 1, y - 1);
  }

  private static boolean rookCheck(int x, int y) {
    for (int i = 0; i < n; i++) {
      if ((i != y && !notOccupied(x, i)) || (i != x && !notOccupied(i, y))) {
        return false;
      }
    }
    return true;
  }

  private static boolean knightCheck(int x, int y) {
    return notOccupied(x + 2, y + 1)
        && notOccupied(x + 2, y - 1)
        && notOccupied(x - 2, y + 1)
        && notOccupied(x - 2, y - 1)
        && notOccupied(x + 1, y + 2)
        && notOccupied(x + 1, y - 2)
        && notOccupied(x - 1, y + 2)
        && notOccupied(x - 1, y - 2);
  }

  private static boolean bishopCheck(int x, int y) {
    for (int i = 1; i < n; i++) {
      if (!notOccupied(x - i, y - i)
          || !notOccupied(x - i, y + i)
          || !notOccupied(x + i, y - i)
          || !notOccupied(x + i, y + i))
        return false;
    }
    return true;
  }

  private static boolean queenCheck(int x, int y) {
    return bishopCheck(x, y) && rookCheck(x, y);
  }

  private static boolean kingCheck(int x, int y) {
    int[] delta = { -1, 0, 1 };
    for (int i : delta) {
      for (int j : delta) {
        if (!notOccupied(x + i, y + j) && (i != 0 || j != 0))
          return false;
      }
    }
    return true;
  }

  private static void printBoard() {
    char[] pieces = { '0', 'K', 'Q', 'R', 'N', 'B', 'P' };
    
    for (int[] arr : board) {
      for (int i : arr) {
        System.out.print(pieces[i + 1] + " ");
      }
      System.out.println();
    }
    
  }
  public static ArrayList<Piece> chessBoard(){
    ArrayList<Piece> a = new ArrayList<Piece>();
    char[] pieces = { '0', 'K', 'Q', 'R', 'N', 'B', 'P' };
    for(int i=0;i<n;i++){
      for(int j=0;j<n;j++){
        if(pieces[board[i][j]+1]!='0'){
          a.add(new Piece(i, j, true, pieces[board[i][j]+1]));
        }
      }
    }
    return a;
  }
}