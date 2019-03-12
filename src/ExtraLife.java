import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna  za extra zycia
 */
public class ExtraLife extends Bomb {

    /**
     * Konstruktr klasy ExtraLife
     * @param x - wspolrzedna x bomby
     * @param y - wspolrzedna y bomby
     */
    public ExtraLife(int x, int y){
        super(x, y);
        look = new ImageIcon("icons/extraLife.png").getImage();
    }

    /**
     * Funkcja typu void odpowiedzialna za rysowanie obiektu extra zycia
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        width = (int) (Config.getWidthOfExtraLife() * scaleX);
        height = (int) (Config.getHeightOfExtraLife() * scaleY);

        rec.width = (int) (Config.getWidthOfExtraLife() * scaleX);
        rec.height = (int) (Config.getHeightOfExtraLife() * scaleY);

        g.drawImage(look,(int)(positionX*scaleX),(int)(positionY*scaleY),width,height,this);

    }

    /**
     * Funkcja typu void sprawdzajaca czy obiekt extra zycia nie zderzyl sie z graczem lub "ochraniaczami"
     * @param player - obecny gracz
     * @param myProtector1 - "ochraniacz"
     * @param myProtector2 - "ochraniacz"
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void collision(Player player, Protectors myProtector1, Protectors myProtector2, double scaleX, double scaleY){
        Collisions.collisionExtraLivesWithPlayer(this, player, scaleX, scaleY);
        Collisions.collisionExtraLifeWithProtector(this, myProtector1, scaleX, scaleY);
        Collisions.collisionExtraLifeWithProtector(this, myProtector2, scaleX, scaleY);
    }
}