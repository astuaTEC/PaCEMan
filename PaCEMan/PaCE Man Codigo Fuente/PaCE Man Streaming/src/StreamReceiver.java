/*
Thread encargado de esperar 200 milisegundos y mostrar un screenshot del juego
*/
public class StreamReceiver implements Runnable{

    StreamWindow streamWindow;


    public StreamReceiver(StreamWindow framee){
        this.streamWindow=framee;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(200);
                streamWindow.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}