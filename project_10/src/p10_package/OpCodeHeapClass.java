package p10_package;

/**
 * Array-based OpCodeClass min heap class used as a priority queue for OpCodeClass data
 * 
 * @author Zachariah Derrick
 *
 */

public class OpCodeHeapClass {
	/**
	 * Initial array capacity
	 */
	public final int DEFAULT_ARRAY_CAPACITY = 10;
	/**
	 * Array for heap
	 */
	private OpCodeClass[] heapArray;
	/**
	 * Management data for array
	 */
	private int arraySize;
	/**
	 * Management data for array
	 */
	private int arrayCapacity;
	/**
	 * Display flag can be set to observe bubble up and trickle down operations
	 */
	private boolean displayFlag;
	
	/**
	 * Default constructor sets up array management conditions and default display flag setting
	 */
	public OpCodeHeapClass() 
	{
		heapArray = new OpCodeClass[DEFAULT_ARRAY_CAPACITY];
		arraySize = 0;
		arrayCapacity = DEFAULT_ARRAY_CAPACITY;
		setDisplayFlag(false);
	}
	
	/**
	 * Copy constructor copies array and array management conditions and default display flag setting
	 * @param copied - OpCodeHeapClass object to be copied
	 */
	public OpCodeHeapClass(OpCodeHeapClass copied)
	{
		int wkgIndex;
		
		for (wkgIndex = 0; wkgIndex < copied.arraySize; wkgIndex++)
		{
			heapArray[wkgIndex] = new OpCodeClass(copied.heapArray[wkgIndex]);
		}
		
		arraySize = copied.arraySize;
		arrayCapacity = copied.arrayCapacity;
		setDisplayFlag(copied.displayFlag);
	}
	
	/**
	 * Accepts OpCodeData item and adds it to heap
	 * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data addition
	 * Note: must check for resize before attempting to add an item
	 * @param newItem - OpCodeClass data item to be added
	 */
	public void addItem(OpCodeClass newItem)
	{
		System.out.println();
		System.out.format("Adding new process: %s\n", newItem.toString());
		
		heapArray[arraySize] = newItem;
		bubbleUpArrayHeap(arraySize);
		
		arraySize++;
		checkForResize();
	}
	
	/**
	 * Recursive operation to reset data in the correct order for the min heap after new data addition
	 * @param currentIndex - index of current item being assessed, and moved up as needed
	 */
	private void bubbleUpArrayHeap(int currentIndex)
	{
		OpCodeClass parent;
		int parentIndex;
		
		if (currentIndex != 0)
		{
			//even means right child
			//otherwise it is left child
			if (currentIndex % 2 == 0)
			{
				parentIndex = ( currentIndex - 2 ) / 2;
			}
			else
			{
				parentIndex = ( currentIndex - 1 ) / 2;
			}
			
			parent = heapArray[parentIndex];
				
			if (heapArray[currentIndex].compareTo(parent) < 0)
			{
				//display
				if (displayFlag)
				{
					System.out.print("   - Bubble up:\n");
					System.out.format("     - Swapping parent: %s with child: %s\n", parent, heapArray[currentIndex]);
				}
				
				//swap
				heapArray[parentIndex] = heapArray[currentIndex];
				heapArray[currentIndex] = parent;
				
				bubbleUpArrayHeap(parentIndex);
			}
		}
	}
	
	/**
	 * Automatic resize operation used prior to any new data addition in the heap
	 * Tests for full heap array, and resizes to twice the current capacity as required
	 */
	private void checkForResize()
	{
		OpCodeClass[] temp;
		int wkgIndex;
		
		if (arraySize >= arrayCapacity)
		{
			temp = new OpCodeClass[arrayCapacity * 2];
			
			for (wkgIndex = 0; wkgIndex < arraySize; wkgIndex++)
			{
				temp[wkgIndex] = new OpCodeClass(heapArray[wkgIndex]);
			}
			
			heapArray = temp;
			arrayCapacity *= 2;
		}
	}
	
	/**
	 * Tests for empty heap
	 * @return boolean result of test
	 */
	public boolean isEmpty()
	{
		if (arraySize == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Removes OpCodeClass data item from top of min heap, 
	 * thus being the operation with the lowest priority value
	 * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data removal
	 * @return OpCodeClass item removed
	 */
	public OpCodeClass removeItem()
	{
		OpCodeClass removed;
		
		if (isEmpty()) {
			return null;
		} 
		else if (arraySize == 1)
		{
			//display
			if (displayFlag)
			{
				System.out.println();
				System.out.format("Removing process: %s\n", heapArray[0]);
			}
			
			removed = heapArray[0];
			arraySize--;
		}
		else
		{
			//display
			if (displayFlag)
			{
				System.out.println();
				System.out.format("Removing process: %s\n", heapArray[0]);
			}
			
			removed = heapArray[0];
			heapArray[0] = heapArray[arraySize - 1];
			heapArray[arraySize - 1] = removed;
			arraySize--;
			
			trickleDownArrayHeap(0);
		}
		
		return removed;
	}
	
	/**
	 * Utility method to set the display flag 
	 * for displaying internal operations of the heap bubble and trickle operations
	 * @param setState - flag used to set the state to display, or not
	 */
	public void setDisplayFlag(boolean setState)
	{
		displayFlag = setState;
	}
	
	/**
	 * Dumps array to screen as is, no filtering or management
	 */
	public void showArray()
	{
		int wkgIndex;
		
		for (wkgIndex = 0; wkgIndex < arraySize; wkgIndex++)
		{
			System.out.format("%s\n", heapArray[wkgIndex].toString());
		}
		
		System.out.println();
	}
	
	/**
	 * Recursive operation to reset data in the correct order for the min heap after data removal
	 * @param currentIndex - index of current item being assessed, and moved down as required
	 */
	private void trickleDownArrayHeap(int currentIndex)
	{
		OpCodeClass temp;
		int rightChildIndex, leftChildIndex;
		
		rightChildIndex = (currentIndex * 2) + 2;
		leftChildIndex = (currentIndex * 2) + 1;
		
		if (rightChildIndex < arraySize)
		{
			if (heapArray[rightChildIndex].compareTo(heapArray[leftChildIndex]) < 0)
			{
				//display
				if (displayFlag)
				{
					System.out.print("   - Trickle down:\n");
					System.out.format("     - Swapping parent: %s with child: %s\n",
									  heapArray[currentIndex], heapArray[rightChildIndex]);
				}
				
				//swap
				temp = heapArray[currentIndex];
				heapArray[currentIndex] = heapArray[rightChildIndex];
				heapArray[rightChildIndex] = temp;
				
				trickleDownArrayHeap(rightChildIndex);
			}
			else
			{
				//display
				if (displayFlag)
				{
					System.out.print("   - Trickle down:\n");
					System.out.format("     - Swapping parent: %s with child: %s\n",
									  heapArray[currentIndex], heapArray[leftChildIndex]);
				}
				
				//swap
				temp = heapArray[currentIndex];
				heapArray[currentIndex] = heapArray[leftChildIndex];
				heapArray[leftChildIndex] = temp;
				
				trickleDownArrayHeap(leftChildIndex);
			}
		}
		else if (leftChildIndex < arraySize)
		{
			//display
			if (displayFlag)
			{
				System.out.print("   - Trickle down:\n");
				System.out.format("     - Swapping parent: %s with child: %s\n",
								  heapArray[currentIndex], heapArray[leftChildIndex]);
			}
			
			//swap
			temp = heapArray[currentIndex];
			heapArray[currentIndex] = heapArray[leftChildIndex];
			heapArray[leftChildIndex] = temp;
			
			trickleDownArrayHeap(leftChildIndex);
		}
	}
}
