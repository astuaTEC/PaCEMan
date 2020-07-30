package game.graphics;

import game.classes.EntityA;
import game.classes.EntityB;
import game.classes.EntityC;
import game.classes.WallEntity;

/**
 * Physics class
 * controls the game graphics collisions
 * @author Saymon Astúa, Oscar Araya
 */
public class Physics {

    // Collision of EntityA with EntityB
    public static boolean Collision(EntityA entityA, EntityB entityB){
        if(entityA.getBounds().intersects(entityB.getBounds())){
            return true;
        }
        return false;
    }

    // Collision of EntityA with EntityC
    public static boolean Collision(EntityA entityA, EntityC entityC){
        if(entityA.getBounds().intersects(entityC.getBounds())){
            return true;
        }
        return false;
    }

    // Collision of EntityA with wallEntity
    public static boolean Collision(EntityA entityA, WallEntity wallEntity){
        if(entityA.getBounds().intersects(wallEntity.getBounds())){
            return true;
        }
        return false;
    }


}
