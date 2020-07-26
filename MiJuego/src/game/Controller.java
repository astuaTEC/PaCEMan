package game;

import game.classes.EntityA;
import game.classes.EntityB;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private LinkedList<EntityA> ea = new LinkedList<>();
    private LinkedList<EntityB> eb = new LinkedList<>();

    Random r = new Random();

    EntityA entA;
    EntityB entB;

    private Textures textures;

    public Controller(Textures textures){
        this.textures = textures;
        addEntity(new Enemy(100, 100, textures));
    }

    public void createEnemy(int enemy_count){
        for(int i = 0; i < enemy_count; i++){
            //addEntity(new Enemy(r.nextInt(640), -10, textures));
        }
    }

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
    }

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

    public LinkedList<EntityA> getEntityA(){
        return ea;
    }
    public LinkedList<EntityB> getEntityB(){
        return eb;
    }
}
