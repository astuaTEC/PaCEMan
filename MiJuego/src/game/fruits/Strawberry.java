package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Strawberry extends Fruit implements EntityC {

    public Strawberry(double x, double y, int value, Textures textures){
        super(x, y, value, textures);
        this.fruit = textures.strawberry[0];
    }

}
