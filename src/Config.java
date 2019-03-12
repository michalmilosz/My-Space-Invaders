import java.io.*;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

/**
 *Klasa odpowiedzialna za plik konfiguracyjny oraz wszelkie stale potrzebne do  poziomów, scenariuszy gry, warunków ukończenia poziomu i gry itp
 */
public class Config {
    private static int level = 1;

    private static int enemiesVertically;
    private static int enemiesHorizontally;
    private static int widthMenu;
    private static int heightMenu;
    private static int widthGamePanel;
    private static int heightGamePanel;
    private static int animationWidth;
    private static int animationHeight;
    private static int widthOfImage;
    private static int heightOfImage;
    private static int widthOfBullet;
    private static int heightOfBullet;

    private static int howManyEnemy1;
    private static int howManyEnemy2;
    private static int howManyEnemy3;

    private static int positionOfPlayerX ;
    private static int positionOfPlayerY ;
    private static int lifeLevelOfPlayer;

    private static int maxLevel;

    private static int startingPositionOfEnemyX;
    private static int chancesToExtraPointLike_1_To;

    private static int timeToSleep;


    private static int chancesToExtraLifeLike_1_To;

    private static int widthOfExtraLife;
    private static int heightOfExtraLife;

    private static int widthOfExtraPoint;
    private static int heightOfExtraPoint;

    private static int widthOfBomb;
    private static int heightOfBomb;

    private static int pointsEnemy1;
    private static int pointsEnemy2;
    private static int pointsEnemy3;

    private static int lifeLevelEnemy1;
    private static int lifeLevelEnemy2;
    private static int lifeLevelEnemy3;

    private static int jumpEnemy;
    private static int moveEnemy;

    private static int extraPoints;


    //DOTYCZY OCHRANIACZY
    private static int positionOfProtectorX;
    private static int positionOfProtectorY;
    private static int lifeLevelOfProtector;
    private static int widthOfProtector;
    private static int heightOfProtector;
    private static int distanceBetweenProtectors;



    private static int _defaultEnemiesVerticle = 5;
    private static int _defaultEnemiesHorizontal = 10;
    private static int _defaultWidthMenu = 300;
    private static int _defaultHeightMenu = 200;
    private static int _defaultWidthMap = 800;
    private static int _defaultHeightMap = 600;
    private static int _defaultAnimationWidth = 1000;
    private static int _defaultAnimationHeight = 1000;
    private static int  _defaultMaxLevel = 3;

    Properties config = new Properties();

    /**
     * konstruktor klasy Config - pobiera dane z pliku i ustawia wszystkie wartosci
     * @param file_Config - parametr przechowujacy sciezke do pliku konfiguracyjnego
     */
    public Config(String file_Config) {
        load_configuration(file_Config);
        setConfig();
        set_enemies();
    }

    /**
     * Config laduje parametry gry z pliku konfiguracyjnego
     * @param file_configuration plik z ktorego mamy pobrac konfiguracje programu
     */
    private void load_configuration(String file_configuration){
        FileInputStream in = null;

        try {
            in = new FileInputStream(file_configuration);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            enemiesVertically = _defaultEnemiesVerticle;
            enemiesHorizontally = _defaultEnemiesHorizontal;
            widthGamePanel = _defaultWidthMap;
            heightGamePanel = _defaultHeightMap;
            widthMenu = _defaultWidthMenu;
            heightMenu = _defaultHeightMenu;
            animationWidth = _defaultAnimationWidth;
            animationHeight = _defaultAnimationHeight;
            maxLevel = _defaultMaxLevel;
        }
    }

    /**
     *Config ustawia liczbe przeciwnikow w zaleznosci od poziomu
     */
    public  void set_enemies(){
        switch(getLevel()){
            case (1):
                howManyEnemy1 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY1_P1"));
                howManyEnemy2 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY2_P1"));
                howManyEnemy3 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY3_P1"));
                timeToSleep = Integer.parseInt(config.getProperty("TIME_TO_SLEEP_P1"));
                enemiesHorizontally = Integer.parseInt(config.getProperty("ENEMIES_HORIZONTAL_P1"));
                jumpEnemy = Integer.parseInt(config.getProperty("JUMP_ENEMY_P1"));
                moveEnemy = Integer.parseInt(config.getProperty("MOVE_ENEMY_P1"));
                enemiesVertically = howManyEnemy1+howManyEnemy2+howManyEnemy3;
                break;
            case (2):
                howManyEnemy1 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY1_P2"));
                howManyEnemy2 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY2_P2"));
                howManyEnemy3 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY3_P2"));
                timeToSleep = Integer.parseInt(config.getProperty("TIME_TO_SLEEP_P2"));
                enemiesHorizontally = Integer.parseInt(config.getProperty("ENEMIES_HORIZONTAL_P2"));
                jumpEnemy = Integer.parseInt(config.getProperty("JUMP_ENEMY_P2"));
                moveEnemy = Integer.parseInt(config.getProperty("MOVE_ENEMY_P2"));
                enemiesVertically = howManyEnemy1+howManyEnemy2+howManyEnemy3;

                break;
            case (3):
                howManyEnemy1 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY1_P3"));
                howManyEnemy2 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY2_P3"));
                howManyEnemy3 = Integer.parseInt(config.getProperty("HOW_MANY_ENEMY3_P3"));
                timeToSleep = Integer.parseInt(config.getProperty("TIME_TO_SLEEP_P3"));
                enemiesHorizontally = Integer.parseInt(config.getProperty("ENEMIES_HORIZONTAL_P3"));
                jumpEnemy = Integer.parseInt(config.getProperty("JUMP_ENEMY_P3"));
                moveEnemy = Integer.parseInt(config.getProperty("MOVE_ENEMY_P3"));
                enemiesVertically = howManyEnemy1+howManyEnemy2+howManyEnemy3;
        }
    }

    /**
     * Funkcja typu void pobierajaca instrukcje z pliku txt
     * @return - zwaraca jest instrukcja w postaci Stringa
     */
    public static String getInstruction() {
        File file = new File ("instruction.txt");
        Scanner in =null;
        try {
            in = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            return null;
        }
        String instruction = null;
        try {
            instruction = ("\n");
            while(true) {
                instruction = instruction+in.nextLine()+("\n");
                if(instruction.equals("Dobrej Zabawy!")) {

                    break;
                }


            }
        }
        catch(NoSuchElementException | IllegalStateException e) {
            try {
                in.close();
            }
            catch(IllegalStateException ise) {
                return null;
            }
        }
        try {
            in.close();
        }
        catch(IllegalStateException ise) {
        }
        return instruction;
    }


    /**
     * Funkcja ustawiajaca wszystkie wartosci w obieckie pobierajac je z pliku txt
     */
    private void setConfig(){
        widthGamePanel = Integer.parseInt(config.getProperty("WIDTH_GAME_PANEL"));
        heightGamePanel = Integer.parseInt(config.getProperty("HEIGHT_GAME_PANEL"));
        widthMenu = Integer.parseInt(config.getProperty("WIDTH_MENU"));
        heightMenu = Integer.parseInt(config.getProperty("HEIGHT_MENU"));
        animationWidth = Integer.parseInt(config.getProperty("ANIMATION_WIDTH"));
        animationHeight = Integer.parseInt(config.getProperty("ANIMATION_HEIGHT"));
        maxLevel = Integer.parseInt(config.getProperty("MAX_LEVEL"));
        chancesToExtraPointLike_1_To = Integer.parseInt(config.getProperty("CHANCES_TO_ENEMY_SHOOT_IN_PERCENT"));
        widthOfProtector =Integer.parseInt(config.getProperty("WIDTH_OF_PROTECTOR"));
        heightOfProtector =Integer.parseInt(config.getProperty("HEIGHT_OF_PROTECTOR"));
        distanceBetweenProtectors = Integer.parseInt(config.getProperty("DISTANCE_BETWEEN_PROTECTORS"));
        positionOfProtectorX = Integer.parseInt(config.getProperty("POSITION_OF_PROTECTOR_X"));
        positionOfProtectorY = Integer.parseInt(config.getProperty("POSITION_OF_PROTECTOR_Y"));
        lifeLevelOfProtector = Integer.parseInt(config.getProperty("LIFE_LEVEL_OF_PROTECTOR"));
        widthOfExtraLife=Integer.parseInt(config.getProperty("WIDTH_OF_EXTRA_LIFE"));
        heightOfExtraLife=Integer.parseInt(config.getProperty("HEIGHT_OF_EXTRA_LIFE"));
        widthOfExtraPoint=Integer.parseInt(config.getProperty("WIDTH_OF_EXTRA_POINT"));
        heightOfExtraPoint=Integer.parseInt(config.getProperty("HEIGHT_OF_EXTRA_POINT"));
        widthOfBomb =Integer.parseInt(config.getProperty("WIDTH_OF_BOMB"));
        heightOfBomb =Integer.parseInt(config.getProperty("HEIGHT_OF_BOMB"));
        startingPositionOfEnemyX = Integer.parseInt(config.getProperty("STARTING_POSITION_OF_ENEMY_X"));
        lifeLevelOfPlayer = Integer.parseInt(config.getProperty("LIFE_LEVEL_OF_PLAYER"));
        widthOfImage = Integer.parseInt(config.getProperty("WIDTH_OF_IMAGE"));
        heightOfImage = Integer.parseInt(config.getProperty("HEIGHT_OF_IMAGE"));
        widthOfBullet = Integer.parseInt(config.getProperty("WIDTH_OF_BULLET"));
        heightOfBullet = Integer.parseInt(config.getProperty("HEIGHT_OF_BULLET"));
        chancesToExtraLifeLike_1_To = Integer.parseInt(config.getProperty("CHANCES_TO_EXTRA_LIFE_LIKE_1_TO"));
        chancesToExtraPointLike_1_To = Integer.parseInt(config.getProperty("CHANCES_TO_EXTRA_POINT_LIKE_1_TO"));
        pointsEnemy1 = Integer.parseInt(config.getProperty("POINTS_ENEMY_1"));
        pointsEnemy2 = Integer.parseInt(config.getProperty("POINTS_ENEMY_2"));
        pointsEnemy3 = Integer.parseInt(config.getProperty("POINTS_ENEMY_3"));
        lifeLevelEnemy1 = Integer.parseInt(config.getProperty("LIFE_LEVEL_ENEMY_1"));
        lifeLevelEnemy2 = Integer.parseInt(config.getProperty("LIFE_LEVEL_ENEMY_2"));
        lifeLevelEnemy3 = Integer.parseInt(config.getProperty("LIFE_LEVEL_ENEMY_3"));
        extraPoints = Integer.parseInt(config.getProperty("EXTRA_POINTS"));



        positionOfPlayerX = animationWidth /2- widthOfImage /2;
        positionOfPlayerY = animationHeight - heightOfImage;

    }

    public static int getWidthOfExtraLife() {
        return widthOfExtraLife;
    }

    public static void setWidthOfExtraLife(int widthOfExtraLife) {
        Config.widthOfExtraLife = widthOfExtraLife;
    }

    public static int getHeightOfExtraLife() {
        return heightOfExtraLife;
    }

    public static void setHeightOfExtraLife(int heightOfExtraLife) {
        Config.heightOfExtraLife = heightOfExtraLife;
    }

    public static int getWidthOfExtraPoint() {
        return widthOfExtraPoint;
    }

    public static void setWidthOfExtraPoint(int widthOfExtraPoint) {
        Config.widthOfExtraPoint = widthOfExtraPoint;
    }

    public static int getHeightOfExtraPoint() {
        return heightOfExtraPoint;
    }

    public static void setHeightOfExtraPoint(int heightOfExtraPoint) {
        Config.heightOfExtraPoint = heightOfExtraPoint;
    }

    public static int getWidthOfBomb() {
        return widthOfBomb;
    }

    public static void setWidthOfBomb(int widthOfBomb) {
        Config.widthOfBomb = widthOfBomb;
    }

    public static int getHeightOfBomb() {
        return heightOfBomb;
    }

    public static void setHeightOfBomb(int heightOfBomb) {
        Config.heightOfBomb = heightOfBomb;
    }

    public static int getHowManyEnemy1() { return howManyEnemy1; }

    public static void setHowManyEnemy1(int howManyEnemy1) { Config.howManyEnemy1 = howManyEnemy1; }

    public static int getHowManyEnemy2() { return howManyEnemy2; }

    public static void setHowManyEnemy2(int howManyEnemy2) { Config.howManyEnemy2 = howManyEnemy2; }

    public static int getHowManyEnemy3() { return howManyEnemy3; }

    public static void setHowManyEnemy3(int howManyEnemy3) { Config.howManyEnemy3 = howManyEnemy3; }

    public static int getEnemiesVertically() { return enemiesVertically; }

    public static void setEnemiesVertically(int enemiesVertically) { Config.enemiesVertically = enemiesVertically; }

    public static int getDistanceBetweenProtectors() { return distanceBetweenProtectors; }

    public static void setDistanceBetweenProtectors(int distanceBetweenProtectors) { Config.distanceBetweenProtectors = distanceBetweenProtectors; }

    public static int getWidthOfProtector() { return widthOfProtector; }

    public static void setWidthOfProtector(int widthOfProtector) { Config.widthOfProtector = widthOfProtector; }

    public static int getHeightOfProtector() { return heightOfProtector; }

    public static void setHeightOfProtector(int heightOfProtector) { Config.heightOfProtector = heightOfProtector; }

    public static int getPositionOfProtectorX() { return positionOfProtectorX; }

    public static void setPositionOfProtectorX(int positionOfProtectorX) { Config.positionOfProtectorX = positionOfProtectorX; }

    public static int getPositionOfProtectorY() { return positionOfProtectorY; }

    public static void setPositionOfProtectorY(int positionOfProtectorY) { Config.positionOfProtectorY = positionOfProtectorY; }

    public static int getLifeLevelOfProtector() { return lifeLevelOfProtector; }

    public static void setLifeLevelOfProtector(int lifeLevelOfProtection) { Config.lifeLevelOfProtector = lifeLevelOfProtection; }

    public static int getAnimationWidth() {
        return animationWidth;
    }

    public static void setAnimationWidth(int animationWidth) {
        Config.animationWidth = animationWidth;
    }

    public static int getAnimationHeight() {
        return animationHeight;
    }

    public static void setAnimationHeight(int animationHeight) {
        Config.animationHeight = animationHeight;
    }

    public static int getChancesToExtraPointLike_1_To() { return chancesToExtraPointLike_1_To; }

    public static void setChancesToExtraPointLike_1_To(int chancesToExtraPointLike_1_To) { Config.chancesToExtraPointLike_1_To = chancesToExtraPointLike_1_To; }

    public static int getLifeLevelOfPlayer() { return lifeLevelOfPlayer; }

    public static void setLifeLevelOfPlayer(int lifeLevelOfPlayer) { Config.lifeLevelOfPlayer = lifeLevelOfPlayer; }

    public static int getStartingPositionOfEnemyX() { return startingPositionOfEnemyX; }

    public static void setStartingPositionOfEnemyX(int startingPositionOfEnemyX) { Config.startingPositionOfEnemyX = startingPositionOfEnemyX; }

    public static int getLevel() { return level; }

    public static void setLevel(int poziom) { Config.level = poziom; }

    public static int getEnemiesHorizontally() { return enemiesHorizontally; }

    public static void setEnemiesHorizontally(int enemiesHorizontally) { Config.enemiesHorizontally = enemiesHorizontally; }

    public static int getWidthMenu() { return widthMenu; }

    public static void setWidthMenu(int widthMenu) { Config.widthMenu = widthMenu; }

    public static int getHeightMenu() { return heightMenu;}

    public static void setHeightMenu(int heightMenu) { Config.heightMenu = heightMenu; }

    public static int getWidthGamePanel() { return widthGamePanel; }

    public static void setWidthGamePanel(int widthGamePanel) { Config.widthGamePanel = widthGamePanel; }

    public static int getHeightGamePanel() { return heightGamePanel; }

    public static void setHeightGamePanel(int heightGamePanel) { Config.heightGamePanel = heightGamePanel; }

    public static int getWidthOfImage() { return widthOfImage; }

    public static void setWidthOfImage(int widthOfImage) { Config.widthOfImage = widthOfImage; }

    public static int getHeightOfImage() { return heightOfImage; }

    public static void setHeightOfImage(int heightOfImage) { Config.heightOfImage = heightOfImage; }

    public static int getWidthOfBullet() { return widthOfBullet; }

    public static void setWidthOfBullet(int widthOfBullet) { Config.widthOfBullet = widthOfBullet; }

    public static int getHeightOfBullet() { return heightOfBullet; }

    public static void setHeightOfBullet(int heightOfBullet) { Config.heightOfBullet = heightOfBullet; }

    public static int getPositionOfPlayerX() { return positionOfPlayerX; }

    public static void setPositionOfPlayerX(int positionOfPlayerX) { Config.positionOfPlayerX = positionOfPlayerX; }

    public static int getPositionOfPlayerY() { return positionOfPlayerY;}

    public static void setPositionOfPlayerY(int positionOfPlayerY) { Config.positionOfPlayerY = positionOfPlayerY; }

    public static int getMaxLevel() { return maxLevel; }

    public static void setMaxLevel(int maxLevel) { Config.maxLevel = maxLevel; }

    public static int getChancesToExtraLifeLike_1_To() {
        return chancesToExtraLifeLike_1_To;
    }

    public static void setChancesToExtraLifeLike_1_To(int chancesToExtraLifeLike_1_To) {
        Config.chancesToExtraLifeLike_1_To = chancesToExtraLifeLike_1_To;
    }

    public static int getTimeToSleep() {
        return timeToSleep;
    }

    public static void setTimeToSleep(int timeToSleep) {
        Config.timeToSleep = timeToSleep;
    }

    public static int getPointsEnemy1() {
        return pointsEnemy1;
    }

    public static void setPointsEnemy1(int pointsEnemy1) {
        Config.pointsEnemy1 = pointsEnemy1;
    }

    public static int getPointsEnemy2() {
        return pointsEnemy2;
    }

    public static void setPointsEnemy2(int pointsEnemy2) {
        Config.pointsEnemy2 = pointsEnemy2;
    }

    public static int getPointsEnemy3() {
        return pointsEnemy3;
    }

    public static void setPointsEnemy3(int pointsEnemy3) {
        Config.pointsEnemy3 = pointsEnemy3;
    }

    public static int getLifeLevelEnemy1() {
        return lifeLevelEnemy1;
    }

    public static void setLifeLevelEnemy1(int lifeLevelEnemy1) {
        Config.lifeLevelEnemy1 = lifeLevelEnemy1;
    }

    public static int getLifeLevelEnemy2() {
        return lifeLevelEnemy2;
    }

    public static void setLifeLevelEnemy2(int lifeLevelEnemy2) {
        Config.lifeLevelEnemy2 = lifeLevelEnemy2;
    }

    public static int getLifeLevelEnemy3() {
        return lifeLevelEnemy3;
    }

    public static void setLifeLevelEnemy3(int lifeLevelEnemy3) {
        Config.lifeLevelEnemy3 = lifeLevelEnemy3;
    }

    public static int getExtraPoints() {
        return extraPoints;
    }

    public static void setExtraPoints(int extraPoints) {
        Config.extraPoints = extraPoints;
    }

    public static int getJumpEnemy() {
        return jumpEnemy;
    }

    public static void setJumpEnemy(int jumpEnemy) {
        Config.jumpEnemy = jumpEnemy;
    }

    public static int getMoveEnemy() {
        return moveEnemy;
    }

    public static void setMoveEnemy(int moveEnemy) {
        Config.moveEnemy = moveEnemy;
    }

}
