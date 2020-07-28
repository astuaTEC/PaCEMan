package game;

import java.awt.*;

public class Menu {


    public Rectangle playButton = new Rectangle(Game.WIDTH/ 2 + 200, 150 ,100, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH/ 2 + 200, 250 ,100, 50);

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;


        //Font font = new Font("arial", Font.BOLD, 50);
        //g.setFont(font);
        g.setColor(Color.WHITE);
        //g.drawString("PAC MAN", Game.WIDTH/2 + 40, 100);*/

        Font font1 = new Font("arial", Font.BOLD, 30);
        g.setFont(font1);
        g.drawString("Play", playButton.x + 19, playButton.y + 30);
        g2d.draw(playButton);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 30);
        g2d.draw(quitButton);
    }
}
