package game.characters;

import game.Controller;
import game.Game;
import game.algorithm.Node;
import game.classes.WallEntity;
import game.graphics.Physics;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Shadow character class
 * @author Saymon Astúa, Oscar Araya
 */
public class Shadow extends Ghost implements EntityB {

    LinkedList<Point> points = new LinkedList<>();
    ArrayList<Node> closedList = new ArrayList<Node>();
    Point destiny;

    public Shadow(double x, double y, Textures textures, Controller controller){
        super(x, y, textures, controller);

        this.velX = 0.5;
        this.velY = 0.5;

        destiny = new Point(14, 23);

        upAnimation = new Animation(10, textures.shadow[6], textures.shadow[7]);

        rightAnimation = new Animation(10, textures.shadow[0], textures.shadow[1]);

        downAnimation = new Animation(10, textures.shadow[2], textures.shadow[3]);

        leftAnimation = new Animation(10, textures.shadow[4], textures.shadow[5]);

        //getRute();
    }

    /**
     * Update the graphic movements of Shadow
     */
    public void tick(){
        verifyRute();
        Point a = getPos();
        getNextPoint(a);

        move();

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
        points.clear();
        algorithm.run();
        closedList = algorithm.A.getClosedList();
        for(int i = 0; i < closedList.size(); i++){
            Node node = closedList.get(i);
            points.add(new Point(node.getCol(), node.getRow()));
        }

    }

    public void getNextPoint(Point a) {
        if (points.size() > 0) {
            Point b = points.get(0);
            if (a.x == b.x && a.y == b.y){
                points.removeFirst();
            }
            if (a.x != b.x) {
                if (a.x < b.x) {
                    indication = "R";
                } else {
                    indication = "L";
                }
            }
             if (a.x == b.x ) {
                if (a.y > b.y ) {
                    indication = "U";
                }  else if (a.y < b.y) {
                    indication = "D";
                }
            }
        }
    }

    public void verifyRute(){
        if(!destiny.equals(controller.pacManPos) && controller.pacManPos != null){
            destiny = controller.pacManPos;
            algorithm.setStart(new Node((int)(y/20), (int)(x/20)));
            algorithm.setEnd(new Node(destiny.y, destiny.x));
            getRute();
        }

    }
    public void move(){
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
    }
}
