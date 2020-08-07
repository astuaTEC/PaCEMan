
import javax.swing.*;
import java.awt.*;
import java.io.*;

import com.google.gson.*;

/*
Clase que describe a los objetos de tipo AdminGUI. Estos objetos son la ventana en donde se muestran las opciones
de administrador dentro de PaCE Man.
*/
public class AdminGUI {


    // Constantes de tipo Image que contienen a la imagen de fondo y el icono de la ventana.
    private static final Image ADMIN_MENU_IMAGE = Toolkit.getDefaultToolkit().getImage
            ("GraphicResources/Background.png");
    private static final Image ADMIN_ICON_IMAGE = Toolkit.getDefaultToolkit().getImage
            ("GraphicResources/Icon.png");


    // Constantes de tipo Integer que contienen el ancho y el alto de la ventana.
    private static final Integer MENU_WIDTH = 900;
    private static final Integer MENU_HEIGHT = 660;


    // Constante de tipo JFrame que contiene la ventana en donde se mostraran los contenidos para el administrador.
    public static final JFrame MENU_FRAME = new JFrame();


    // Constante de tipo Integer que contiene el puerto de conexion mediante el cual se comunica al cliente.
    public static final Integer connectionPort = 5050;


    // Constante de tipo ConnectionToClient que contiene al objeto encargado de la comunicacion con el cliente.
    public static final ConnectionToClient connectionThread = new ConnectionToClient();;


    /*
    Atributos estaticos de tipo String que contienen la posicion, los puntos, la velocidad y las vidas actuales
    provenientes del codigo escrito en C, y que sirven para ser mostrados al administrador en pantalla.
    */
    public static String currentPosition = "(1,2)";
    public static String currentPoints = "0";
    public static String currentSpeed = "1";
    public static String currentLives = "3";


    /*
    Metodo encargado de crear la ventana para el administrador. Se define su tamano, su fondo, su icono y demas
    propiedades que son constantes para cada partida.
    */
    private static void createMenuWindow(){
        updateWindowInformation();
        MENU_FRAME.setSize(MENU_WIDTH, MENU_HEIGHT);
        MENU_FRAME.setResizable(false);
        MENU_FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MENU_FRAME.add(new CustomPaintComponent());
        MENU_FRAME.setIconImage(ADMIN_ICON_IMAGE);
        MENU_FRAME.setVisible(true);
    }


    /*
    Metodo encargado de crear la base de datos que es accedida por el codigo en C. Esta base de datos almacena
    los valores actuales del juego en DataBase.txt. Esta base de datos es consultada por el codigo C antes
    de generar los mensajes para el cliente, y es la forma en la que el servidor lleva un control sobre
    la partida en ejecucion. En este metodo se rellena la base de datos con los valores iniciales por defecto
    de cada partida, con los fantasmas activados, las vidas en tres y la velocidad en uno.
    */
    public static void createDataBase() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter dataBaseWriter = new PrintWriter("DataBase/DataBase.txt", "UTF-8");
        dataBaseWriter.println("1. isPinkyActive:");
        dataBaseWriter.println(1);
        dataBaseWriter.println("2. isInkyActive:");
        dataBaseWriter.println(1);
        dataBaseWriter.println("3. isBlinkyActive:");
        dataBaseWriter.println(1);
        dataBaseWriter.println("4. isClydeActive:");
        dataBaseWriter.println(1);
        dataBaseWriter.println("5. currentPoints:");
        dataBaseWriter.println(0);
        dataBaseWriter.println("6. currentLives:");
        dataBaseWriter.println(3);
        dataBaseWriter.println("7. currentLevel:");
        dataBaseWriter.println(1);
        dataBaseWriter.println("8. currentSpeed:");
        dataBaseWriter.println(1);
        dataBaseWriter.println("END");
        dataBaseWriter.close();
    }


    /*
    Metodo encargado de la comunicacion con el codigo en C. En funcion de un identificador correspondiente para cada
    mensaje, el codigo en C interpreta lo que se le ha enviado y genera una respuesta que se almacena en la variable
    serverFinalAnswer, que luego pasa a ser analizada.
     */
    public static void sendMessageToServerC(Integer messageID) throws IOException{
        Process executeServerOnC = new ProcessBuilder("PaCE Man Administrador C.exe",Integer.toString(messageID),currentPosition).start();
        InputStream serverAnswer = executeServerOnC.getInputStream();
        InputStreamReader serverAnswerReader = new InputStreamReader(serverAnswer);
        BufferedReader bufferToReadServerAnswer = new BufferedReader(serverAnswerReader);
        String serverFinalAnswer = bufferToReadServerAnswer.readLine();
        System.out.println(serverFinalAnswer);
        analyzeAnswerFromC(serverFinalAnswer);
    }


    /*
    Metodo encargado de analizar la respuesta que se recibe del codigo en C. Estas respuestas varian en funcion
    del identificador, y para cada identificador, que van del 0 al 8 y de la a a la j, se genera un mensaje
    distinto que es enviado al cliente. Este metodo es el que realmente permite la comunicacion entre el codigo
    de C y el cliente escrito en Java, al este ultimo actuar en consecuencia de lo que determine la parte de C.
    */
    public static void analyzeAnswerFromC(String answerFromC) throws IOException {
        if(answerFromC!=null){
            char answerID = answerFromC.charAt(0);
            int ID = 0;

            if(answerID=='0'){
                AdminGUI.currentPoints = answerFromC.substring(2);
                updateWindowInformation();
            }else if(answerID=='1'){
                updateWindowInformation();
            }else if(answerID=='2'){
                AdminGUI.currentPosition = "NO PERMITIDA";
                updateWindowInformation();
            }else if(answerID=='3'){
                updateWarningInformation("Se ha colocado a Pinky");
                ID = 1;
            }else if(answerID=='4'){
                updateWarningInformation("Pinky ya se encuentra activo");
            }else if(answerID=='5'){
                updateWarningInformation("Se ha colocado a Inky");
                ID = 2;
            }else if(answerID=='6'){
                updateWarningInformation("Inky ya se encuentra activo");
            }else if(answerID=='7'){
                updateWarningInformation("Se ha colocado a Blinky");
                ID = 3;
            }else if(answerID=='8'){
                updateWarningInformation("Blinky ya se encuentra activo");
            }else if(answerID=='a'){
                updateWarningInformation("Se ha colocado a Clyde");
                ID = 4;
            }else if(answerID=='b'){
                updateWarningInformation("Clyde ya se encuentra activo");
            }else if(answerID=='c'){
                updateWarningInformation("Se ha colocado una fruta en " + currentPosition);
                ID = 5;
            }else if(answerID=='d'){
                updateWarningInformation("Se ha colocado una pastilla de poder en " + currentPosition);
                ID = 6;
            }else if(answerID=='e'){
                AdminGUI.currentSpeed = answerFromC.substring(2);
                updateWindowInformation();
                ID = 7;
            }else if(answerID=='f'){
                updateWarningInformation("La velocidad maxima es de " + currentSpeed);
            }else if(answerID=='g'){
                AdminGUI.currentSpeed = answerFromC.substring(2);
                updateWindowInformation();
                ID = 8;
            }else if(answerID=='h'){
                updateWarningInformation("La velocidad minima es de " + currentSpeed);
            }else if(answerID=='i'){
                AdminGUI.currentLives = answerFromC.substring(2);
                updateWindowInformation();
                ID = 9;
            }else if(answerID=='j'){
                AdminGUI.currentLives = answerFromC.substring(2);
                updateWindowInformation();
            }


            String xPos;
            String yPos;
            if(currentPosition.length()==5){
                xPos = currentPosition.substring(1,2);
                yPos = currentPosition.substring(3,4);
            }else if(currentPosition.length()==7){
                xPos = currentPosition.substring(1,3);
                yPos = currentPosition.substring(4,6);
            }else{
                if(currentPosition.charAt(3)==','){
                    xPos = currentPosition.substring(1,3);
                    yPos = currentPosition.substring(4,5);
                }else{
                    xPos = currentPosition.substring(1,2);
                    yPos = currentPosition.substring(3,5);
                }
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id",ID);
            jsonObject.addProperty("xPos",fromStringToInt(yPos));
            jsonObject.addProperty("yPos",fromStringToInt(xPos));
            jsonObject.addProperty("currentSpeed",fromStringToInt(currentSpeed));


            ConnectionToClient.sendMessageToClient(jsonObject.toString());
        }
    }


    public static int fromStringToInt(String number) {
        int result = 0;
        if (number.length() == 2) {
            char primerDigito = number.charAt(0);
            if (primerDigito == '1') {
                result = 10;
            } else if (primerDigito == '2') {
                result = 20;
            } else if (primerDigito == '3') {
                result = 30;
            } else if (primerDigito == '4') {
                result = 40;
            } else if (primerDigito == '5') {
                result = 50;
            } else if (primerDigito == '6') {
                result = 60;
            } else if (primerDigito == '7') {
                result = 70;
            } else if (primerDigito == '8') {
                result = 80;
            } else {
                result = 90;
            }

            char segundoDigito = number.charAt(1);
            if (segundoDigito == '1') {
                result += 1;
            } else if (segundoDigito == '2') {
                result += 2;
            } else if (segundoDigito == '3') {
                result += 3;
            } else if (segundoDigito == '4') {
                result += 4;
            } else if (segundoDigito == '5') {
                result += 5;
            } else if (segundoDigito == '6') {
                result += 6;
            } else if (segundoDigito == '7') {
                result += 7;
            } else if (segundoDigito == '8') {
                result += 8;
            } else {
                result += 9;
            }
        } else {
            char primerDigito = number.charAt(0);
            if (primerDigito == '1') {
                result = 1;
            } else if (primerDigito == '2') {
                result = 2;
            } else if (primerDigito == '3') {
                result = 3;
            } else if (primerDigito == '4') {
                result = 4;
            } else if (primerDigito == '5') {
                result = 5;
            } else if (primerDigito == '6') {
                result = 6;
            } else if (primerDigito == '7') {
                result = 7;
            } else if (primerDigito == '8') {
                result = 8;
            } else {
                result = 9;
            }
        }
        return result;
    }




    public static void analyzeMessageFromClient(String message) throws IOException {
        switch (message) {
            case "n1":
                sendMessageToServerC(14);
                break;
            case "n2":
                sendMessageToServerC(12);
                break;
            case "n3":
                sendMessageToServerC(13);
                break;
            case "n4":
                sendMessageToServerC(15);
                break;
            case "n5":
                sendMessageToServerC(10);
                break;
            case "n6":
                sendMessageToServerC(16);
                break;
            case "n7":
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                sendMessageToServerC(10);
                break;
            case "n8":
                sendMessageToServerC(11);
                break;

        }
    }



    /*
    Metodo utilizado para actualizar la informacion actual de la partida que se esta ejecutando en el cliente en
    pantalla, para que el administrador este al tanto de dicha informacion. Se llama cada vez que se recibe un
    cambio en la puntuacion, en las vidas, en la velocidad o en la posicion seleccionada por el administrador.
    */
    private static void updateWindowInformation() {
        MENU_FRAME.setTitle("PaCE Man Administrador || Posicion " + currentPosition + " || Puntos " + currentPoints +
                 " || Vidas " + currentLives + " || Velocidad " + currentSpeed);
    }


    /*
    Metodo utilizado para actualizar la informacion actual de la partida que se esta ejecutando en el cliente en
    pantalla. Este metodo se llama unicamente cuando se intenta aumentar la velocidad mas alla de 5, se intenta
    disminuir la velocidad menos de 1, se selecciona una posicion no valida para colocar frutas o pastillas, o se
    intenta activar un fantasma que ya se encuentra activo. La finalidad es mostrarle al administrador esta
    informacion para que corrija su seleccion.
    */
    private static void updateWarningInformation(String message){
        MENU_FRAME.setTitle("PaCE Man Administrador || " + message);
    }


    /*
    Clase estatica que hereda de Component. Se utiliza para dibujar el fondo de la ventana por medio del metodo
    paint, inherente a la clase Component. USO DE LA HERENCIA.
    */
    static class CustomPaintComponent extends Component{
        public void paint(Graphics graphicsToDrawWallpaper){
            graphicsToDrawWallpaper.drawImage(ADMIN_MENU_IMAGE,0,0,this);
        }
    }


    /*
    Metodo main del administrador. Se encarga de tres principales procesos. Crea la ventana, genera una nueva
    base de datos, e inicializa la conexion con el cliente en Java.
    */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        AdminGUI.createMenuWindow();
        AdminGUI.createDataBase();
        AdminGUI.MENU_FRAME.addMouseListener(new MouseInput());

        connectionThread.run();


    }
}
