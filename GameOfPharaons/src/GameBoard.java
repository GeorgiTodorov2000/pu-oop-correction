import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {
    public static final int TILE_X_COUNT = 13;
    public static final int TILE_Y_COUNT = 13;
    private Object[][] pieceCollection;
    private Object selectedPiece;
    private Object attackPiece;
    private Random RANDOM = new Random();
    private int TURN_COUNTER = 0;

    JLabel l1 = null;

    private int P1_MP = 10;
    private int P2_MP = 10;

    private int P1_PIECE_COUNTER = 18;
    private int P2_PIECE_COUNTER = 18;

    private int P1_DOG_EATING_BUG = 0;
    private int P1_DRUNKEN_KNIGHT = 0;
    private int P1_MAGIC_CAT = 0;
    private int P1_RECKLESS_CANIBAL = 0;
    private int P1_SAND_TURTLE = 0;
    private int P1_SOFISTICATED_SAM = 0;
    private int P2_DOG_EATING_BUG = 0;
    private int P2_DRUNKEN_KNIGHT = 0;
    private int P2_MAGIC_CAT = 0;
    private int P2_RECKLESS_CANIBAL = 0;
    private int P2_SAND_TURTLE = 0;
    private int P2_SOFISTICATED_SAM = 0;

    private int warMagicUsed = 0;

    public GameBoard() {
        this.pieceCollection = new Object[TILE_X_COUNT][TILE_Y_COUNT];
        RANDOM = new Random();

        pieceCreator();

        this.setSize(900, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 1; row < 13; ++row) {
            for(int col = 1; col < 13; ++col) {
                GameTiles tile = new GameTiles(row, col);
                tile.render(g);

                if(this.hasBoardPiece(row, col)) {
                    Figure figure = (Figure) this.getBoardPiece(row, col);
                    figure.render(g);

                }

            }
        }
    }

    private int playerTurn() {
        if(TURN_COUNTER % 2 == 0) {

            return 1;
        } else {

            return 2;
        }
    }


    public void pieceCreator() {

        for (int i = 0; i < 5; i++) {
            int monsterType = RANDOM.nextInt(6);
            monsterType++;
            int x = RANDOM.nextInt(3);
            int y = RANDOM.nextInt(11);
            x++;
            y++;
            switch (monsterType) {

                case 2:
                    this.pieceCollection[x][y] = new DogEatingBug(x, y, 11, 10, 2, 8, 5,2);
                    P1_DOG_EATING_BUG++;
                    break;
                case 1:
                    this.pieceCollection[x][y] = new DrunkenKnight(x, y, 12, 5, 5, 5, 5,5);
                    P1_DRUNKEN_KNIGHT++;
                    break;
                case 3:
                    this.pieceCollection[x][y] = new MagicCat(x, y, 13, 8, 5, 10, 1,5);
                    P1_MAGIC_CAT++;
                    break;
                case 4:
                    this.pieceCollection[x][y] = new RecklessCanibal(x, y, 14, 4, 6, 8, 10,6);
                    P1_RECKLESS_CANIBAL++;
                    break;
                case 5:
                    this.pieceCollection[x][y] = new SandTurtle(x, y, 15, 5, 5, 5, 5,5);
                    P1_SAND_TURTLE++;
                    break;
                case 6:
                    this.pieceCollection[x][y] = new SofisticatedSam(x, y, 16, 10, 5, 4, 1,5);
                    P1_SOFISTICATED_SAM++;
                    break;
            }

        }
        for (int i = 0; i < 5; i++) {
            int monsterType = RANDOM.nextInt(6);
            monsterType++;
            int x = 9;
            int changeX = RANDOM.nextInt(3);
            int y = RANDOM.nextInt(12);
            x = changeX + x;
            x++;
            y++;

            switch (monsterType) {

                case 1:
                    this.pieceCollection[x][y] = new DogEatingBug(x, y, 21, 10, 2, 8, 5,2);
                    P2_DOG_EATING_BUG++;
                    break;
                case 2:
                    this.pieceCollection[x][y] = new DrunkenKnight(x, y, 22, 5, 5, 5, 5,5);
                    P2_DRUNKEN_KNIGHT++;
                    break;
                case 3:
                    this.pieceCollection[x][y] = new MagicCat(x, y, 23, 8, 5, 10, 1,5);
                    P2_MAGIC_CAT++;
                    break;
                case 4:
                    this.pieceCollection[x][y] = new RecklessCanibal(x, y, 24, 4, 6, 8, 10,6);
                    P2_RECKLESS_CANIBAL++;
                    break;
                case 5:
                    this.pieceCollection[x][y] = new SandTurtle(x, y, 25, 5, 10, 1, 4,10);
                    P2_SAND_TURTLE++;
                    break;
                case 6:
                    this.pieceCollection[x][y] = new SofisticatedSam(x, y, 26, 10, 5, 4, 1,5);
                    P2_SOFISTICATED_SAM++;
                    break;
            }

        }

        }

    private boolean checkPlayerMP(int requiredMP) {
        if(playerTurn() == 1) {
            if(P1_MP >= requiredMP) {
                P1_MP -= requiredMP;
                return true;
            }
        } else {
            if(P2_MP >= requiredMP) {
                P1_MP -= requiredMP;
                return true;
            }
        }
        System.out.println("Not enough MP");
        return false;
    }

    public int isThatFigureYours() {
        Figure figure = (Figure) this.selectedPiece;
        if(figure.getId() < 20 && playerTurn() == 2) {
            System.out.println("Only player 1 can move this");
            return 0;
        } else if(figure.getId() > 20 && playerTurn() == 1) {
            System.out.println("Only player 2 can move this");
            return 0;
        }
        return 1;
    }


    public void newTurnPieceCreator() {
        if(playerTurn() == 1) {

                int monsterType = RANDOM.nextInt(6);
                monsterType++;
                int x = RANDOM.nextInt(3);
                int y = RANDOM.nextInt(11);
                x++;
                y++;
                if (!isTileEmpty(x, y)) {
                    x = RANDOM.nextInt(3);
                    y = RANDOM.nextInt(11);
                    x++;
                    y++;
                }

                switch (monsterType) {

                    case 1:

                        if(P1_DOG_EATING_BUG < 3) {
                            this.pieceCollection[x][y] = new DogEatingBug(x, y, 11, 10, 2, 8, 5,2);
                            P1_DOG_EATING_BUG++;
                            break;
                        }

                    case 2:

                        if(P1_DRUNKEN_KNIGHT < 3) {
                            this.pieceCollection[x][y] = new DrunkenKnight(x, y, 12, 5, 5, 5, 5,5);
                            P1_DRUNKEN_KNIGHT++;
                            break;
                        }
                    case 3:

                        if(P1_MAGIC_CAT < 3) {
                            this.pieceCollection[x][y] = new MagicCat(x, y, 13, 8, 5, 10, 1,5);
                            P1_MAGIC_CAT++;
                            break;
                        }
                    case 4:
                        if(P1_RECKLESS_CANIBAL < 3) {
                            this.pieceCollection[x][y] = new RecklessCanibal(x, y, 14, 4, 6, 8, 10,6);
                            P1_RECKLESS_CANIBAL++;
                            break;
                        }
                    case 5:

                        if(P1_SAND_TURTLE < 3) {
                            this.pieceCollection[x][y] = new SandTurtle(x, y, 15, 5, 5, 5, 5,5);
                            P1_SAND_TURTLE++;
                            break;
                        }
                    case 6:

                        if(P1_SOFISTICATED_SAM < 3) {
                            this.pieceCollection[x][y] = new SofisticatedSam(x, y, 16, 10, 5, 4, 1,5);
                            P1_SOFISTICATED_SAM++;
                            break;
                        }



            }

        } else {

                int monsterType = RANDOM.nextInt(6);
                monsterType++;
                int x = 9;
                int changeX = RANDOM.nextInt(3);
                int y = RANDOM.nextInt(12);
                x = changeX + x;
                x++;
                y++;
                if (!isTileEmpty(x, y)) {
                    x = 9;
                    changeX = RANDOM.nextInt(3);
                    y = RANDOM.nextInt(12);
                    x = changeX + x;
                    x++;
                    y++;
                }

                switch (monsterType) {

                    case 1:
                        if(P2_DOG_EATING_BUG < 3) {
                            this.pieceCollection[x][y] = new DogEatingBug(x, y, 21, 10, 2, 8, 5, 2);
                            P2_DOG_EATING_BUG++;
                            break;
                        }
                    case 2:
                        if(P2_DRUNKEN_KNIGHT < 3) {
                            this.pieceCollection[x][y] = new DrunkenKnight(x, y, 22, 5, 5, 5, 5, 5);
                            P2_DRUNKEN_KNIGHT++;
                            break;
                        }

                    case 3:
                        if(P2_MAGIC_CAT < 3) {
                            this.pieceCollection[x][y] = new MagicCat(x, y, 23, 8, 5, 10, 1, 5);
                            P2_MAGIC_CAT++;
                            break;
                        }

                    case 4:
                        if(P2_RECKLESS_CANIBAL < 3) {
                            this.pieceCollection[x][y] = new RecklessCanibal(x, y, 24, 4, 6, 8, 10, 6);
                            P2_RECKLESS_CANIBAL++;
                            break;
                        }

                    case 5:
                        if(P2_SAND_TURTLE < 3) {
                            this.pieceCollection[x][y] = new SandTurtle(x, y, 25, 5, 10, 1, 4, 10);
                            P2_SAND_TURTLE++;
                            break;
                        }
                    case 6:
                        if(P2_SOFISTICATED_SAM < 3) {
                            this.pieceCollection[x][y] = new SofisticatedSam(x, y, 26, 10, 5, 4, 1, 5);
                            P2_SOFISTICATED_SAM++;
                            break;
                        }

                }

            }
        }


    private void lost(Figure attacked) {
        if (attacked.getId() > 20) {
            P2_PIECE_COUNTER--;
            if(P2_PIECE_COUNTER == 0) {
                System.out.println("Player 2 lost");
                System.exit(1);
            }
        } else {
            P1_PIECE_COUNTER--;
            if(P1_PIECE_COUNTER == 0) {
                System.out.println("Player 1 lost");
                System.exit(1);
            }
        }
    }

    public boolean attack (int row, int col, Figure figure, Figure attacked) {
        this.pieceCollection[row][col] = this.attackPiece;

        int defence = attacked.getDP();

        int defencePoints = defence - figure.getAP() ;
        if(defencePoints <= 0) {
            this.pieceCollection[row][col] = null;
            if(playerTurn() == 1) {
                P1_MP += attacked.getMP();
            } else {
                P2_MP += attacked.getMP();
            }
            lost(attacked);
            System.out.println("Enemy figure died");
        } else {
            this.pieceCollection[row][col] = this.attackPiece;

            System.out.println("Enemy figure survived with defence points: " + defencePoints);
            attacked.setDp(defencePoints);
        }
        return false;
    }

    public void sideMenu(Figure figure) {
        if(l1 != null) {
            this.remove(l1);
        }
        String currentTurn;
        if(playerTurn() == 1) {
            currentTurn = "P2 with MP: " + P2_MP;
        } else {
            currentTurn = "P1 with MP: " + P1_MP;
        }
        l1=new JLabel(currentTurn);
        l1.setBounds(700,20, 100,30);


        int magic = RANDOM.nextInt(5);
        JButton button=new JButton(magic(magic));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (magic) {
                    case 0: if(checkPlayerMP(20)) System.out.println("Heal the world magic activated"); figure.setDp(figure.getMaxDP()); break;
                    case 1: if(checkPlayerMP(10)) System.out.println("Move magic activated"); break;
                    case 2: if(checkPlayerMP(10)) System.out.println("Defence up magic activated"); figure.setDp(figure.getMaxDP()); break;
                    case 3: if(checkPlayerMP(20)) System.out.println("Attack magic activated"); break;
                    case 4: if(checkPlayerMP(20)) System.out.println("War magic activated");figure.setAp(figure.getAP() * 2); warMagicUsed++;break;
                }
            }
        });

        button.setBounds(700,50,150,50);

        this.add(l1);this.add(button);
        this.setLayout(null);
        this.setVisible(true);
    }

    private String magic(int magic) {

        String magicSpell = "";
        switch (magic) {
            case 0: magicSpell = "Heal the world"; break;
            case 1: magicSpell = "Move"; break;
            case 2: magicSpell = "Defence up"; break;
            case 3: magicSpell = "Attack"; break;
            case 4: magicSpell = "War"; break;

        }
        return magicSpell;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());

        if(this.selectedPiece != null) {

            Figure figure = (Figure) this.selectedPiece;
            sideMenu(figure);
            if (this.hasBoardPiece(row, col)) {
                this.attackPiece = this.getBoardPiece(row, col);
            }
            Figure attacked = (Figure) this.attackPiece;

            if (isThatFigureYours() == 1) {
                if (figure.isMoveValid(row, col) == 1) {

                    int initialRow = figure.getRow();
                    int initialCol = figure.getCol();
                    if (isTileEmpty(row, col)) {


                        figure.move(row, col);

                        this.pieceCollection[initialRow][initialCol] = null;
                        this.pieceCollection[row][col] = this.selectedPiece;
                    } else{
                        if (figure.getId() < 20 && attacked.getId() > 20) {
                            attack(row, col, figure, attacked);
                        }
                        else if (figure.getId() > 20 && attacked.getId() < 20) {
                            attack(row, col, figure, attacked);
                        }
                        else {
                            System.out.println("You can't attack your own figure");
                            return;
                        }
                    }
                    newTurnPieceCreator();
                    if(warMagicUsed == 1) {
                        figure.setAp(figure.getAP() / 2);
                        warMagicUsed--;
                    }

                    TURN_COUNTER++;
                    this.attackPiece = null;
                    this.selectedPiece = null;
                    int magic = RANDOM.nextInt(5);
                    magic(magic);
                    sideMenu(figure);
                    repaint();
                    return;
                }

                }


            }

        if (this.hasBoardPiece(row, col)) {
            this.selectedPiece = this.getBoardPiece(row, col);
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private Object getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }
    private boolean hasBoardPiece(int row, int col) {
        return this.getBoardPiece(row, col) != null;
    }
    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / 50;
    }
    private boolean isTileEmpty(int row, int col) {
        return this.pieceCollection[row][col] == null;
    }
}
