import javax.swing.*;
import java.awt.*;

/*
Clase encargada del manejo del streaming. Recibe las imagenes que le llegan de el cliente principal
*/
class StreamWindow extends JFrame {

    private static final StreamWindow streamWindow = new StreamWindow();
    private static int currentFrame;

    private static JLabel streamLabel;
    private static ImageIcon icon;

    private static final Image ICON = Toolkit.getDefaultToolkit().getImage
            ("GraphicResources/Icon.png");


    public StreamWindow() {
        setTitle("PaCE Man Espectador");
        setIconImage(ICON);
        setSize(900,660);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("Streaming/1.png")));
        setLayout(new FlowLayout());

        setSize(899,619);
        setSize(900,660);
    }

    public void update() {
        icon = new ImageIcon("Streaming/"+ currentFrame +".png");
        streamLabel.setIcon(icon);
        streamLabel.repaint();
        streamWindow.setContentPane(streamLabel);
        currentFrame++;
    }

    public static void main(String[] args) {
        currentFrame=1;

        icon = new ImageIcon("Streaming/"+ currentFrame +".png");
        streamLabel = new JLabel(icon);
        streamWindow.setContentPane(streamLabel);

        StreamReceiver stream = new StreamReceiver(streamWindow);
        stream.run();
    }
}