package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Cherry extends Fruit implements EntityC {

    public Cherry(double x, double y, int value, Textures textures){
        super(x, y, value, textures);
        this.fruit = textures.cherry[0];
    }

}
