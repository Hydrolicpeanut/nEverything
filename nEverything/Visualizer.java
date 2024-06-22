
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Visualizer {
    public Visualizer(ArrayList<Piece> Level, int n) throws IOException{
        Image imgs[] = new Image[6];
        imgs[0] = ImageIO.read(new File("Images/king.png"));
        imgs[1] = ImageIO.read(new File("Images/queen.png"));
        imgs[2] = ImageIO.read(new File("Images/bishop.png"));
        imgs[3] = ImageIO.read(new File("Images/Knight.png"));
        imgs[4] = ImageIO.read(new File("Images/rook.png"));
        imgs[5] = ImageIO.read(new File("Images/pawn.png"));
        Color Light = new Color(240, 194, 128);
        Color Dark = new Color(109, 62, 23);
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 64*n, 64*n);
        frame.setUndecorated(true);
        JPanel pn = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean white =true;
                for(int y=0;y<n;y++){
                    for(int x=0;x<n;x++){
                        if(white==true){
                            g.setColor(Light);  
                        }else{
                            g.setColor(Dark);
                        }
                        
                        g.fillRect(x*64, y*64, 64, 64);
                        white=!white;
                    }
                    if(n%2==0){
                        white=!white; 
                    }
                    
                }
                for(Piece p:Level){
                    int ind=0;
                    if(p.getName().equalsIgnoreCase("k")){
                        ind=0;
                    }
                    if(p.getName().equalsIgnoreCase("q")){
                        ind=1;
                    }
                    if(p.getName().equalsIgnoreCase("b")){
                        ind=2;
                    }
                    if(p.getName().equalsIgnoreCase("n")){
                        ind=3;
                    }
                    if(p.getName().equalsIgnoreCase("r")){
                        ind=4;
                    }
                    if(p.getName().equalsIgnoreCase("p")){
                        ind=5;
                    }                                                                                           
                    g.drawImage(imgs[ind], p.xp*64+10, p.yp*64+10, this);
                    
                }
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

    }
    
    
}
