package game.characters;

import game.Controller;
import game.Game;
import game.classes.WallEntity;
import game.graphics.Physics;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Pokey Character class
 * @author Saymon Astúa, Oscar Araya
 */
public class Pokey extends Ghost implements EntityB {

    ArrayList<String> movements = new ArrayList<>();

    public Pokey(double x, double y, Textures textures, Controller controller){
        super(x, y, textures, controller);

        upAnimation = new Animation(10, textures.pokey[6], textures.pokey[7]);

        rightAnimation = new Animation(10, textures.pokey[0], textures.pokey[1]);

        downAnimation = new Animation(10, textures.pokey[2], textures.pokey[3]);

        leftAnimation = new Animation(10, textures.pokey[4], textures.pokey[5]);

        movements.add("L"); movements.add("D"); movements.add("U"); movements.add("R");
    }

    /**
     * Update the graphic movements of Pokey
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
