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
        super(14, 13, textures, controller);

        this.velX = 1;
        this.velY = 1;

        destiny = new Point(14, 23);

        upAnimation = new Animation(10, textures.shadow[6], textures.shadow[7]);

        rightAnimation = new Animation(10, textures.shadow[0], textures.shadow[1]);

        downAnimation = new Animation(10, textures.shadow[2], textures.shadow[3]);

        leftAnimation = new Animation(10, textures.shadow[4], textures.shadow[5]);

    }

    /**
     * Update the graphic movements of Shadow
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
        // verify collisions
        wallCollision();

        verifyFhashing();
    }

    /**
     * Gets the path of pathfinding A * in the form of Points
     */
    public void getRute(){
        points.clear();
        closedList.clear();
        algorithm.run();
        closedList = algorithm.A.getClosedList();
        for(int i = 0; i < closedList.size(); i++){
            Node node = closedList.get(i);
            points.add(new Point(node.getCol(), node.getRow()));
        }

    }

    /**
     * Algorithm to follow a specified route using points
     * @param a the current Point
     */
    public void getNextPoint(Point a) {
        if (points.size() > 0) {
            Point b = points.get(0);
            if (a.x == b.x && a.y == b.y){
                points.removeFirst();
            }
            else if (a.y == b.y && specificPoints.contains(a)) {
                if (a.x < b.x ) {
                    indication = "R";
                } else {
                    indication = "L";
                }
            }
            else if (a.x == b.x && specificPoints.contains(a)) {
                if (a.y > b.y ) {
                    indication = "U";
                }  else {
                    indication = "D";
                }
            }
        }
    }

    /**
     * verify that the route to be calculated meets certain requirements
     */
    public void verifyRute(){
        if(!destiny.equals(controller.pacManPos) && controller.pacManPos != null && !isFlash){
            Point start = getPos();
            destiny = controller.pacManPos;
            if(!start.equals(destiny) && verifyAstar(start, destiny) && specificPoints.contains(start)) {
                algorithm.setStart(new Node(start.y, start.x));
                algorithm.setEnd(new Node(destiny.y, destiny.x));
                getRute();
            }
        }
    }

    /**
     * Update Ghost graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        //closedList = algorithm.A.getClosedList();
        // Draw full path
        /*for(Node n : closedList) {
            int i = n.getRow();
            int j = n.getCol();
            g.setColor(Color.RED);
            g.fillRect(20 * j, 20 * i, 20, 20);
        }*/
        if(!isFlash) {
            if (up)
                upAnimation.drawAnimation(g, x, y, 0);
            if (down)
                downAnimation.drawAnimation(g, x, y, 0);
            if (left)
                leftAnimation.drawAnimation(g, x, y, 0);
            if (right)
                rightAnimation.drawAnimation(g, x, y, 0);
        }
        else{
            flashAnimation.drawAnimation(g, x, y, 0);
        }
    }

    /**
     * Verify that the position between the pacman and the ghost
     * is within the established limits
     * @param a current position
     * @param b PacMan position
     * @return true if compliant, false otherwise
     */
    public boolean verifyAstar(Point a, Point b){

        if (Math.abs(a.x - b.x) > 2 || Math.abs(a.y - b.y) > 2)
            return Math.abs(a.x - b.x) < 11 && Math.abs(a.y - b.y) < 14;
        else
            return false;

    }
}
