/*Author: Vicky Chen
 * Date: 03/26/2014
 * This program simulates 100 generations in the Game of Life. x is dead, o is alive
 * */

import java.util.Scanner;

public class gameOfLife
{
	public static void main(String[]args) 
	{ 
		//create row and column variables
		
		Scanner numinput = new Scanner(System.in);
		
		System.out.print("Enter the number of rows: ");
		int row = numinput.nextInt();
		
		System.out.print("Enter the number of columns ");
		int column = numinput.nextInt();
		
		//draw the 2D array board
		// place the declaration of your board array here
		char[][] theBoard = new char [row][column];
		
		//declare a temp array
		char[][] temp = new char [row][column];
		
		//call the clear board method, pre-populate all elmts with empty
		clearBoard(theBoard);
		
		
		//get the random number of elmt placements
		int boardChance = (int)( (.6) *(row*column) );
		
		//call the clear board method, pre-populate all elmts with empty
		clearBoard(theBoard);
		
		//call the randomizeBoard method
		randomizeBoard(theBoard, boardChance);
		
		//call the printBoard method
		printBoard(theBoard);
		
		//set up for 100 generations
		for(int generation = 1; generation <= 100 ; generation++)
		{
			System.out.println();
			System.out.println("Generation: " + generation);
			
						
			//call the printBoard method
			//printBoard(theBoard);
			
			//call the runGame method to handle the game logic
			//store the calculations in a temp array
			runGame(theBoard, temp);
			
			//call the printBoard method, after updating array with temp array calcs
			printBoard(theBoard);
			
		}
	}

	//define the clearBoard method
	public static void clearBoard(char[][] board)
	{
		//iterate through all elmts and put in empty elmt
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				//put in empty char space
				//MAKE SURE TO USE SINGLE TICKS FOR CHAR,
				//DOUBLE TICKS IS FOR STRINGS
				board[row][col] = 'x';
			}
		}
	}
	
	//define the randomizeBoard method
	public static void randomizeBoard(char[][] board, double chance)
	{
		//set up counter variable
		int counter = 0;
		
		//iterate through all elmts and put in number of random 'o' determined by chance
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				//for some reason if statement needs to go first otherwise counter will keep going past chance value
				if (counter == chance)
				{
					break;
				}
				
				board[row][col] = 'o';
				counter++;
				
				//System.out.println("chance" +chance);
				//System.out.println("counter" + counter);
				
				
			}			
		}
		
			
		//now have right number of random 'o' but need to shuffle them up to be random
		//SHUFFLE THEM UP!
		//1) visit every elmt
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				//2)pick a random row and col location
				int rowRandom = (int) (Math.random()*(board.length-1) + 0  );
				int colRandom = (int) (Math.random()*(board[row].length-1) + 0);
				
				//3) store current elmt into temp var
				char tempChar = board[row][col];
				
				//4) place contents at random location in current location
				board[row][col] = board[rowRandom][colRandom];
				
				//5) place the contents of the temp var into random location
				board[rowRandom][colRandom] = tempChar;
			}
		}
	
		
}
	
	//define the printBoard method
	public static void printBoard(char[][] board)
	
	{
		//set up counter variable
		int counter = 0;
		
		//iterate through all elmts
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				//print out the elmt
				System.out.print(board[row][col]);
				counter++;
				
				//if reach end of row, line break
				if (counter == board.length)
				{
					System.out.println();
					counter = 0;
				}
			}
			
		}
	}
	
	//define the runGame method
	public static void runGame(char[][] board, char[][] temp)
	{
		
		
		//iterate through all elmts
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				
				//set up accumulator variable for LIVE neighbors
				int neighbor = 0;
				
				//set up direction vars
				int right = col+1;
				int left = col-1;
				int up = row-1;
				int down = row+1;
				
				/* now check how many live neighbors the cell is touching.
				 * have to check the cell to the left, right, top, bottom, top-left,
				 * top-right, bottom-left, bottom-right of cell.
				 */
				// RIGHT of the cell, add 1 to the column
				//But first, we have to make sure that that cell is inside the array
				// so we don't get an error saying that the index is out of range.
				
				//test the first row
				if (row==0)
				{
					//test if it is top left corner
					if (col==0)
					{
						if ( (right < board.length-1) && (down <board[row].length-1)  )
						{
							//only test right, below and bottm right
							//right
							if (board[row][right] == 'o')
							{
								neighbor++;
							}
							
							//below
							if (board[down][col] == 'o')
							{
								neighbor++;
							}
							
							//bottom right
							if (board[down][right] == 'o')
							{
								neighbor++;
							}
						}
					}
				
					//test if it is top right corner
					else if (col==board.length-1)
					{
						
						if ( (left > 0) && (down <board[row].length-1)  )
						{
							//test left, down, bottom left
							if (board[row][left] == 'o')
							{
								neighbor++;
							}
							
							//below
							if (board[down][col] == 'o')
							{
								neighbor++;
							}
							
							//bottom left
							if (board[down][left] == 'o')
							{
								neighbor++;
							}
							
						}
					}
					
					else 
					{
						if ( (left > 0) && (right < board.length-1) && (down <board[row].length-1)  )
						{
							//test left, right, below, bottom right, bottom left
							//right 
							if (board[row][right] == 'o')
							{
								neighbor++;
							}
							
							//below
							if (board[down][col] == 'o')
							{
								neighbor++;
							}
							
							//bottom right
							if (board[down][right] == 'o')
							{
								neighbor++;
							}	
							
							//left
							if (board[row][left] == 'o')
							{
								neighbor++;
							}
													
							//bottom left
							if (board[down][left] == 'o')
							{
								neighbor++;
							}
						}
					}
				}
				
				else if (row == board.length-1)
				{
					if ( (right < board.length-1) && (up >0)  )
					{
						//test to see if it is bottom left corner
						if (col==0)
						{
							//only test top, right, top right
							if (board[up][col]=='o')
							{
								neighbor++;
							}
							
							//right
							//right 
							if (board[row][right] == 'o')
							{
								neighbor++;
							}
							
							//top right
							if (board [up][right] == 'o')
							{
								neighbor++;
							}	
					}
				}
					
					//test if in bottom right corner
					else if (col== board[row].length-1)
					{
						if ( (left > 0) && (up >0)  )
						{
						
						//only test left, top, top left
			
						//left
						if (board[row][left] == 'o')
						{
							neighbor++;
						}
						
						//top
						if (board[up][col]=='o')
						{
							neighbor++;
						}
						
						//top left
						if (board[up][left] == 'o')
						{
							neighbor++;
						}
						
						}
					}
						
						
						
					else
					{	
						if ( (left > 0) && (right < board.length-1) && (up >0)  )
						{
						
						//it is in the middle
						//only test top, top left, top right, left, right
						
						//top
						if (board[up][col]=='o')
						{
							neighbor++;
						}
						
						//top left
						if (board[up][left] == 'o')
						{
							neighbor++;
						}
						
						//left
						if (board[row][left] == 'o')
						{
							neighbor++;
						}
						
						
						//right 
						if (board[row][right] == 'o')
						{
							neighbor++;
						}
						
						//top right
						if (board [up][right] == 'o')
						{
							neighbor++;
						}
						
					}
					}
				}
				
				//check the cols on the edges, already tested the 4 corners
				else if ( (col == 0) && (row > 0) && (row < board.length-1 )  )
				{
					if ( (right < board.length-1) && (up >0) && (down <board[row].length-1)  )
					{
					
					
					//only check top, bottom, right, top right, bottom right
					
					//right 
					if (board[row][right] == 'o')
					{
						neighbor++;
					}
					
					//top right
					if (board [up][right] == 'o')
					{
						neighbor++;
					}
				
					//below
					if (board[down][col] == 'o')
					{
						neighbor++;
					}
					
					//bottom right
					if (board[down][right] == 'o')
					{
						neighbor++;
					}	
					
					//top
					if (board[up][col]=='o')
					{
						neighbor++;
					}
					
					}

				}
				
				//test if on the right edge, not corners
				else if ( (col == board[row].length -1) && (row > 0) && (row < board.length-1 ) )
				{
					//only check left, top, bottom, top left, bottom left
					
					if ( (left > 0) && (up >0) && (down <board[row].length-1)  )
					{
					
					//below
					if (board[down][col] == 'o')
					{
						neighbor++;
					}
					
					
					//top
					if (board[up][col]=='o')
					{
						neighbor++;
					}
					
					//top left
					if (board[up][left] == 'o')
					{
						neighbor++;
					}
					
					//left
					if (board[row][left] == 'o')
					{
						neighbor++;
					}
					
					//bottom left
					if (board[down][left] == 'o')
					{
						neighbor++;
					}
					
					}
					
				}

				else //everything else in the middle
					
				{
					if ( (left > 0) && (right < board.length-1) && (up >0) && (down <board[row].length-1)  )
					{
						//test below, top, top left, left , top right, bottom left, bottom right, right
						//below
						if (board[down][col] == 'o')
						{
							neighbor++;
						}
						
						
						//top
						if (board[up][col]=='o')
						{
							neighbor++;
						}
						
						//top left
						if (board[up][left] == 'o')
						{
							neighbor++;
						}
						
						//left
						if (board[row][left] == 'o')
						{
							neighbor++;
						}
						
						//bottom left
						if (board[down][left] == 'o')
						{
							neighbor++;
						}
						
						//right 
						if (board[row][right] == 'o')
						{
							neighbor++;
						}
						
						//top right
						if (board [up][right] == 'o')
						{
							neighbor++;
						}
						
						//bottom right
						if (board[down][right] == 'o')
						{
							neighbor++;
						}
					}
				}
						
				//if the cell is alive
				if ( board [row][col] == 'o')
				{
					//if live cell has less than 2 live neighbors
					if (neighbor <2)
					{
						//it will be dead in next generation
						//store this in the temp array in same location
						temp [row][col] = board[row][col];
					}
					
					//if cell has 2 or 3 live neighbors, it will stay alive
					else if (neighbor == 2 || neighbor == 3)
					{
						//it will stay alive in next generation
						//store this in the temp array in the same location
						temp [row][col] = board[row][col];
					}
					
					//if cell has more than 4 live neightbors, it will be dead
					else if (neighbor > 3)
					{
						//it will be dead in next generation
						//store this in the temp array in same location
						temp [row][col] = board[row][col];
					}					
					
				}
				
				//if it's reach here, it's def not alive, so it must dead
				//if it is dead and is next to exactly 3 live neighbors, it will be alive
				else if (neighbor == 3) 
				{
					//it will be alive in next gen, store this in temp array
					temp[row][col] = 'o';
				}
				
				//otherwise if not next to 3, and it is dead, it will stay dead
				else if (neighbor != 3) 
				{
					//it will be dead in next gen, store this in temp array
					temp[row][col] = 'x';
				}
				
			}
			
			//switch temp array calcs into the board array
			for (int boardRow = 0; boardRow < board.length; boardRow++)
			{
				for (int boardCol = 0; boardCol < board[row].length; boardCol++)
				{ 
					board[boardRow][boardCol] = temp[boardRow][boardCol];
				}
			}
		}
	}
}
