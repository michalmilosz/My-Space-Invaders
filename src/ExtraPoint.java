import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna  za extra punkty
 */
public class ExtraPoint extends Bomb {

    /**
     * Konstruktr klasy ExtraPoint
     * @param x - wspolrzedna x bomby
     * @param y - wspolrzedna y bomby
     */
    public ExtraPoint(int x, int y) {
        super(x, y);
        look = new ImageIcon("icons/extraPoint.gif").getImage();
    }

    /**
     * Funkcja typu void odpowiedzialna za rysowanie extra punktu
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        width = (int) (Config.getWidthOfExtraPoint() * scaleX);
        height = (int) (Config.getHeightOfExtraPoint() * scaleY);

        rec.width = (int) (Config.getWidthOfExtraPoint() * scaleX);
        rec.height = (int) (Config.getHeightOfExtraPoint() * scaleY);

        g.drawImage(look,(int)(positionX*scaleX),(int)(positionY*scaleY),width,height,this);

    }

    /**
     * Funkcja typu void sprawdzajaca czy obiekt extra punktu nie zderzyl sie z graczem lub "ochraniaczami"
     * @param player - obecny gracz
     * @param myProtector1 - "ochraniacz"
     * @param myProtector2 - "ochraniacz"
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void collision(Player player, Protectors myProtector1, Protectors myProtector2, double scaleX, double scaleY){
        Collisions.collisionExtraPointWithPlayer(this, player, scaleX, scaleY);
        Collisions.collisionExtraPointWithProtector(this, myProtector1, scaleX, scaleY);
        Collisions.collisionExtraPointWithProtector(this, myProtector2, scaleX, scaleY);
    }
}
