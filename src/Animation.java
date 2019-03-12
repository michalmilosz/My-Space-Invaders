import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * Klasa Animation tworzaca animacje gry.
 */
public class Animation extends Canvas implements Runnable, KeyListener, ActionListener {

    public Thread kicker = null;

    Config config  ;
    private BestScores bestScores ;

    //Tworzenie guzików potrzebnych do zakończenia gry
    private JButton exitToMenu = new JButton();
    private JButton gameFromBeginning = new JButton();

    //Utworzenie obiektu listy najlepszych wyników
    private BestScores bestScoresList;

    //Zmienne potrzebna do skalowania.
    public double scaleX;
    public double scaleY;

    //Utworzenie ochraniaczy
    private Protectors myProtector1 = new Protectors(0);
    private Protectors myProtector2 = new Protectors(Config.getDistanceBetweenProtectors());

    //Utworzenie grzacza
    private Player myPlayer = new Player();
    //Zmienna używana do określenia kierunku ruchu gracza
    private int ifMovePlayer = 0;

    //Utworzenie tablicy wrogów
    private ArrayOfEnemies myEnemies = new ArrayOfEnemies();

    //Utworzenie obiektu dodatkowego życia
    private ExtraLife extraLife = null;

    //Utworzenie obiektu dodatkowego punktu
    private ExtraPoint extraPoint = null;

    //stworzenie pocisku od wroga
    private Bomb bomb = null;

    // tworzony jest pocisk
    private Bullet bullet = null;





    /**
     * Zmienne używane w podwójnym buforze.
     */
    private Image offscr = null;
    private Graphics offscrgr = null;


    /**
     * Kostruktor Animacji - ustawiny jest rozmiar, słuchacz zdarzeń związanych
     * z nacisnięciem klawiszy klawiatury, ustawienie skupienia
     *
     * @param
     */
    /**
     * Kostruktor Animacji - ustawiny jest rozmiar, słuchacz zdarzeń związanych
     * z nacisnięciem klawiszy klawiatury, ustawienie skupienia
     * @param myPlayerName - parametr przekazujacy nick gracza
     * @param c - obiekt klasy Config
     * @param bS - obiekt klasy BestScores
     * @param exitToMenu - przycisk JButton potrzebny do wyjscia do menu po zakonczeniu gry
     * @param gameFromBeginning - przycisk JButton potrzebny do tego, aby ponownie zaczac gre po zakonczeniu rozgrywki
     */
    Animation(String myPlayerName,Config c, BestScores bS, JButton exitToMenu, JButton gameFromBeginning) {

        config = c;
        bestScores = bS;

        this.exitToMenu = exitToMenu;
        this.exitToMenu.addActionListener(this);

        this.gameFromBeginning = gameFromBeginning;
        this.gameFromBeginning.addActionListener(this);

        kicker = null;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        bestScoresList  = new BestScores("best_scores.txt");
        myPlayer.name = myPlayerName;


    }


    /**
     * Funkcja ustawiająca domyślny wymiar okna.
     *
     * @return - zwraca nowe wymiry.
     */

    public Dimension getPreferredSize() {
        return new Dimension(Config.getAnimationWidth(), Config.getAnimationHeight());
    }

    /**
     * Ramka jest wyświetlana, łącząc ją z natywnym zasobem ekranu.
     */
    public void addNotify() {
        super.addNotify();
        offscr = createImage(getPreferredSize().width, getPreferredSize().height);
        offscrgr = offscr.getGraphics();

        // Utworzenie wrogów
        myEnemies.MadeEnemies(offscrgr,1,1,config);
    }

    /**
     * Uaktualnienie obrazu.
     *
     * @param g - zmiennna Graphics.
     */
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * Funkcja rysująca obraz na ekranie.
     *
     * @param g - zmienna Graphics.
     */
    public void paint(Graphics g) {
        g.drawImage(offscr, 0, 0, this);
    }


    /**
     * Funkcja służąca do rysowania obrazu w buforze.
    */
    private void updateOffscreen() {



        //Zmienne potrzebne do wylosowania który wróg wyrzuci bombę, extra punkt czy extra życie
        Random lottery = new Random();
        int enemyX;
        int enemyY;
        int ifEnemyShoot = 0;

        // zmienne potrzebna do skalowania.
        double XXX = getWidth();
        double YYY = getHeight();

        scaleX = XXX / Config.getAnimationWidth();
        scaleY = YYY / Config.getAnimationHeight();

        //czyszczenie ekranu, ktory nie jest na wierzchu
        offscrgr.clearRect(0, 0, getWidth(), getHeight());

        //ustawienie czcionki informacji wypisywanych na ekran
        Font font = new Font("SanSerif", Font.ITALIC, 25);
        offscrgr.setFont(font);

        //Wypisanie na ekran informacje o poziomie, liczbie zyc i zdobytych punktach
        String nameLevel = "Poziom nr " + Config.getLevel();
        int positionXofNameLevel = getWidth() / 2 - offscrgr.getFontMetrics().stringWidth(nameLevel) / 2; // 1/2 szerokości panelu odjąć 1/2 szerokości tekstu
        offscrgr.setColor(Color.black);
        offscrgr.drawString(nameLevel, positionXofNameLevel, 20);
        String score = "Zdobyte punkty = " + myPlayer.score;
        int positionXofScore = getWidth() - offscrgr.getFontMetrics().stringWidth(score);
        offscrgr.drawString(score, positionXofScore, 20);
        String numbersOfLifes = "Liczba zyc = " + myPlayer.lifeLevel;
        int positionXofNumbersOfLife = positionXofScore;
        offscrgr.drawString(numbersOfLifes, positionXofNumbersOfLife, 50);


        //rysowanie wrogów
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                if (myEnemies.enemies[i][j] != null) {
                    myEnemies.enemies[i][j].draw(offscrgr, scaleX, scaleY);
                }
            }
        }

        //rysowanie gracza i poruszanie nim
        myPlayer.draw(offscrgr, scaleX, scaleY);
        if (ifMovePlayer == 1) {
            myPlayer.moveRight(getWidth(), scaleX);
        } else {
            if (ifMovePlayer == 2) {
                myPlayer.moveLeft(getWidth());
            }
        }

        //rysowanie ochraniaczy
        if (myProtector1 != null) {
            myProtector1.draw(offscrgr, scaleX, scaleY, myProtector1.rec.x, myProtector1.rec.y);
            if (myProtector1.lifeLevel == 0) {
                myProtector1 = null;
            }
        }
        if (myProtector2 != null) {
            myProtector2.draw(offscrgr, scaleX, scaleY, myProtector2.rec.x, myProtector2.rec.y);
            if (myProtector2.lifeLevel == 0) {
                myProtector2 = null;
            }
        }

        //generowanie bomby- możliwy tylko 1 bomba jednocześnie
        enemyX = lottery.nextInt(Config.getEnemiesVertically());
        enemyY = lottery.nextInt(Config.getEnemiesHorizontally());
        if (myEnemies.enemies[enemyX][enemyY] != null) {
            if (bomb == null) {
                bomb = new Bomb((int) ((myEnemies.enemies[enemyX][enemyY].positionX + myEnemies.enemies[enemyX][enemyY].width / (2 * scaleX))), myEnemies.enemies[enemyX][enemyY].positionY + myEnemies.enemies[enemyX][enemyY].height);
                bomb.ifExist = true;

            }
        }

        //generowanie ekstra punktów- możliwy tylko 1 dodtkowe punkt jednocześnie
        enemyX = lottery.nextInt(Config.getEnemiesVertically());
        enemyY = lottery.nextInt(Config.getEnemiesHorizontally());
        if (myEnemies.enemies[enemyX][enemyY] != null) {

            ifEnemyShoot = lottery.nextInt(Config.getChancesToExtraPointLike_1_To());
            if (ifEnemyShoot == 1 && extraPoint == null) {
                extraPoint = new ExtraPoint((int) ((myEnemies.enemies[enemyX][enemyY].positionX + myEnemies.enemies[enemyX][enemyY].width / (2 * scaleX))), myEnemies.enemies[enemyX][enemyY].positionY + myEnemies.enemies[enemyX][enemyY].height);
                extraPoint.ifExist = true;

            }
        }

        //generowanie ekstra żyć - możliwy tylko 1 dodtkowe życie jednocześnie
        enemyX = lottery.nextInt(Config.getEnemiesVertically());
        enemyY = lottery.nextInt(Config.getEnemiesHorizontally());
        if (myEnemies.enemies[enemyX][enemyY] != null) {

            ifEnemyShoot = lottery.nextInt(Config.getChancesToExtraLifeLike_1_To());
            if (ifEnemyShoot == 1 && extraLife == null) {
                extraLife = new ExtraLife((int) ((myEnemies.enemies[enemyX][enemyY].positionX + myEnemies.enemies[enemyX][enemyY].width / (2 * scaleX))), myEnemies.enemies[enemyX][enemyY].positionY + myEnemies.enemies[enemyX][enemyY].height);
                extraLife.ifExist = true;

            }
        }


        //rysowanie bomby i jej obsługa
        if (bomb != null) {
            //rysowanie bomby i przesuwanie jej
            bomb.draw(offscrgr, scaleX, scaleY);
            bomb.move(getHeight(), scaleY);

            //sprawdzanie kolizji bomby z graczem oraz z ochraniaczami
            bomb.collision(myPlayer,myProtector1,myProtector2,scaleX,scaleY);

            if (!bomb.ifExist) {
                bomb = null;
            }
        }

        //rysowanie exta punktu w postaci monety
        if (extraPoint != null) {
            //rysowanie pocisku i jego przesuwanie
            extraPoint.draw(offscrgr, scaleX, scaleY);
            extraPoint.move(getHeight(), scaleY);

            //sprawdzanie kolizji dodatkowego punktu z graczem oraz z ochraniaczami
            extraPoint.collision(myPlayer,myProtector1,myProtector2,scaleX,scaleY);

            if (!extraPoint.ifExist) {
                extraPoint = null;
            }
        }

        //rysowanie ekstra zycia w postaci serca
        if (extraLife != null) {
            //rysowanie pocisku i jego przesuwanie
            extraLife.draw(offscrgr, scaleX, scaleY);
            extraLife.move(getHeight(), scaleY);

            //sprawdzanie kolizji dodatkowego zycia z graczem oraz z ochraniaczami
            extraLife.collision(myPlayer, myProtector1, myProtector2, scaleX, scaleY);

            if (!extraLife.ifExist) {
                extraLife = null;
            }
        }


        // rysowanie pocisku
        if (bullet != null) {
            //rysowanie pocisku i jego przesuwanie
            bullet.draw(offscrgr, scaleX, scaleY);
            bullet.move();

            //sprawdzanie kolizji pocisku gracza z wrogami oraz z ochraniaczami
            bullet.collisions(myEnemies,myPlayer,scaleX,scaleY,myProtector1,myProtector2);
            if (!bullet.ifExist) {
                bullet = null;
            }
        }
    }


    void updateOffscreenSize(final int w, final int h) {
        if (kicker != null) {
            Thread k = kicker;
            kicker = null;
            k.interrupt();
        }
        offscr = createImage(w, h);
        offscrgr = offscr.getGraphics();
        (kicker = new Thread(this)).start();
    }

    /**
     * Funckja sluzaca do uspienia watku na jakis czas okreslony w pliku konfiguracyjnym
     */
    private void sleeep() {
        try {
            Thread.sleep(config.getTimeToSleep());
        } catch (InterruptedException ie) {
        }
    }

    /**
     * Funkcja run watku
     */
    public void run() {

        while (kicker == Thread.currentThread()) {

                updateOffscreen();
                //uruchamiana jest funkcja poruszająca wrogami
                myEnemies.MoveEnemies(getWidth(), scaleX, scaleY,config);

                //Rozwarzanie przypadku, gdy nie ma wrogów
                if (myEnemies.ifEnemyEmpty()) {
                    try {
                        bullet.ifExist = false;
                        bullet = null;
                        bomb = null;
                        extraPoint = null;
                        extraLife = null;
                        myProtector1 = null;
                        myProtector2 = null;

                        updateOffscreen();
                        repaint();
                        Config.setLevel(Config.getLevel()+1);

                        Thread.sleep(1000);
                        /**
                         * Warunek konczacy gre, gdy gracz ukonczy wszystkie poziomy
                         */
                        if(Config.getLevel()> Config.getMaxLevel()){
                            bestScoresList.setScoreList(myPlayer, "best_scores.txt");
                            /**
                             * Ustawienie poziomu ponownie na 1.
                             */
                            Config.setLevel(1);
                            EndPanel koniec = new EndPanel(exitToMenu,gameFromBeginning,config,bestScores, myPlayer,"Wygrales!");
                            koniec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            koniec.setVisible(true);
                            koniec.toFront();
                            kicker = null;
                            break;
                        }else{
                            myEnemies.MadeEnemies(offscrgr,scaleX,scaleY,config);
                            myProtector1 = new Protectors(0);
                            myProtector2 = new Protectors(Config.getDistanceBetweenProtectors());
                        }


                    } catch (InterruptedException ie) {
                        System.out.println("ZLE...");
                    }
                }

            //Warunek kończocy grę gdy wrogowie zderzą się z ochraniaczem lub z graczem
            if (myPlayer.lifeLevel == 0||Collisions.collisionEnemyWithProtector(myEnemies,myProtector1,scaleX,scaleY)||Collisions.collisionEnemyWithProtector(myEnemies,myProtector2,scaleX,scaleY)||Collisions.collisionEnemyWithPlayer(myEnemies,myPlayer,scaleX,scaleY)){
                killAllEnemies();

                bullet = null;
                bomb = null;
                extraPoint = null;
                extraLife = null;
                myProtector1 = null;
                myProtector2 = null;

                bestScoresList.setScoreList(myPlayer, "best_scores.txt");
                updateOffscreen();
                repaint();

                EndPanel koniec = new EndPanel(exitToMenu,gameFromBeginning,config,bestScores, myPlayer,"Przegrales!");
                koniec.setVisible(true);
                koniec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                koniec.toFront();
                kicker = null;
            }

            repaint();
            sleeep();

        }
    }

    /**
     * Funckja zabijaca wszystkich wrogow
     */
    protected void killAllEnemies(){
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                if (myEnemies.enemies[i][j] != null) {
                    myEnemies.enemies[i][j]= null;
                }

            }
        }
    }

    /**
     * Funkcja wywoływana, gdy wciśniemy przycisk
     * @param e - akcja przycisniecia klawisza
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ifMovePlayer =1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ifMovePlayer = 2;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if ( bullet == null) {
                bullet = new Bullet((int)(myPlayer.positionX+myPlayer.width/(2*scaleX)-Config.getWidthOfBullet()/(2*scaleX)), myPlayer.positionY-Config.getHeightOfBullet());
                bullet.ifExist = true;
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     *Funkcja wywoływana, gdy puścimy przycisk
     * @param e - akcja przycisniecia klawisza
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            ifMovePlayer = 0;

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            ifMovePlayer = 0;
        }
    }
}
