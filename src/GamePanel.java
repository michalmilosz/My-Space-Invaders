import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Klasa GamePanel dziedziczaca po JFrame. Dzieki niej uzyskujemy mape.
 */
public class GamePanel extends JFrame implements ActionListener, KeyListener{

    Config config;
    BestScores bestScores;
    JButton exitToMenu = new JButton("Wyjscie do menu");
    JButton gameFromBeginning = new JButton("Rozpocznij od poczatku");
    Thread kick;

    /**
     * Kontruktor klasy GamePanel
     * @param myPlayerName - parametr typu string przekazujacy nick gracza
     * @param c - obiekt klasy Config
     * @param bS - obiekt klasy BestScores
     */
    public GamePanel(String myPlayerName, Config c, BestScores bS) {

        config = c;
        bestScores = bS;

        Animation board = new Animation(myPlayerName, config,bS,exitToMenu,gameFromBeginning) ;

        setSize(Config.getWidthGamePanel(), Config.getHeightGamePanel());
        setBounds(200,100,Config.getWidthGamePanel(), Config.getHeightGamePanel());

        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board.setBackground(Color.gray);

       this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent ce) {
                board.updateOffscreenSize(ce.getComponent().getWidth(), ce.getComponent().getHeight());
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        pack();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                board.setVisible(true);
            }

        });

        kick = new Thread(board);
        kick.start();

        pack();

        exitToMenu.addActionListener(this);
        gameFromBeginning.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == exitToMenu){
            dispose();
        }
        if(src == gameFromBeginning){
            dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
