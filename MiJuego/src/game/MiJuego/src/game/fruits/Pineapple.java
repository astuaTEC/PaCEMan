package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Pineapple extends Fruit implements EntityC {

    public Pineapple(double x, double y, int value, Textures textures){
        super(x, y, value, textures);
        this.fruit = textures.pineapple[0];
    }

}
