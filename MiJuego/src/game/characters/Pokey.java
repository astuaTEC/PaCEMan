package game.characters;

import game.Game;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

public class Pokey extends Ghost implements EntityB {

    public Pokey(double x, double y, Textures textures){
        super(x, y, textures);

        upAnimation = new Animation(10, textures.pokey[6], textures.pokey[7]);

        rightAnimation = new Animation(10, textures.pokey[0], textures.pokey[1]);

        downAnimation = new Animation(10, textures.pokey[2], textures.pokey[3]);

        leftAnimation = new Animation(10, textures.pokey[4], textures.pokey[5]);
    }

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
