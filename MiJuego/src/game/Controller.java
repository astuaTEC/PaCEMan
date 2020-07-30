package game;

import game.characters.Bashful;
import game.characters.Pokey;
import game.characters.Shadow;
import game.characters.Speedy;
import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.classes.WallEntity;
import game.fruits.*;
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

    Random r = new Random();

    EntityA entA;
    EntityB entB;
    EntityC entC;
    WallEntity wallEntity;

    private Textures textures;

    public Controller(Textures textures){
        this.textures = textures;
        addEntity(new Shadow(100, 100, textures));
        addEntity(new Speedy(100, 100, textures));
        addEntity(new Bashful(100, 100, textures));
        addEntity(new Pokey(100, 100, textures));

        addEntity(new Banana(3, 2, 25, textures));
        addEntity(new Cherry(4, 2, 25, textures));
        addEntity(new Apple(5, 2, 25, textures));
        addEntity(new Orange(6, 2, 25, textures));
        addEntity(new Pineapple(7, 2, 25, textures));
        addEntity(new Strawberry(8, 2, 25, textures));

    }

    public void createEnemy(int enemy_count){
        for(int i = 0; i < enemy_count; i++){
            //addEntity(new Pokey(r.nextInt(640), -10, textures));
        }
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

    public void addEntity(EntityA block){
        ea.add(block);
    }
    public void removeEntity(EntityA block){
        ea.remove(block);
    }
    public void addEntity(EntityB block){
        eb.add(block);
    }
    public void removeEntity(EntityB block){
        eb.remove(block);
    }
    public void addEntity(EntityC block){
        ec.add(block);
    }
    public void removeEntity(EntityC block){
        ec.remove(block);
    }
    public void addEntity(WallEntity block){
        we.add(block);
    }
    public void removeEntity(WallEntity block){
        we.remove(block);
    }

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
}
