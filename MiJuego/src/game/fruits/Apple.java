package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Apple extends Fruit implements EntityC {

    public Apple(double x, double y, int value, Textures textures){
        super(x, y, value, textures);
        this.fruit = textures.apple[0];
    }

}
