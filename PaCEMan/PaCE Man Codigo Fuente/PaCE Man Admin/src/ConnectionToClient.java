import java.net.*;
import java.io.*;


/*
Clase que describe a los objetos de tipo ConnectionToClient. Tal se indica, implementa la clase Runnable, por lo que
debe sobrescribirse el metodo run de la misma. Este objeto se encarga de la conexion directa entre cliente y servidor,
y este ultimo, a su vez, llama al codigo escrito en C que realiza las validaciones correspondientes antes de generar
cada una de las respuestas.
*/
public class ConnectionToClient implements Runnable{


    // Atributo de tipo Socket que almacena el socket para la comunicacion con el cliente.
    private Socket socketToClient;


    // Atributo de tipo ServerSocket que almacena el socket de servidor para la comunicacion con el cliente
    private ServerSocket serverSocketToClient;


    /*
    Atributos estaticos de tipo DataInputStream y DataOutputStream, que se encargan de almacenar los mensajes
    que llegan del cliente y que van hacia el cliente, respectivamente.
    */
    private static DataInputStream messageFromClient = null;
    private static DataOutputStream messageToClient = null;


    /*
    Metodo encargado de revisar el socket de cliente para determinar si existe un mensaje para enviar o un
    nuevo mensaje recibido. Estos mensajes se asignan a messageFromClient y messageToClient respectivamente,
    y pueden ser valores de tipo null (en caso no haber mensajes que recibir o mensajes por enviar).
     */
    private void checkSocketForMessages() {
        try {
            messageFromClient = new DataInputStream(socketToClient.getInputStream());
            messageToClient = new DataOutputStream(socketToClient.getOutputStream());
            messageToClient.flush();
        } catch (IOException ignored) {
        }
    }


    /*
    Metodo encargado de enviar los mensajes al cliente. Lo que hace realmente es llenar el messageToClient
    con el mensaje que se quiere enviar.
    */
    public static void sendMessageToClient(String message) throws IOException {
        messageToClient.writeUTF(message);
        messageToClient.flush();
    }


    /*
    Metodo encargado de detener la conexion con el cliente. Esto hace posible que se pueda conectar un cliente,
    jugar una partida y desconectarse, dandole paso asi a un nuevo cliente. Limpia los contenedores de mensajes
    del cliente y para el cliente, ademas de cerrar el socket y reiniciar la base de datos, al tratarse de una
    nueva partida.
    */
    private void stopConnectionToClient() {
        try {
            messageFromClient.close();
            messageToClient.close();
            socketToClient.close();
        } catch (IOException ignored) {
        }
    }


    /*
    Metodo encargado de iniciar la conexion con el cliente. Define que el clientThread se ejecutara de forma
    indefinida, hasta que ocurra un error en la conexión o hasta que el cliente se desconecte.
    */
    private void startConnectionToClient() {
        Thread clientThread = new Thread(() -> {
            while (true) {
                try {
                    try {
                        serverSocketToClient = new ServerSocket(AdminGUI.connectionPort);
                        socketToClient = serverSocketToClient.accept();
                    } catch (Exception exceptionWhenConnecting) {
                        System.exit(0);
                    }
                    checkSocketForMessages();
                    getMessageFromClient();
                } finally {
                    stopConnectionToClient();
                }
            }
        });
        clientThread.start();
    }


    /*
    Metodo encargado de recibir el mensaje del cliente, por medio de leer el messageToClient. En funcion de lo
    que se reciba aqui, se debe comunicar con el codigo en C y con la base de datos, con el fin de generar una
    respuesta al cliente.
    */
    public void getMessageFromClient() {
        try {
            do {
                AdminGUI.analyzeMessageFromClient(messageFromClient.readUTF());



            } while(true);
        } catch (IOException exception) {
            stopConnectionToClient();
        }
    }


    /*
    Metodo sobreescrito de la clase Runnable, que define el comportamiento que deben ejecutar los objetos de tipo
    ConnectionToClient. Se encarga de agregar el control por medio de mouse a la ventana del administrador, ademas de
    iniciar la conexion con el cliente. USO DE LA SOBREESCRITURA.
    */
    @Override
    public void run() {
        startConnectionToClient();
    }
}