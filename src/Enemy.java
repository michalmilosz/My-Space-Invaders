import javax.swing.*;
import java.awt.*;

/**
 * klasa odpowiedzialna za wrogow pierwszego rodzaju w grze
 */
public class Enemy extends CharacterInGame {

    public int points;
    public int jump;

    /**
     * konstruktor klasy Enemy
     * @param x - wspolrzedna x wroga
     * @param y - wspolrzedna y wroga
     */
    public Enemy(int x, int y){
        look = new ImageIcon("icons/enemy1.png").getImage();
        points = Config.getPointsEnemy1();
        lifeLevel = Config.getLifeLevelEnemy1();
        jump = Config.getJumpEnemy();
        positionX = x;
        positionY = y;
        width = Config.getWidthOfImage();
        height = Config.getHeightOfImage();
        rec = new Rectangle(positionX,positionY,width,height);
    }

    /**
     * Funkcja odpowiedzialna za poruszanie wroga w prawo
     * @param config - obiekt klasy Config, potrzebny do pobrania wielkosci kroku wroga
     */
    public void moveRight(Config config) {
        positionX+=config.getMoveEnemy();
        rec.x+=config.getMoveEnemy();
    }
    /**
     * Funkcja odpowiedzialna za poruszanie wroga w lewo
     * @param config - obiekt klasy Config
     */
    public void moveLeft(Config config) {
        positionX-=config.getMoveEnemy();
        rec.x-=config.getMoveEnemy();
    }
    /**
     * Funkcja odpowiedzialna za rysowanie wroga
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void draw(Graphics g,double scaleX, double scaleY) {

        width = (int)(Config.getWidthOfImage()*scaleX);
        height = (int)(Config.getHeightOfImage()*scaleY);

        rec.width =(int)(Config.getWidthOfImage()*scaleX);
        rec.height =(int)(Config.getHeightOfImage()*scaleY);

        g.drawImage(look,(int)(positionX*scaleX),(int)(positionY*scaleY),width,height,this);

    }





    public Enemy() {
    }



}
