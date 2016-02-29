
import java.util.*;

import java.io.*;

public class SudokuSolver

{

   

   

    private final int PUZZLE_WIDTH;

     
    private final int SUB_WIDTH, SUB_HEIGHT;   

  

    public int[][] board;   

       

    public SudokuSolver(int puzzleWidth, int subWidth, int subHeight) throws IllegalDimensionException

    {

        if(puzzleWidth <= 0 || subWidth <= 0 || subHeight <= 0 || subWidth * subHeight != puzzleWidth)

            throw new IllegalDimensionException();

       

        PUZZLE_WIDTH = puzzleWidth;

        SUB_WIDTH = subWidth;

        SUB_HEIGHT = subHeight;

        board = new int[9][9];

    }

   

   

    public void loadBoard()

    {           

     int[][] newBoard={

                            {0,0,0,0,0,0,0,0,0},

                            {0,0,0,0,0,0,0,0,0},

                            {0,0,0,0,0,0,0,0,0},

                            {0,0,0,0,0,0,0,0,0},

                            {8,0,0,0,0,0,0,0,0},

                            {0,0,0,0,2,0,0,0,0},

                            {0,0,0,0,0,0,0,0,0},

                            {0,0,0,0,0,0,0,0,0},

                            {0,0,0,0,0,0,0,0,0}

                            };

                            board=newBoard;                      

                          

    }

   

   
    private boolean canPlace(int r, int c, int x)

    {

        if(r < 0 || c < 0 || r >= PUZZLE_WIDTH || c >= PUZZLE_WIDTH || x <= 0 || x > PUZZLE_WIDTH)

            throw new IllegalArgumentException();

       

        return canPlaceInRow(r, x) && canPlaceInCol(c, x) && canPlaceInSubBox(r, c, x);

    }

   

   
    private boolean canPlaceInRow(int r, int x)

    {

        if(r < 0 || r >= PUZZLE_WIDTH || x <= 0 || x > PUZZLE_WIDTH)

            throw new IllegalArgumentException();

       

        for(int value : board[r])

            if(value == x)

                return false;

        return true;

    }

   

   
    private boolean canPlaceInCol(int c, int x)

    {

        if(c < 0 || c >= PUZZLE_WIDTH || x <= 0 || x > PUZZLE_WIDTH)

            throw new IllegalArgumentException();

       

        for(int r = 0; r != board.length; ++r)

            if(board[r][c] == x)

                return false;

        return true;

    }

   


    private boolean canPlaceInSubBox(int r, int c, int x)

    {

        if(r < 0 || c < 0 || r >= PUZZLE_WIDTH || c >= PUZZLE_WIDTH || x <= 0 || x > PUZZLE_WIDTH)

            throw new IllegalArgumentException();

       

               

        int leftCol = (c / SUB_WIDTH) * SUB_WIDTH;

        int rightCol = leftCol + SUB_WIDTH - 1;

       

        int topRow = (r / SUB_HEIGHT) * SUB_HEIGHT;

        int bottomRow = topRow + SUB_HEIGHT - 1;

       


        for(int searchR = topRow; searchR <= bottomRow; ++searchR)

            for(int searchC = leftCol; searchC <= rightCol; ++searchC)

                if(board[searchR][searchC] == x)

                    return false;

       

        return true;

    }

   

    

    public void printBoard()

    {

        for(int i = 0; i <board.length; ++i)

        {

            System.out.print(" --");

            if((i + 1) % SUB_WIDTH == 0)

                System.out.print(" ");

        }

        System.out.println();

       

        for(int row = 0; row < PUZZLE_WIDTH; ++row)

        {

            for(int col = 0; col < PUZZLE_WIDTH; ++col)

            {

                int value = board[row][col];

                String out = "|" + (col % SUB_WIDTH == 0 && col != 0 ? "|" : "");

                if(value == 0)

                    out += " ";

                else {

                    out += value + (value < 10 ? " " : "");

                }

                System.out.print(out);

            }

            System.out.println("|");

           

            if((row + 1) % SUB_HEIGHT == 0)

            {

                for(int i = 0; i != board.length; ++i)

                {

                    System.out.print(" --");

                    if((i + 1) % SUB_WIDTH == 0)

                        System.out.print(" ");

                }

                System.out.println();

            }

        }

       

        System.out.println();

    }

   



    private int getNextRow(int r, int c)

    {

        if(r < 0 || c < 0 || r >= PUZZLE_WIDTH || c >= PUZZLE_WIDTH)

            throw new IllegalArgumentException();

        return (c == PUZZLE_WIDTH - 1 ? r + 1 : r);

    }

   

    private int getNextCol(int r, int c) {

        if(r < 0 || c < 0 || r >= PUZZLE_WIDTH || c >= PUZZLE_WIDTH)

            throw new IllegalArgumentException();

       

        return (c == PUZZLE_WIDTH - 1 ? 0 : c + 1);

    }

   


    private boolean isLastCell(int r, int c)

    {

        return r == PUZZLE_WIDTH - 1 && c == PUZZLE_WIDTH - 1;

    }

   



    private boolean solveCell(int r, int c)   

    {

        if(r < 0 || c < 0 || r >= PUZZLE_WIDTH || c >= PUZZLE_WIDTH)

            throw new IllegalArgumentException();

       

        for(int guess = 1; guess <= PUZZLE_WIDTH; ++guess)

        {

            

            if(canPlace(r, c, guess)) {

            

                board[r][c] = guess;

               

                if(isLastCell(r, c))

                    return true;

              

                if(solvePuzzle(getNextRow(r, c), getNextCol(r, c)))

                    return true;

              
            }

        }

       
        board[r][c] = 0;

        return false; 
    }

   



    public boolean solvePuzzle(int r, int c)

    {

        if(r < 0 || c < 0 || r >= PUZZLE_WIDTH || c >= PUZZLE_WIDTH)

            throw new IllegalArgumentException();

       

       
        if(board[r][c] != 0)

        {

            
				    if(isLastCell(r, c))

                return true; 
            
            if(solvePuzzle(getNextRow(r, c), getNextCol(r, c)))

                return true;             
					 
					 return false;

        }

       

        return solveCell(r, c);

    }

   

    public static void main(String[] args) {


       

       

            int puzzleWidth, subWidth, subHeight;

           

            

            puzzleWidth = 9;

            

            subWidth =3;

            

            subHeight =3;

            try

          {

          

            SudokuSolver solver = new SudokuSolver(puzzleWidth, subWidth, subHeight);

           


     }

     catch(IllegalDimensionException c)

     {

          System.out.println("Illegal Dimension");

     }

           }

} 

class IllegalDimensionException extends Exception {

       private static final long serialVersionUID = 1L;

     IllegalDimensionException()

    {

          super("illegal Dimension");

     }

}


class FileFormatException extends Exception {


   private static final long serialVersionUID = 1L;

     FileFormatException()

     {

          super("illegal File Format");

     }

   

}



class IllegalPuzzleException extends Exception {

    
   private static final long serialVersionUID = 1L;

     IllegalPuzzleException()

     {

          super("illegal Puzzle");

     }

   

   

}
