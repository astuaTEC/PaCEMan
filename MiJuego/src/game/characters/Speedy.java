package game.characters;
import game.Controller;
import game.Game;
import game.classes.WallEntity;
import game.graphics.Physics;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

import java.util.LinkedList;

/**
 * Speedy character class
 * @author Saymon Astúa, Oscar Araya
 */

public class Speedy extends Ghost implements EntityB {

    public Speedy(double x, double y, Textures textures, Controller controller){
        super(x, y, textures, controller);

        upAnimation = new Animation(10, textures.speedy[6], textures.speedy[7]);

        rightAnimation = new Animation(10, textures.speedy[0], textures.speedy[1]);

        downAnimation = new Animation(10, textures.speedy[2], textures.speedy[3]);

        leftAnimation = new Animation(10, textures.speedy[4], textures.speedy[5]);
    }

    /**
     * Update the graphic movements of Speedy
     */
    public void tick(){

        switch (indication) {
            case "R":
                up = false;
                right = true;
                down = false;
                left = false;
                x += velX;
                break;
            case "L":
                up = false;
                right = false;
                down = false;
                left = true;
                x -= velX;
                break;
            case "D":
                up = false;
                right = false;
                down = true;
                left = false;
                y += velY;
                break;
            case "U":
                up = true;
                right = false;
                down = false;
                left = false;
                y -= velY;
                break;
        }

        //x += velX;
        // y += velY;

        if(x <= 20) {
            //right warp
            if (y >= 280 && y < 300)
                x = 530;
            else
                x = 20;
        }
        if (x >= 550) {
            //left warp
            if(y >= 280 && y < 300) {
                x = 20;
            }
            else
                x = 550;
        }

        // Collisions with walls
        LinkedList<WallEntity> we = controller.getWallEntity();
        for(int i = 0; i < we.size(); i++){
            WallEntity tempEnt = we.get(i);
            if(Physics.Collision(this, tempEnt) && !tempEnt.isGhosLicense()){
                double tempX = tempEnt.getX();
                double tempY = tempEnt.getY();
                if (tempX > x) {
                    x = tempX - 20;
                    changeDirection();
                }
                if (tempX < x) {
                    x = tempX + 20;
                    changeDirection();
                }
                if (tempY > y) {
                    y = tempY - 20;
                    changeDirection();
                }
                if (tempY < y) {
                    y = tempY + 20;
                    changeDirection();
                }
            }
        }
        if(!isFlash) {
            if (up)
                upAnimation.runAnimation();
            if (down)
                downAnimation.runAnimation();
            if (left)
                leftAnimation.runAnimation();
            if (right)
                rightAnimation.runAnimation();
        }
        else{
            flashAnimation.runAnimation();
        }
    }
}
