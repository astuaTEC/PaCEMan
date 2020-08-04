package game.characters;

import game.Controller;
import game.algorithm.Node;
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

    }

    /**
     * Update the graphic movements of BashFul
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

    public void getRute(){
        points.clear();
        closedList.clear();
        algorithm.run();
        closedList = algorithm.A.getClosedList();
        for (Node node : closedList) {
            points.add(new Point(node.getCol(), node.getRow()));
        }
    }

}
