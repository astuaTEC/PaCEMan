package game.characters;

import game.Controller;
import game.algorithm.Algorithm;
import game.algorithm.FindPath;
import game.algorithm.Node;
import game.graphics.Textures;
import game.libs.Animation;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Ghost character class
 * @author Saymon Astúa, Oscar Araya
 */
public class Ghost {

    // Attributes
    double x, y;
    double velX = 0;
    double velY = 0;

    Random r = new Random();

    Textures textures;
    Controller controller;
    String indication = null;

    ArrayList<String> movements = new ArrayList<>();
    public LinkedList<Point> specificPoints;
    public ArrayList<Node> closedList = new ArrayList<Node>();
    public LinkedList<Point> points = new LinkedList<>();

    public Point destiny;

    public Algorithm algorithm = new Algorithm();

    boolean up, down, right, left, isFlash;

    // Animations
    Animation upAnimation, downAnimation, leftAnimation, rightAnimation, flashAnimation;

    public Ghost(double x, double y, Textures textures, Controller controller){
        this.x = x;
        this.y = y;
        this.textures = textures;
        this.controller = controller;
        this.specificPoints = controller.getSpecificPoints();

        up = false;
        right = false;
        down = true;
        left = false;
        isFlash = false;

        indication = "R";

        destiny = new Point(14, 23);

        velX = 1;
        velY = 1;

        flashAnimation = new Animation(7, textures.flashGhost[0], textures.flashGhost[1]);

        movements.add("R"); movements.add("D"); movements.add("U"); movements.add("L");
    }

    /**
     * Update Ghost graphics
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){

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

    public Point getPos(){
        Point point = new Point();
        point.setLocation((int)((x)/20), (int)((y)/20));
        return point;

    }

    public void changeDirection(){
        int i = r.nextInt(3 + 1);
        indication = movements.get(i);
    }

    /**
     * Graphic limits of ghost
     * @return Ractangle: means the limit of Ghost character
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 20, 20);
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

    public void verifyRute(){
        if(!destiny.equals(controller.pacManPos) && controller.pacManPos != null){
            Point start = getPos();
            destiny = controller.pacManPos;
            if(!start.equals(destiny) && verifyAstar(start, destiny) && specificPoints.contains(start)) {
                algorithm.setStart(new Node(start.y, start.x));
                algorithm.setEnd(new Node(destiny.y, destiny.x));
                getRute();
            }
        }
    }

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

    public boolean verifyAstar(Point a, Point b){
        if (Math.abs(a.x - b.x) > 2 || Math.abs(a.y - b.y) > 2)
            return Math.abs(a.x - b.x) < 8 && Math.abs(a.y - b.y) < 8;
        else
            return false;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isFlash() {
        return isFlash;
    }

    public void setFlash(boolean flash) {
        isFlash = flash;
    }
}
