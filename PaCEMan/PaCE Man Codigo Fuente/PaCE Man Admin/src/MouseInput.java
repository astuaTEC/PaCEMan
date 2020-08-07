import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


/*
Clase que describe a los objetos de tipo MouseInput. Este objeto es el que hace posible el controlar el menu
del administrador por medio del mouse. Implementa la clase MouseListener, por lo que se deben sobreescribir
parte de sus metodos. En este caso, solo se utiliza el metodo mousePressed, para cuando el administrador selecciona
una opcion en pantalla.
*/
class MouseInput implements MouseListener {


    /*
    Metodo encargado de detectar el click en pantalla del administrador, y en funcion de su posicion en los
    ejes xy, se sabe que la opcion seleccionada es una u otra. Se recurre al calculo de pixeles para determinar la
    posicion en la matriz en la que el administrador ha dado click, por medio de dos ciclos for anidados uno dentro
    de otro. Una vez que se ha validado cual de las opciones ha seleccionado el administrador, se envia un mensaje
    al codigo en C para que interprete y valide la opcion seleccionada.
     */
    @Override
    public void mousePressed(MouseEvent click) {
        Integer mouseInX = click.getX();
        Integer mouseInY = click.getY();
        Integer messageID = 0;

        if(mouseInX >= 600 && mouseInX <= 875){
            if(mouseInY >= 205 && mouseInY <= 250){
                messageID = 2;
            }else if(mouseInY >= 260 && mouseInY <= 305){
                messageID = 3;
            }else if(mouseInY >= 315 && mouseInY <= 360){
                messageID = 4;
            }else if(mouseInY >= 370 && mouseInY <= 415){
                 messageID = 5;
            }else if(mouseInY >= 425 && mouseInY <= 470){
                 messageID = 6;
            }else if(mouseInY >= 480 && mouseInY <= 525){
                 messageID = 7;
            }else if(mouseInY >= 535 && mouseInY <= 580){
                 messageID = 8;
            }else if(mouseInY >= 590 && mouseInY <= 635){
                 messageID = 9;
            }
        }
        if(messageID!=0){
            try{
                AdminGUI.sendMessageToServerC(messageID);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }else{
            if (mouseInX >= 0 && mouseInX <= 600 && mouseInY >= 40 && mouseInY <= 660) {
                for (Integer x = 0; x <= 600; x += 20) {
                    for (Integer y = 0; y <= 660; y += 20) {
                        if (mouseInX >= x - 20 && mouseInX <= x && mouseInY >= y - 20 && mouseInY <= y) {
                            try {
                                AdminGUI.currentPosition = "(" + (y/20-3) + "," + (x/20-1) + ")";
                                AdminGUI.sendMessageToServerC(1);
                            }catch (IOException ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}