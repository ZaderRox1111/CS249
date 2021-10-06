package p6_package;

/**
 * Class for creating an iterator
 * 
 * @author Zachariah Derrick
 *
 */

public class IteratorClass {
	/**
	 * Default capacity for default constructor
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Char constant for left bracket
	 */
	private static final char LEFT_BRACKET = '[';
	
	/**
	 * Constant for not found
	 */
	private static final int NOT_FOUND = -1;
	
	/**
	 * Char constant for right bracket
	 */
	private static final char RIGHT_BRACKET = ']';
	
	/**
	 * Char constant for space
	 */
	private static final char SPACE = ' ';
	
	/**
	 * index of current index of iterator
	 */
	private int currentIndex;
	
	/**
	 * array holding items
	 */
	private int[] iteratorArray;
	
	/**
	 * iterator capacity
	 */
	private int iteratorCapacity;
	
	/**
	 * iterator size
	 */
	private int iteratorSize;
	
	/**
	 * Default constructor, initialize iterator to default capacity
	 */
	public IteratorClass() 
	{
		currentIndex = 0;
		iteratorArray = new int[DEFAULT_CAPACITY];
		iteratorCapacity = DEFAULT_CAPACITY;
		iteratorSize = 0;
	}
	
	/**
	 * Initialization constructor, creates iterator with given capacity
	 * @param capacity
	 */
	public IteratorClass(int capacity)
	{
		currentIndex = 0;
		iteratorArray = new int[capacity];
		iteratorCapacity = capacity;
		iteratorSize = 0;
	}
	
	/**
	 * Clears iterator of all valid values by setting array size and 
	 * current index to zero, values remain in array but are not accessible
	 */
	public void clear()
	{
		iteratorSize = 0;
		currentIndex = 0;
	}
	
	/**
	 * Displays the iterator and surrounds currentIndex with brackets
	 * 
	 * Displays "empty" if empty
	 */
	public void displayIterator()
	{
		int index;
		
		if (isEmpty())
		{
			System.out.print("Empty\n");
		}
		else
		{
			System.out.print("Iterator:");
			
			for (index = 0; index < iteratorSize; index++)
			{
				if (index == currentIndex)
				{
					System.out.format(" %c%d%c", LEFT_BRACKET, iteratorArray[index], RIGHT_BRACKET);
				}
				else
				{
					System.out.format(" %d", iteratorArray[index]);	
				}
			}
			
			System.out.println();
		}
	}
	
	/**
	 * returns value at current index
	 * @return integer value at current index
	 */
	public int getValueAtCurrent()
	{
		if (isEmpty())
		{
			return NOT_FOUND;
		}
		
		int currentValue = iteratorArray[currentIndex];
		
		return currentValue;
	}
	
	/**
	 * checks for next available item in the iterator
	 * @return boolean result of test
	 */
	public boolean hasNext()
	{
		return currentIndex < (iteratorSize - 1);
	}
	
	/**
	 * checks for previous item in the iterator
	 * @return boolean result of test
	 */
	public boolean hasPrev()
	{
		return currentIndex > 0;
	}
	
	/**
	 * inserts item into iterator at current index, checks for resize
	 * @param newVal
	 */
	public void insertAtCurrent(int newVal)
	{
		int index;
		
		for (index = (iteratorSize); index > currentIndex; index--)
		{
			iteratorArray[index] = iteratorArray[index - 1];
		}
		
		iteratorArray[currentIndex] = newVal;
		iteratorSize++;
		
		if (iteratorSize >= iteratorCapacity)
		{
			resize();
		}
	}
	
	/**
	 * inserts item at end of iterator, check for resize
	 * @param newVal
	 */
	public void insertAtEnd(int newVal)
	{
		iteratorArray[iteratorSize] = newVal;
		iteratorSize++;
		
		if (iteratorSize >= iteratorCapacity)
		{
			resize();
		}
	}
	
	/**
	 * inserts item at beginning of iterator, checks for resize
	 * @param newVal
	 */
	public void insertAtFront(int newVal)
	{
		int index;
		
		for (index = (iteratorSize); index > 0; index--)
		{
			iteratorArray[index] = iteratorArray[index - 1];
		}
		
		iteratorArray[0] = newVal;
		iteratorSize++;
		
		if (iteratorSize >= iteratorCapacity)
		{
			resize();
		}
	}
	
	/**
	 * moves current index to the right
	 * @return boolean result of action
	 */
	public boolean moveNext()
	{
		if (currentIndex >= (iteratorSize - 1))
		{
			return false;
		}
		
		currentIndex++;
		return true;
	}

	/**
	 * moves current index to the left
	 * @return boolean result of action
	 */
	public boolean movePrev()
	{
		if ((currentIndex) <= 0)
		{
			return false;
		}
		
		currentIndex--;
		return true;
	}
	
	/**
	 * Checks if size is equal to zero
	 * @return boolean result of test
	 */
	public boolean isEmpty()
	{
		if (iteratorSize == 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * removes item at current index, resolves if last item removed
	 * @return integer value if successful, NOT_FOUND if not
	 */
	public int removeAtCurrent()
	{
		int index, value;
		
		if (isEmpty())
		{
			return NOT_FOUND;
		}

		value = iteratorArray[currentIndex];

		if (currentIndex != iteratorSize - 1)
		{
			for (index = currentIndex; index < iteratorSize - 1; index++)
			{
				iteratorArray[index] = iteratorArray[index + 1];
			}
		}
		else
		{
			movePrev();
		}
		
		iteratorSize--;
		return value;
	}
	
	/**
	 * Resets array capacity to twice the capacity as needed
	 */
	public void resize()
	{
		int wkgIndex;
		int[] temp = new int[iteratorCapacity * 2];
		
		for (wkgIndex = 0; wkgIndex < iteratorSize; wkgIndex++)
		{
			temp[wkgIndex] = iteratorArray[wkgIndex];
		}
		
		iteratorArray = temp;
	}
	
	/**
	 * sets current index to beginning
	 * @return boolean result of action
	 */
	public boolean setToFirst()
	{
		if (isEmpty())
		{
			return false;
		}
		
		currentIndex = 0;
		return true;
	}
	
	/**
	 * sets current index to end
	 * @return boolean result of action
	 */
	public boolean setToLast()
	{
		if (isEmpty())
		{
			return false;
		}
		
		currentIndex = iteratorSize - 1;
		return true;
	}
}
