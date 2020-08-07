package game;

import game.characters.*;
import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.classes.WallEntity;
import game.graphics.Textures;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Controller class
 * controls graphic elements on the screen
 * @author Saymon Astúa, Oscar Araya
 */

public class Controller {

    // Attributes
    private LinkedList<EntityA> ea = new LinkedList<>();
    private LinkedList<EntityB> eb = new LinkedList<>();
    private LinkedList<EntityC> ec = new LinkedList<>();
    private LinkedList<WallEntity> we = new LinkedList<>();
    private LinkedList<Point> specificPoints = new LinkedList<>();

    public boolean up, down, right, left, isDeath;

    Random r = new Random();

    EntityA entA;
    EntityB entB;
    EntityC entC;
    WallEntity wallEntity;

    public Point pacManPos;

    private Textures textures;

    public Controller(Textures textures){
        this.textures = textures;

        addEntity(new Speedy(11, 13, textures, this));
        addEntity(new Bashful(12, 13, textures, this));
        addEntity(new Pokey(13, 13, textures, this));
        addEntity(new Shadow(14, 13, textures, this));

    }


    /**
     * Update the graphic movements of the different elements on the screen
     */
    public void tick(){
        // A class
        for(int i = 0; i < ea.size(); i++){
            entA = ea.get(i);
            entA.tick();
        }
        // B class
        for(int i = 0; i < eb.size(); i++){
            entB = eb.get(i);
            entB.tick();
        }
        // C class
        for(int i = 0; i < ec.size(); i++){
            entC = ec.get(i);
            entC.tick();
        }
        // Wall class
        /*for(int i = 0; i < we.size(); i++){
            wallEntity = we.get(i);
            wallEntity.tick();
        }*/
    }

    /**
     * Update graphics elements on the screen
     * @param g Graphics to draw on the screen
     */
    public void render(Graphics g){
        // A class
        for(int i = 0; i < ea.size(); i++){
            entA = ea.get(i);
            entA.render(g);
        }
        // B class
        for(int i = 0; i < eb.size(); i++){
            entB = eb.get(i);
            entB.render(g);
        }
        // C class
        for(int i = 0; i < ec.size(); i++){
            entC = ec.get(i);
            entC.render(g);
        }
        // Wall class
        /*for(int i = 0; i < we.size(); i++){
            wallEntity = we.get(i);
            wallEntity.render(g);
        }*/
    }

    /**
     * Updates the speed of ghosts
     * @param vel speed to put in the ghosts
     */
    public void setGhostVel(double vel){
        for (EntityB entityB : eb) {
            entB = entityB;
            entB.setVel(vel);
        }
    }

    /**
     * Add an EntityA to the game
     * @param block the EntityA
     */
    public void addEntity(EntityA block){
        ea.add(block);
    }
    /**
     * Add a Point to the game
     * @param block the Point
     */
    public void addPoint(Point block){
        specificPoints.add(block);
    }
    /**
     * Delete an EntityA to the game
     * @param block the EntityA
     */
    public void removeEntity(EntityA block){
        ea.remove(block);
    }
    /**
     * Add an EntityB to the game
     * @param block the EntityB
     */
    public void addEntity(EntityB block){
        eb.add(block);
    }
    /**
     * Delete an EntityB to the game
     * @param block the EntityB
     */
    public void removeEntity(EntityB block){
        eb.remove(block);
    }
    /**
     * Add an EntityA to the game
     * @param block the EntityC
     */
    public void addEntity(EntityC block){
        ec.add(block);
    }
    /**
     * Delete an EntityC to the game
     * @param block the EntityC
     */
    public void removeEntity(EntityC block){
        ec.remove(block);
    }
    /**
     * Add a WallEntity to the game
     * @param block the WallEntity
     */
    public void addEntity(WallEntity block) { we.add(block); }
    /**
     * Delete a WallEntity to the game
     * @param block the WallEntity
     */
    public void removeEntity(WallEntity block){
        we.remove(block);
    }

    /////////////////   GETTERS AND SETTERS ///////////////////////////

    public LinkedList<EntityA> getEntityA(){
        return ea;
    }
    public LinkedList<EntityB> getEntityB(){
        return eb;
    }
    public LinkedList<EntityC> getEntityC(){
        return ec;
    }
    public LinkedList<WallEntity> getWallEntity(){
        return we;
    }

    public LinkedList<Point> getSpecificPoints() { return specificPoints;}

    public void setPacManPos(Point pacManPos) { this.pacManPos = pacManPos;}

}
