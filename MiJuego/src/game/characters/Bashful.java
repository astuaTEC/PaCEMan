package game.characters;

import game.Controller;
import game.Game;
import game.classes.WallEntity;
import game.graphics.Physics;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;


import java.awt.*;
import java.util.LinkedList;

/**
 * Bashful character class
 * @author Saymon Astúa, Oscar Araya
 */
public class Bashful extends Ghost implements EntityB {

    LinkedList<Point> points = new LinkedList<>();

    public Bashful(double x, double y, Textures textures, Controller controller){
        super(x, y, textures, controller);

        upAnimation = new Animation(10, textures.bashful[6], textures.bashful[7]);

        rightAnimation = new Animation(10, textures.bashful[0], textures.bashful[1]);

        downAnimation = new Animation(10, textures.bashful[2], textures.bashful[3]);

        leftAnimation = new Animation(10, textures.bashful[4], textures.bashful[5]);

        for(int i = 2; i < 14; i++){
            points.add(new Point(i, 1));
        }
        points.add(new Point(13, 2));
        getRute();
    }

    /**
     * Update the graphic movements of BashFul
     */
    public void tick(){
        Point a = getPos();
        getNextPoint(a);

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
        if(y <= 0)
            y = 0;
        if(y >= 580)
            y = 580;

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

    public void getRute(){
        LinkedList<String> rute = new LinkedList<>();
        for(int i = 0; i + 1 < points.size(); i++){
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            if(p1.x < p2.x){
                rute.add("R");
            }
            else if(p1.x > p2.x){
                rute.add("L");
            }
            else if(p1.y > p2.y){
                rute.add("U");
            }
            else if(p1.y < p2.y){
                rute.add("D");
            }
        }
        System.out.print("Rute:");
        for(int j = 0; j < rute.size(); j++){
            System.out.print(" --> " + rute.get(j));
        }
    }

    public void getNextPoint(Point a) {
        if (points.size() > 0) {
            Point b = points.get(0);
            if (a.x != b.x || a.y != b.y) {
                if (a.x < b.x) {
                    indication = "R";
                } else if (a.x > b.x) {
                    indication = "L";
                } else if (a.y > b.y) {
                    indication = "U";
                } else if (a.y < b.y) {
                    indication = "D";
                }
            }
            else {
                points.removeFirst();
            }
        }
    }
}
