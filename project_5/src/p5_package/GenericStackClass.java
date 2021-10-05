package p5_package;

public class GenericStackClass<GenericData extends Comparable<GenericData>> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final char SPACE = ' ';
	
	private Object[] stackArray;
	private int stackCapacity;
	private int stackSize;
	
	public GenericStackClass() 
	{
		stackArray = new Object[DEFAULT_CAPACITY];
		stackCapacity = DEFAULT_CAPACITY;
		stackSize = 0;
	}
	
	public GenericStackClass(int capacity)
	{
		stackArray = new Object[capacity];
		stackCapacity = capacity;
		stackSize = 0;
	}
	
	public void clear()
	{
		stackSize = 0;
	}
	
	public void displayStack()
	{
		if (!isEmpty())
		{
			int numChars = 13;
			int index;
			
			System.out.print("Stack Top: ");
			
			for (index = stackSize - 1; index > 0; index--)
			{
				System.out.println((((StudentClass)stackArray[index]).toString()));
				
				printChars(numChars, SPACE);
				
				numChars += 2;
			}
			
			System.out.println("Stack Bottom: " + (((StudentClass)stackArray[0]).toString()));
		}
		
	}
	
	public boolean isEmpty()
	{
		if (stackSize == 0)
		{
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public GenericData peekTop()
	{
		GenericData peeked;
		
		if (isEmpty())
		{
			return null;
		}
		
		peeked = (GenericData)stackArray[stackSize - 1];
		
		return peeked;
	}
	
	@SuppressWarnings("unchecked")
	public GenericData pop()
	{
		GenericData popped;
		
		popped = (GenericData)stackArray[stackSize - 1];
		
		stackSize--;
		
		return popped;
	}
	
	public void printChars(int numChars, char outChar)
	{
		int index;
		
		for (index = 0; index < numChars; index++)
		{
			System.out.print(outChar);
		}
	}
	
	public void push(GenericData newValue)
	{
		stackArray[stackSize] = newValue;
		stackSize++;
		
		if (stackSize == stackCapacity)
		{
			resize();
		}
	}
	
	public void resize()
	{
		Object[] temp = new Object[stackCapacity * 2];
		int index;
		
		for (index = 0; index < stackSize; index++)
		{
			temp[index] = stackArray[index];
		}
		
		stackArray = temp;
		stackCapacity *= 2;
	}
}
