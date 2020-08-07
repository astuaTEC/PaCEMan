package game.characters;

import game.Controller;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;
import java.util.ArrayList;

/**
 * Pokey Character class
 * @author Saymon Astúa, Oscar Araya
 */
public class Pokey extends Ghost implements EntityB {

    ArrayList<String> movements = new ArrayList<>();

    public Pokey(double x, double y, Textures textures, Controller controller){
        super(13, 13, textures, controller);

        upAnimation = new Animation(10, textures.pokey[6], textures.pokey[7]);

        rightAnimation = new Animation(10, textures.pokey[0], textures.pokey[1]);

        downAnimation = new Animation(10, textures.pokey[2], textures.pokey[3]);

        leftAnimation = new Animation(10, textures.pokey[4], textures.pokey[5]);

        movements.add("L"); movements.add("D"); movements.add("U"); movements.add("R");
    }

    /**
     * Update the graphic movements of Pokey
     */
    public void tick() {

        move();

        if (x <= 20) {
            //right warp
            if (y >= 280 && y < 300) {
                x = 20;
                changeDirection();
            }

        }
        if (x >= 450) {
            if (y >= 280 && y < 300) {
                x = 440;
                changeDirection();
            }
        }

        // verify the collisions

        wallCollision();

        verifyFhashing();
    }

}
