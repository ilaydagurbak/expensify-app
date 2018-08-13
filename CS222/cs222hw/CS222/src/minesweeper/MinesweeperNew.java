package minesweeper;

/***
 * OzU CS 222 Note:
 * This code is taken from
 * http://www.planet-source-code.com/vb/scripts/ShowCode.asp?txtCodeId=4853&lngWId=2
 *
 ***/

// The "MinesweeperNew" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperNew extends Applet implements MouseListener
{
    Button buttonArray[] [];
    boolean isBomb[] [];
    private boolean isFlag[] [];
    boolean isExposed[] [];   //used for exposing 0's. If true then a 0 has been exposed
    boolean isNumOrBomb[] [];
    int bombsRemaining;
    String surbombs;        //number of bombs surrounding button [x][y] (is a string so that we can setLabel for the button)
    int randx, randy;       //random ints for bombs
    int row = 16, col = 30, numBombs = 99, Setup = 3; //number of rows columns, and bombs
    int sizex = 780, sizey = 492;
    private Font font;
    private Panel gridPanel, borderPanel, gamePanel, panel;
    Label bombs, timeRemaning, newGameLabel;
    Button restartE, restartM, restartH;
    GridLayout gridLayout;
    public void init ()
    {
        setLayout (new BorderLayout ());
        gridLayout = new GridLayout (row, col);
        gridPanel = new Panel (gridLayout);
        font = new Font ("ComicSans", font.BOLD, 17);
        setFont (font);
        borderPanel = new Panel (new BorderLayout ());
        panel = new Panel ();
        timeRemaning = new Label ("");
        newGameLabel = new Label ("New Game");
        restartE = new Button ("Easy");
        restartE.addMouseListener (this);
        restartM = new Button ("Medium");
        restartM.addMouseListener (this);
        restartH = new Button ("Hard");
        restartH.addMouseListener (this);
        gamePanel = new Panel ();
        bombsRemaining = numBombs;
        bombs = new Label (Integer.toString (bombsRemaining));
        buttonArray = new Button [row] [col];
        isBomb = new boolean [row] [col];
        isFlag = new boolean [row] [col];
        isExposed = new boolean [row] [col];
        isNumOrBomb = new boolean [row] [col];
        for (int x = 0 ; x < row ; x++)
        //these for loops set up the buttons and sets all the boolean arrays to = false
        {
            for (int y = 0 ; y < col ; y++)
            {
                buttonArray [x] [y] = new Button ();
                buttonArray [x] [y].addMouseListener (this);
                gridPanel.add (buttonArray [x] [y]);
            }
        }
        add (borderPanel, "North");
        add (gridPanel, "Center");
        borderPanel.add (bombs, "West");
        borderPanel.add (panel, "North");
        panel.add (newGameLabel);
        borderPanel.add (gamePanel, "Center");
        gamePanel.add (restartE);
        gamePanel.add (restartM);
        gamePanel.add (restartH);
        borderPanel.setBackground(Color.lightGray);
        newGameLabel.setBackground(Color.lightGray);
        newGameLabel.setForeground(Color.black);
        bombs.setBackground(Color.lightGray);
        bombs.setForeground(Color.white);
        Restart_Game (row, col, numBombs, row, col, sizex, sizey);
    }


    public void Restart_Game (int row, int col, int numbombs, int prow, int pcol, int sizex, int sizey)
    {
        //mBar.Restart (table, row, col);
        //mBar.SetupMenu ();
        setSize (sizex, sizey);
        invalidate();
        validate();
        gridLayout.setRows (row);
        gridLayout.setColumns (col);
        int count = 0;
        bombsRemaining = numbombs;
        bombs.setText (Integer.toString (bombsRemaining));
        for (int x = 0 ; x < prow ; x++)
        {
            for (int y = 0 ; y < pcol ; y++)
            {
                gridPanel.remove (buttonArray [x] [y]);
            }
        }
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {

                buttonArray [x] [y].setEnabled (true);
                buttonArray [x] [y].setLabel ("");
                buttonArray [x] [y].setBackground (Color.gray);
                buttonArray [x] [y].setForeground (Color.white);
                gridPanel.add (buttonArray [x] [y]);
                isBomb[x] [y] = false;
                isFlag [x] [y] = false;
                isExposed [x] [y] = false;
                isNumOrBomb [x] [y] = false;
            }
        }
        setSize (sizex, sizey);
        invalidate();
        validate();
        //adds the bombs to random places on the grid
        while (count < numbombs)
        {
            randx = (int) (Math.random () * (row));
            randy = (int) (Math.random () * (col));
            if (isBomb[randx] [randy] == false)
            {
                isBomb[randx] [randy] = true;
                isNumOrBomb [randx] [randy] = true;
                count++;
            }
        }
    }


    public void mouseClicked (MouseEvent e)
    {
        int prow = 0, pcol = 0;
        if (e.getSource () == restartE)
        {
            row = 10;
            col = 10;
            numBombs = 10;
            sizex = 300;
            sizey = 346;
            if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 1;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 1;
            }
        }
        if (e.getSource () == restartM)
        {
            row = 16;
            col = 16;
            numBombs = 40;
            sizex = 496;
            sizey = 540;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 2;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 2;
            }
        }
        if (e.getSource () == restartH)
        {
            row = 16;
            col = 30;
            numBombs = 99;
            sizex = 780;
            sizey = 492;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 3;
            }
            else if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 3;
            }
        }
        if (e.getSource () == restartE || e.getSource () == restartM || e.getSource () == restartH)
            Restart_Game (row, col, numBombs, prow, pcol, sizex, sizey);
        boolean gameover = false;
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (e.getSource () == buttonArray [x] [y])
                {
                    if (e.getButton () == e.BUTTON1 && isFlag [x] [y] == false) //if left click, and there is no flag on the button
                    {
                        if (isBomb[x] [y] == true)  // if you you click on a bomb, results in game over
                        {
                            buttonArray [x] [y].setLabel ("*");
                            gameover ();
                            buttonArray [x] [y].setBackground (Color.black);
                            gameover = true;
                            break;

                        }
                        isExposed [x] [y] = true;
                        isNumOrBomb [x] [y] = true; // these set to true mean that the button has been clicked
                        surbombs = Integer.toString (surroundingBombs (x, y)); //gets the number of surrounding buttons with bombs and sets it to a string so that it is possible to setLabel
                        buttonArray [x] [y].setLabel (surbombs); // sets the label to be the number of bombs in the 8 surrounding buttons
                        if (surroundingBombs (x, y) == 0)
                        {
                            check (x, y);          //calls a recursive method so that if a "0" is there the surrounding 8 buttins must be exposed and if one of those is "0" it too must be exposed and so on
                        }
                    }
                    else if (e.getButton () == e.BUTTON3)  // if it is a right click
                    {
                        if (isFlag [x] [y] == true) //if there is a flag already present set it so that there is no flag
                        {
                            buttonArray [x] [y].setLabel ("");
                            buttonArray [x] [y].setForeground (Color.white);
                            isFlag [x] [y] = false;
                            isNumOrBomb [x] [y] = false;
                            bombsRemaining++;
                        }
                        else if (isNumOrBomb [x] [y] == false || isBomb[x] [y] == true) //if there is no flag, set it so there is a flag
                        {
                            buttonArray [x] [y].setLabel ("|>");
                            buttonArray [x] [y].setForeground (Color.red);
                            isFlag [x] [y] = true;
                            isNumOrBomb [x] [y] = true;
                            bombsRemaining--;
                        }
                        bombs.setText (Integer.toString (bombsRemaining));

                    }
                    else if (e.getButton () == e.BUTTON2 && isFlag [x] [y] == false && isNumOrBomb [x] [y] == true && isBomb[x] [y] == false) //if both left and right click at the same time
                    { //only executes if there is no flag, it has been exposed, and no bomb
                        if (flags (x, y) == surroundingBombs (x, y)) //checks that the number of flags around [x][y] = the number of bombs around [x][y]
                        {
                            for (int q = x - 1 ; q <= x + 1 ; q++)
                            {
                                for (int w = y - 1 ; w <= y + 1 ; w++)
                                {
                                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                                        break;
                                    if (isBomb[q] [w] == false && isFlag [q] [w] == true) //if there is no bomb, but there is a flag its game over
                                    {
                                        gameover ();
                                        gameover = true;
                                        break;
                                    }
                                }
                            }
                            if (gameover == false)
                            {
                                expose (x, y);     //eposes the surrounding 8 buttons
                                check (x, y);      //checks if any of those are "0" and calls the recursive method
                            }
                        }
                    }
                    if (gameover == false) //this just calls the method for changing the colours of the buttons if they have been clicked
                        clicked ();
                }

            }
        }
        checkwin ();
    }


    public void clicked ()     //changes the color of the buttons and if [x][y] is "0" set the label to nothing
    {
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (isNumOrBomb [x] [y] == true && isFlag [x] [y] == false && isBomb[x] [y] == false)
                    buttonArray [x] [y].setBackground (Color.darkGray);
                if (isFlag [x] [y] == false && surroundingBombs (x, y) == 0)
                    buttonArray [x] [y].setLabel ("");
            }
        }
    }


    public int flags (int x, int y)  // get the number of surrounding flags
    {
        int surFlags = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;

                    if (isFlag [q] [w] == true)
                    {
                        surFlags++;
                    }
                    break;
                }
            }
        }
        return surFlags;
    }


    public int surroundingBombs (int x, int y)  // checks surrounding 8 squares for number of bombs (it does include itself, but has already been checked for a bomb so it won't matter)
    {
        int surBombs = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (isBomb[q] [w] == true)
                        surBombs++;
                    break;
                }
            }
        }
        return surBombs;
    }


    public void expose (int x, int y)  // exposes the surrounding 8 buttons
    {
        String surrbombs;
        isExposed [x] [y] = true;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (isFlag [q] [w] == true)
                        break;

                    isNumOrBomb [q] [w] = true;
                    surrbombs = Integer.toString (surroundingBombs (q, w));
                    buttonArray [q] [w].setLabel (surrbombs);
                    break;

                }
            }
        }
    }


    public void surr (int x, int y)  //this is what checks if a surrounding button has "0" is so expose it and check around the exposed buttons until there is no more "0"'s
    {

        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (isFlag [q] [w] == true)
                        break;
                    if (isExposed [q] [w] == false && surroundingBombs (q, w) == 0)
                    {
                        expose (q, w);
                        check (q, w);
                    }
                    break;
                }
            }
        }
    }


    public void check (int x, int y)  //calls the surr method but is neccesary because of the expose first
    {
        expose (x, y);
        surr (x, y);
    }


    public void checkwin ()    //checks if all the button without bombs have been pressed
    {
        boolean allexposed = true;
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (isFlag [x] [y] == true && isBomb[x] [y] == false)
                    allexposed = false;
                if (isNumOrBomb [x] [y] == false)
                {
                    allexposed = false;
                    break;
                }
            }
        }
        if (allexposed != false)
        {
            gameover ();
            int x2 = (int) col / 2;
            int y2 = (int) row / 2;
            buttonArray [y2] [x2 - 4].setLabel ("Y");
            buttonArray [y2] [x2 - 3].setLabel ("O");
            buttonArray [y2] [x2 - 2].setLabel ("U");
            buttonArray [y2] [x2 - 1].setLabel ("");
            buttonArray [y2] [x2].setLabel ("W");
            buttonArray [y2] [x2 + 1].setLabel ("I");
            buttonArray [y2] [x2 + 2].setLabel ("N");
            buttonArray [y2] [x2 + 3].setLabel ("!");
            buttonArray [y2] [x2 + 4].setLabel ("!");
            for (int i = -4 ; i < 5 ; i++)
            {
                buttonArray [y2] [x2 + i].setBackground (Color.black);
                buttonArray [y2] [x2 + i].setForeground (Color.white);
            }
        }
    }


    public void gameover ()  // is called if bomb is clicked or on the double click if flag is not on a bomb
    {
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (isBomb[x] [y] == true)
                {
                    buttonArray [x] [y].setLabel ("*"); //exposes all bombs
                    buttonArray [x] [y].setBackground (Color.red);
                }
                buttonArray [x] [y].setEnabled (false); //disable all buttons
            }
        }
        int x2 = (int) col / 2;
        int y2 = (int) row / 2;
        buttonArray [y2] [x2 - 4].setLabel ("Y");
        buttonArray [y2] [x2 - 3].setLabel ("O");
        buttonArray [y2] [x2 - 2].setLabel ("U");
        buttonArray [y2] [x2 - 1].setLabel ("");
        buttonArray [y2] [x2].setLabel ("L");
        buttonArray [y2] [x2 + 1].setLabel ("O");
        buttonArray [y2] [x2 + 2].setLabel ("S");
        buttonArray [y2] [x2 + 3].setLabel ("E");
        buttonArray [y2] [x2 + 4].setLabel ("!");
        for (int i = -4 ; i < 5 ; i++)
        {
            buttonArray [y2] [x2 + i].setBackground (Color.black);
            buttonArray [y2] [x2 + i].setForeground (Color.white);
        }
    }


    /////////////////////////////////////////////////////////////////
    //These are not used but are necessary for mouseListener
    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }
}


