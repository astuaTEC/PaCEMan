package game.fruits;

import game.classes.EntityC;
import game.graphics.Textures;

public class Orange extends Fruit implements EntityC {

    public Orange(double x, double y, int value, Textures textures){
        super(x, y, value, textures);
        this.fruit = textures.orange[0];
    }

}
