package game.characters;
import game.Controller;
import game.Game;
import game.algorithm.Algorithm;
import game.algorithm.Node;
import game.classes.WallEntity;
import game.graphics.Physics;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

import java.awt.*;
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

        // Collisions with walls
        LinkedList<WallEntity> we = controller.getWallEntity();
        for(int i = 0; i < we.size(); i++){
            WallEntity tempEnt = we.get(i);
            if(Physics.Collision(this, tempEnt) && !tempEnt.isGhosLicense()){
                double tempX = tempEnt.getX();
                double tempY = tempEnt.getY();
                if (tempX > x) {
                    x = tempX - 20;
                }
                if (tempX < x) {
                    x = tempX + 20;
                }
                if (tempY > y) {
                    y = tempY - 20;
                }
                if (tempY < y) {
                    y = tempY + 20;
                }
                changeDirection();
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

    public void verifyRute(){
        if(!destiny.equals(controller.pacManPos) && controller.pacManPos != null){
            Point start = getPos();
            /*Point parcialDestiny = controller.pacManPos;
            destiny = selectDestiny(parcialDestiny);*/
            destiny = controller.pacManPos;
            if(!start.equals(destiny) && verifyAstar(start, destiny) && specificPoints.contains(start))  {
                algorithm.setStart(new Node(start.y, start.x));
                algorithm.setEnd(new Node(destiny.y, destiny.x));
                getRute();
            }
        }
    }

    public Point selectDestiny(Point a){
        Point b = new Point();
        if(controller.right){
            if(a.x + 1 < 28)
                b.x = a.x + 1;
            else
                b.x = a.x;
            b.y = a.y;
        }
        else if(controller.left){
            if(a.x - 1 > 1)
                b.x = a.x - 1;
            else
                b.x = a.x;
            b.y = a.y;
        }
        else if(controller.down){
            if(a.y + 1 < 30)
                b.y = a.y + 1;
            else
                b.y = a.y;
            b.x = a.x;
        }
        else if(controller.up){
            if(a.y - 1 > 0)
                b.y = a.y - 1;
            else
                b.y = a.y;
            b.x = a.x;
        }
        if(Algorithm.map[b.y][b.x] != 0) {
            return b;
        }
        else
            return a;
    }
}
