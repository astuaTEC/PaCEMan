package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Banana extends Fruit implements EntityC {

    public Banana(double x, double y, int value, Textures textures){
        super(x, y, value, textures);
        this.fruit = textures.banana[0];
    }

}
