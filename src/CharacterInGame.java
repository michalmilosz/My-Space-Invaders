import java.awt.*;

/**
 * Abstrakcyjna klasa z ktorej dziedzicza wszystkie elementy gry
 */
public abstract class CharacterInGame extends Component {
    public int positionX;
    public int positionY;
    public int width;
    public int height;
    public int lifeLevel;


    public Rectangle rec = new Rectangle();
    Image look;
    int life;

    /**
     * Funkcja odpowiedzialna za rysowanie
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void draw(Graphics g,int scaleX, int scaleY){ }

    /**
     * Funkcja typu void odpowiedzialna za poruszanie w lewo
     */
    public void moveLeft(){
        if(positionX>0) {
            positionX -= 2;
            rec.x = positionX;
        }
    }

    /**
     * Funkcja typu void odpowiedzialna za poruszanie width prawo
     * @param width - szerokosc okienka gry
     * @param scaleX - paramter typu double potrzebny do skalowania
     */
    public void moveRight(int width,double scaleX){

        if (positionX*scaleX+ this.width <width) {
            positionX+=2;
            rec.x =positionX;
        }

    }

}
