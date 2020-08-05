package game.characters;
import game.Controller;
import game.algorithm.Node;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

import java.awt.*;

/**
 * Speedy character class
 * @author Saymon Astúa, Oscar Araya
 */

public class Speedy extends Ghost implements EntityB {

    public Speedy(double x, double y, Textures textures, Controller controller){
        super(11, 13, textures, controller);

        upAnimation = new Animation(10, textures.speedy[6], textures.speedy[7]);

        rightAnimation = new Animation(10, textures.speedy[0], textures.speedy[1]);

        downAnimation = new Animation(10, textures.speedy[2], textures.speedy[3]);

        leftAnimation = new Animation(10, textures.speedy[4], textures.speedy[5]);
    }

    /**
     * Update the graphic movements of Speedy
     */
    public void tick(){
        verifyRute();
        Point a = getPos();

        getNextPoint(a);
        move();

        if(x <= 20) {
            //right warp
            if (y >= 280 && y < 300) {
                x = 20;
                changeDirection();
            }

        }
        if (x >= 450) {
            if(y >= 280 && y < 300) {
                x = 440;
                changeDirection();
            }
        }

        wallCollision();

        verifyFhashing();
    }

    public void verifyRute(){
        if(!destiny.equals(controller.pacManPos) && controller.pacManPos != null){
            Point start = getPos();
            destiny = controller.pacManPos;
            if(!start.equals(destiny) && verifyAstar(start, destiny) && specificPoints.contains(start))  {
                algorithm.setStart(new Node(start.y, start.x));
                algorithm.setEnd(new Node(destiny.y, destiny.x));
                getRute();
            }
        }
    }

}
