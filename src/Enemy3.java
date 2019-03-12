import javax.swing.*;
import java.awt.*;

/**
 * klasa odpowiedzialna za wrogow trzeciego rodzaju w grze
 */
public class Enemy3 extends Enemy{

    /**
     * konstruktor klasy Enemy3
     * @param x - wspolrzedna x wroga
     * @param y - wspolrzedna y wroga
     */
    public Enemy3(int x, int y) {
        look = new ImageIcon("icons/enemy3.png").getImage();
        points = Config.getPointsEnemy3();
        lifeLevel = Config.getLifeLevelEnemy3();
        jump = Config.getJumpEnemy();
        positionX = x;
        positionY = y;
        width = Config.getWidthOfImage();
        height = Config.getHeightOfImage();
        rec = new Rectangle(positionX,positionY,width,height);;
    }

    /**
     * Funkcja odpowiedzialna za rysowanie wroga
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        width = (int)(Config.getWidthOfImage()*scaleX);
        height = (int)(Config.getHeightOfImage()*scaleY);

        rec.width =(int)(Config.getWidthOfImage()*scaleX);
        rec.height =(int)(Config.getHeightOfImage()*scaleY);

        g.drawImage(look,(int)(positionX*scaleX),(int)(positionY*scaleY),width,height,this);

    }
}
