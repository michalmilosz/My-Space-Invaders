import javax.swing.*;
import java.awt.*;

/**
 * klasa odpowiedzialna za gracza w grze, dziedziczy po klasie CharacterInGame
 */
public class Player extends CharacterInGame {

    public int score;
    public int lifeLevel;
    public String name;

    public boolean ifMove;


    /**
     * Konstruktor klasy Player
     */
    public Player(){
        look = new ImageIcon("icons/bohater.gif").getImage();
        positionX = Config.getPositionOfPlayerX();
        positionY = Config.getPositionOfPlayerY();
        score = 0;
        lifeLevel = Config.getLifeLevelOfPlayer();
        width = Config.getWidthOfImage();
        height = Config.getHeightOfImage();
        rec = new Rectangle(positionX,positionY,width,height);
    }

    /**
     * Funkcja odpowiedzialna za rysowanie gracza
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    //@Override
    public void draw(Graphics g, double scaleX, double scaleY) {


        width = (int)(Config.getWidthOfImage()*scaleX);
        height = (int)(Config.getHeightOfImage()*scaleY);

        rec.width =(int)(Config.getWidthOfImage()*scaleX);
        rec.height =(int)(Config.getHeightOfImage()*scaleY);

        g.drawImage(look,(int)(positionX*scaleX),(int)(positionY*scaleY),width,height,this);


    }

    /**
     * Funkcja dodajaca punkty graczowi
     * @param howManyPoints - parametr mowiacy ile punktow dostal gracz
     */
    public void getScore(int howManyPoints){

            score+=howManyPoints;

    }

    /**
     * Funkcja dodajaca zycie graczowi jesli zlapie on "serce"
     */
    public void getExtraLife(){

        if(lifeLevel<3){
            lifeLevel++;
        }

    }

    /**
     * Funkcja odejmujaca zycie graczowi
     * @param playerWasHit - parametr mowiacy czy gracz ucierpial
     */
    public void  getHurt(boolean playerWasHit){
        if(playerWasHit){
            lifeLevel-=1;
        }
    }

    /**
     * Funkcja typu void odpowiedzialna za poruszanie width lewo
     * @param width - parametr przekazujacy szerokosc okienka gry
     */
    public void moveLeft(int width) {
        if (positionX > width- this.width) {
            positionX = width- this.width;
            rec.x = positionX;
        } else {
            super.moveLeft();
        }
    }

}
