package p4_package;

public class SudokuGeneratorClass 
{
	private class CellNode 
	{
		boolean fixedCell;
		int value;
		
		public CellNode()
		{
			this.fixedCell = false;
			this.value = 0;
		}
		
		public CellNode(CellNode copied)
		{
			this.fixedCell = copied.fixedCell;
			this.value = copied.value;
		}
	}
	
	private final int GRID_SIDE = 9;
	private final int SUB_GRID_SIDE = 3;
	private final int SUDOKU_RANGE = 9;
	private CellNode[][] sudokuArray;
	
	public SudokuGeneratorClass() 
	{
		int outIndex, inIndex;
		sudokuArray = new CellNode[GRID_SIDE][GRID_SIDE];
		
		for (outIndex = 0; outIndex < GRID_SIDE; outIndex++)
		{
			for (inIndex = 0; inIndex < GRID_SIDE; inIndex++)
			{
				sudokuArray[outIndex][inIndex] = new CellNode();
			}
		}
	}
	
	public SudokuGeneratorClass(SudokuGeneratorClass copied) 
	{
		int outIndex, inIndex;
		
		for (outIndex = 0; outIndex < GRID_SIDE; outIndex++)
		{
			for (inIndex = 0; inIndex < GRID_SIDE; inIndex++)
			{
				this.sudokuArray[outIndex][inIndex] = new CellNode(copied.sudokuArray[outIndex][inIndex]);
			}
		}
	}
	
	public void createSudoku(int numEmpties, boolean showGrid)
	{
		setDiagonalSubGrids();
		System.out.println("diagonals set");
		
		createSudokuHelper(0, 0, showGrid);
		
		removeNumbers(numEmpties);
		
		System.out.println("SolutionFound:");
		displayGrid();
	}
	
	private boolean createSudokuHelper(int rowPos, int colPos, boolean showGrid)
	{
		int index;

		if (colPos == GRID_SIDE)
		{
			rowPos++;
			colPos = 0;
			
			if (showGrid)
			{
				displayGrid();
			}
		}
		
		if (rowPos == GRID_SIDE)
		{
			return true;
		}
		
		if (sudokuArray[rowPos][colPos].fixedCell)
		{
			if (createSudokuHelper(rowPos, colPos + 1, showGrid))
			{
				return true;
			}
		}
		
		for (index = 1; index < SUDOKU_RANGE + 1; index++)
		{
			//System.out.format("testing num %d at row %d and col %d %b \n", index, rowPos, colPos, hasConflict(rowPos, colPos, index));
			
			if (!hasConflict(rowPos, colPos, index))
			{
				System.out.format("Trying %d at row: %d and column: %d \n", index, rowPos, colPos);
				sudokuArray[rowPos][colPos].value = index;
					
				if (createSudokuHelper(rowPos, colPos + 1, showGrid))
				{
					return true;
				}
				
				sudokuArray[rowPos][colPos].value = 0;
			}
		}
		
		return false;
	}
	
	
	public void displayGrid()
	{
		int rowIndex, colIndex;

		System.out.println();
		
		for (rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++)
		{
			if (rowIndex % SUB_GRID_SIDE == 0)
			{
				System.out.format(" #===|===|===#===|===|===#===|===|===# \n");
			}
			else
			{
				System.out.format(" #---|---|---#---|---|---#---|---|---# \n");
			}
			
			for (colIndex = 0; colIndex < GRID_SIDE; colIndex++)
			{
				if (colIndex % SUB_GRID_SIDE == 0)
				{
					System.out.print(" # ");
				}
				else 
				{
					System.out.print(" | ");
				}
				
				if (sudokuArray[rowIndex][colIndex].value == 0)
				{
					System.out.print(' ');
				}
				else
				{
					System.out.print(sudokuArray[rowIndex][colIndex].value);
				}
			}
			System.out.print(" # ");
			
			System.out.println();
		}
		System.out.println(" #===|===|===#===|===|===#===|===|===# \n");
	}
	
	private int genRandSudokuValue()
	{
		int index;
		
		int randValue = (int)((Math.random() * (SUDOKU_RANGE)) + 1);
		
		// Javadocs said to do this, curious why a for loop is needed
		// Must be a pseudo-random thing
		for (index = 0; index < randValue; index++)
		{
			randValue = (int)((Math.random() * (SUDOKU_RANGE)) + 1);
		}
		
		return randValue;
	}
	
	private boolean hasConflict(int rowLocIndex, int colLocIndex, int value)
	{
		if (isInCol(colLocIndex, value) || isInRow(rowLocIndex, value) || isInSubGrid(rowLocIndex, colLocIndex, value))
		{
			return true;
		}
		
		return false;
	}
	
	private boolean isInCol(int colIndex, int value)
	{
		int index;
		
		for (index = 0; index < GRID_SIDE; index++)
		{
			if (value == sudokuArray[index][colIndex].value)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isInRow(int rowIndex, int value)
	{
		int index;
		
		for (index = 0; index < GRID_SIDE; index++)
		{
			if (value == sudokuArray[rowIndex][index].value)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isInSubGrid(int rowLocIndex, int colLocIndex, int value)
	{
		int rowStartIndex = (rowLocIndex / SUB_GRID_SIDE) * SUB_GRID_SIDE;
		int colStartIndex = (colLocIndex / SUB_GRID_SIDE) * SUB_GRID_SIDE;
		int rowIndex, colIndex;
		
		for (rowIndex = rowStartIndex; rowIndex < rowStartIndex + SUB_GRID_SIDE; rowIndex++)
		{
			for (colIndex = colStartIndex; colIndex < colStartIndex + SUB_GRID_SIDE; colIndex++)
			{
				if (value == sudokuArray[rowIndex][colIndex].value)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void removeNumbers(int numbersToBeRemoved)
	{
		int index, rowIndex, colIndex;
		
		for (index = 0; index < numbersToBeRemoved; index++)
		{
			do
			{
				rowIndex = (int)(Math.random() * 9);
				colIndex = (int)(Math.random() * 9);
			}
			while (sudokuArray[rowIndex][colIndex].value == 0);

			sudokuArray[rowIndex][colIndex].value = 0;
			
		}
	}
	
	private void setDiagonalSubGrids()
	{
		int index;
		int startLoc;
		
		for (index = 0; index < (int)(GRID_SIDE / SUB_GRID_SIDE); index++)
		{
			startLoc = index * SUB_GRID_SIDE;
			setInitialSubGrid(startLoc, startLoc);
		}
	}
	
	private void setInitialSubGrid(int startRow, int startCol)
	{
		int randValue, index, rowPos, colPos;
		
		for (index = 0; index < SUB_GRID_SIDE * SUB_GRID_SIDE; index++)
		{
			rowPos = startRow + (index / SUB_GRID_SIDE);
			colPos = startCol + (index % SUB_GRID_SIDE);
			
			do
			{
				randValue = genRandSudokuValue();
			}
			while (isInSubGrid(rowPos, colPos, randValue));
			
			sudokuArray[rowPos][colPos].value = randValue;
			sudokuArray[rowPos][colPos].fixedCell = true;
		}
	}
}
