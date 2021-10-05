package p3_package;

public class LogN_SortDriverClass 
{
	public LogN_SortDriverClass()
	{
		
	}
	
	public static int compareStrings(String strOne, String strTwo)
	{
		int oneSize = strOne.length();
        int twoSize = strTwo.length();
        int difference, index = 0;
       
        while (index < oneSize && index < twoSize)
           {
            difference = toLowerCase(strOne.charAt(index)) - toLowerCase(strTwo.charAt(index));
           
            if (difference != 0)
               {
                return difference;
               }
           
            index++;
           }
       
        return oneSize - twoSize;
	}
	
	private static void runMerge(String[] localArray, int lowIndex, int middleIndex, int highIndex)
	{
		int copyIndex, leftIndex = 0, rightIndex = 0;
		int sizeOne = middleIndex - lowIndex + 1;
		int sizeTwo = highIndex - middleIndex;
		
		String[] leftArr = new String[sizeOne];
		String[] rightArr = new String[sizeTwo];
		
		for (copyIndex = 0; copyIndex < sizeOne; ++copyIndex)
		{
			leftArr[copyIndex] = localArray[lowIndex + copyIndex];
		}
		for (copyIndex = 0; copyIndex < sizeTwo; ++copyIndex)
		{
			rightArr[copyIndex] = localArray[middleIndex + 1 + copyIndex];
		}
		
		copyIndex = lowIndex;
		
		while (leftIndex < sizeOne && rightIndex < sizeTwo)
		{
			if (compareStrings(leftArr[leftIndex], rightArr[rightIndex]) < 0)
			{
				localArray[copyIndex] = leftArr[leftIndex];
				leftIndex++;
			}
			else 
			{
				localArray[copyIndex] = rightArr[rightIndex];
				rightIndex++;
			}
			copyIndex++;
		}
		
		// Copy the rest over to resolve leftovers
		while (leftIndex < sizeOne)
		{
			localArray[copyIndex] = leftArr[leftIndex];
			leftIndex++;
			copyIndex++;
		}
		
		while (rightIndex < sizeTwo)
		{
			localArray[copyIndex] = rightArr[rightIndex];
			rightIndex++;
			copyIndex++;
		}
	}
	
	public static void runMergeSort(String[] localArray, int size)
	{
		int lowIndex = 0, highIndex = size - 1;
		
		runMergeSortHelper(localArray, lowIndex, highIndex);
	}
	
	private static void runMergeSortHelper(String[] localArray, int lowIndex, int highIndex)
	{
		if (lowIndex < highIndex)
		{
			int middleIndex = lowIndex + ((highIndex - lowIndex) / 2);
			
			runMergeSortHelper(localArray, lowIndex, middleIndex);
			runMergeSortHelper(localArray, middleIndex + 1, highIndex);
			
			runMerge(localArray, lowIndex, middleIndex, highIndex);
		}
	}
	
	private static int runPartition(String[] localArray, int lowIndex, int highIndex)
	{
		int smallIndex, bigIndex;
		
		String pivot = localArray[highIndex];
		
		smallIndex = lowIndex - 1;
		for (bigIndex = lowIndex; bigIndex <= highIndex - 1; bigIndex++)
		{
			if (compareStrings(localArray[bigIndex], pivot) < 0)
			{
				smallIndex++;
				swapValues(localArray, smallIndex, bigIndex);
			}
		}
		swapValues(localArray, smallIndex + 1, highIndex);
		return (smallIndex + 1);
	}
	
	public static void runQuickSort(String[] localArray, int size)
	{
		int lowIndex = 0, highIndex = size - 1;
		
		runQuickSortHelper(localArray, lowIndex, highIndex);
	}
	
	public static void runQuickSortHelper(String[] localArray, int lowIndex, int highIndex)
	{
		int partitionIndex;	
		
		if (lowIndex < highIndex)
		{
			partitionIndex = runPartition(localArray, lowIndex, highIndex);
			
			runQuickSortHelper(localArray, lowIndex, partitionIndex - 1);
			runQuickSortHelper(localArray, partitionIndex + 1, highIndex);
		}
	}
	
	private static void swapValues(String[] localArray, int indexOne, int indexOther)
	{
		String temp = localArray[indexOne];
		localArray[indexOne] = localArray[indexOther];
		localArray[indexOther] = temp;
	}
	
	private static char toLowerCase(char testChar)
	{
		char baseChar = 'A';
		char lowerBaseChar = 'a';
		
		int difference = testChar - baseChar;
		char lowerChar = (char)(lowerBaseChar + difference);
		
		return lowerChar;
	}
}
