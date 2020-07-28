package game.graphics;

import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.classes.WallEntity;
import game.elements.Wall;

import java.util.LinkedList;

public class Physics {

    public static boolean Collision(EntityA entityA, EntityB entityB){
        if(entityA.getBounds().intersects(entityB.getBounds())){
            return true;
        }
        return false;
    }

    public static boolean Collision(EntityA entityA, EntityC entityC){
        if(entityA.getBounds().intersects(entityC.getBounds())){
            return true;
        }
        return false;
    }

    public static boolean Collision(EntityA entityA, WallEntity wallEntity){
        if(entityA.getBounds().intersects(wallEntity.getBounds())){
            return true;
        }
        return false;
    }


}
