public class Piece{
    int xp;
    int yp;
    boolean white;
    char name;
    public Piece(int xp, int yp, boolean white, char name){
        this.xp = xp;
        this.yp = yp;
        this.white =white;
        this.name=name;

    }
    public String toString(){
        return "Piece: "+name+" x: "+xp+" y: "+yp;
    }
    public String getName(){
        return name+"";
    }
}