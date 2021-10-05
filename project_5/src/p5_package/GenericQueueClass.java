package p5_package;

public class GenericQueueClass<GenericData extends Comparable<GenericData>> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final char SPACE = ' ';
	
	private int frontIndex;
	private Object[] queueArray;
	private int queueCapacity;
	private int queueSize;
	private int rearIndex;
	
	public GenericQueueClass() 
	{
		queueArray = new Object[DEFAULT_CAPACITY];
		queueCapacity = DEFAULT_CAPACITY;
		queueSize = 0;
		frontIndex = 0;
		rearIndex = 0;
	}
	
	public GenericQueueClass(int capacity)
	{
		queueArray = new Object[capacity];
		queueCapacity = capacity;
		queueSize = 0;
		frontIndex = 0;
		rearIndex = 0;
	}
	
	public void clear()
	{
		queueSize = 0;
	}
	
	@SuppressWarnings("unchecked")
	public GenericData dequeue()
	{
		Object dequeued;
		
		if (isEmpty())
		{
			return null;
		}
		
		dequeued = queueArray[frontIndex];
		
		frontIndex++;
		frontIndex %= queueCapacity;
		queueSize--;
		
		return (GenericData)dequeued;
	}
	
	public void displayQueue()
	{
		if (!isEmpty())
		{
			int numChars = 15;
			int index, wkgIndex;
			
			System.out.print("Queue Front: ");
			
			for (index = 0; index < queueSize - 1; index++)
			{
				wkgIndex = (frontIndex + index) % queueCapacity;
				
				System.out.println((((StudentClass)queueArray[wkgIndex]).toString()));
				
				printChars(numChars, SPACE);
				
				numChars += 2;
			}
			
			System.out.println("Queue Rear: " + (((StudentClass)queueArray[rearIndex]).toString()));
		}
	}
	
	public void enqueue(GenericData newValue)
	{
		if (isEmpty())
		{
			rearIndex = -1;
		}
		
		rearIndex++;
		rearIndex %= queueCapacity;
		
		queueArray[rearIndex] = newValue;
		
		queueSize++;
		
		if (queueSize == queueCapacity)
		{
			resize();
		}
	}
	
	public boolean isEmpty()
	{
		if (queueSize == 0)
		{
			return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public GenericData peekFront()
	{
		Object peeked;
		
		if (isEmpty())
		{
			return null;
		}
		
		peeked = queueArray[frontIndex];
		
		return (GenericData)peeked;
	}
	
	public void printChars(int numChars, char outChar)
	{
		int index;
		
		for (index = 0; index < numChars; index++)
		{
			System.out.print(outChar);
		}
	}
	
	public void resize()
	{
		Object[] temp = new Object[queueCapacity * 2];
		int wkgIndex, queueWkgIndex;
		
		for (wkgIndex = 0; wkgIndex < queueSize; wkgIndex++)
		{
			queueWkgIndex = (frontIndex + wkgIndex) % queueCapacity;
			temp[wkgIndex] = queueArray[queueWkgIndex];
		}
		
		queueArray = temp;
		
		rearIndex = queueSize - 1;
		frontIndex = 0;
		queueCapacity *= 2;
	}
}
