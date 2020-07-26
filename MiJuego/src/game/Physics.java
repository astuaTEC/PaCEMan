package game;

import game.classes.EntityA;
import game.classes.EntityB;

import java.util.LinkedList;

public class Physics {

    public static boolean Collision(EntityA entityA, EntityB entityB){
        if(entityA.getBounds().intersects(entityB.getBounds())){
            return true;
        }
        return false;
    }

}
