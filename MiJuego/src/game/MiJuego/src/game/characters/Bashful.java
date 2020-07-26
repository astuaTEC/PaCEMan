package game.characters;

import game.Game;
import game.graphics.Textures;
import game.classes.EntityB;
import game.libs.Animation;

public class Bashful extends Ghost implements EntityB {

    public Bashful(double x, double y, Textures textures){
        super(x, y, textures);

        upAnimation = new Animation(10, textures.bashful[6], textures.bashful[7]);

        rightAnimation = new Animation(10, textures.bashful[0], textures.bashful[1]);

        downAnimation = new Animation(10, textures.bashful[2], textures.bashful[3]);

        leftAnimation = new Animation(10, textures.bashful[4], textures.bashful[5]);
    }

    public void tick(){
        y += speed;
        if(y > (Game.HEIGHT * Game.SCALE)){
            speed = r.nextInt(3) + 1;
            y = -10;
            x = r.nextInt(640);
        }
        if(up)
            upAnimation.runAnimation();
        if(down)
            downAnimation.runAnimation();
        if(left)
            leftAnimation.runAnimation();
        if(right)
            rightAnimation.runAnimation();
    }
}
