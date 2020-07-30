package game.characters;

import game.Game;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

/**
 * Shadow character class
 * @author Saymon Astúa, Oscar Araya
 */
public class Shadow extends Ghost implements EntityB {

    public Shadow(double x, double y, Textures textures){
        super(x, y, textures);

        upAnimation = new Animation(10, textures.shadow[6], textures.shadow[7]);

        rightAnimation = new Animation(10, textures.shadow[0], textures.shadow[1]);

        downAnimation = new Animation(10, textures.shadow[2], textures.shadow[3]);

        leftAnimation = new Animation(10, textures.shadow[4], textures.shadow[5]);
    }

    /**
     * Update the graphic movements of Shadow
     */
    public void tick(){
        y += speed;
        if(y > (Game.HEIGHT * Game.SCALE)){
            speed = r.nextInt(3) + 1;
            y = -10;
            x = r.nextInt(640);
        }
        if(!isFlash) {
            if (up)
                upAnimation.runAnimation();
            if (down)
                downAnimation.runAnimation();
            if (left)
                leftAnimation.runAnimation();
            if (right)
                rightAnimation.runAnimation();
        }
        else{
            flashAnimation.runAnimation();
        }
    }
}
