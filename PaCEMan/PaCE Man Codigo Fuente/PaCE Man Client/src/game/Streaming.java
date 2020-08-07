package game;


import java.io.File;

public class Streaming implements Runnable{

    public Streaming(){
        deletePreviousStream();
    }

    public void deletePreviousStream(){
        File dir = new File("Streaming");
        for(File file: dir.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
                Game.takeScreen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
