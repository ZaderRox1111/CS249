package p2_package;

public class RegistrarClass {
	private final int DEFAULT_CAPACITY = 10;
	private final int NOT_FOUND = -1;
	
	public StudentClass[] studentArr;
	
	private int capacity;
	private int size;
	
	public RegistrarClass()
	{
		capacity = DEFAULT_CAPACITY;
		studentArr = new StudentClass[capacity];
		size = 0;
	}
	
	public RegistrarClass(int initialCapacity)
	{
		capacity = initialCapacity;
		studentArr = new StudentClass[capacity];
		size = 0;
	}
	
	public RegistrarClass(RegistrarClass copied)
	{
		capacity = copied.capacity;
		studentArr = copied.studentArr;
		size = copied.size;
	}
	
	public boolean addStudent(StudentClass newStudent)
	{
		studentArr[size] = newStudent;
		size++;
		return true;
	}
	
	public boolean addStudent(String stdName, int stdID, char stdGender, double stdGPA)
	{
		StudentClass newStudent = new StudentClass(stdName, stdID, stdGender, stdGPA);
		
		studentArr[size] = newStudent;
		size++;
		return true;
	}	
	
	private void copyArrayData(StudentClass[] dest, StudentClass[] source)
	{
		int index;
		StudentClass copy;
		
		for (index = 0; index < size; index++)
		{
			copy = new StudentClass(source[index]);
			dest[index] = copy;
		}
	}
	
	public void diagnosticArrayDump()
	{
		
	}
	
	public StudentClass findStudent(StudentClass student)
	{
		int foundIndex;
		StudentClass foundStudent;
		
		foundIndex = findStudentIndex(student);
		
		if (foundIndex == NOT_FOUND)
		{
			return null;
		}
		
		foundStudent = studentArr[foundIndex];
		
		return foundStudent;
	}
	
	public int findStudentIndex(StudentClass student)
	{
		int index;
		
		for (index = 0; index < size; index++)
		{
			if (studentArr[index] == student)
			{
				return index;
			}
		}
		
		return NOT_FOUND;
	}
	
	public StudentClass removeStudent(StudentClass student)
	{
		int index;
		int stdIndex = findStudentIndex(student);
		
		if (stdIndex == NOT_FOUND)
		{
			return null;
		}
		
		for (index = stdIndex; index < size; index++)
		{
			studentArr[index] = studentArr[index + 1];
		}
		
		return student;
	}
	
	public StudentClass[] runBubbleSort()
	{
		int outIndex, inIndex;
		
		StudentClass[] stdArr = new StudentClass[ capacity ];
        copyArrayData( stdArr, studentArr );
        
        for (outIndex = 0; outIndex < size; outIndex++)
        {
        	for (inIndex = 0; inIndex < size - 1; inIndex++)
        	{
        		if (stdArr[inIndex].compareTo(stdArr[inIndex + 1]) > 0)
        		{
        			swapValues(stdArr, inIndex, inIndex + 1);
        		}
        	}
        }
        
        return stdArr;
	}
	
	public StudentClass[] runInsertionSort()
	{
		int index, insertionIndex;
		StudentClass key;
		
		StudentClass[] stdArr = new StudentClass[ capacity ];
        copyArrayData( stdArr, studentArr );
		
		for (index = 1; index < size; index++)
		{
			insertionIndex = index - 1;
			key = stdArr[index];
			
			while (insertionIndex >= 0 && stdArr[insertionIndex].compareTo(key) > 0)
			{
				stdArr[insertionIndex + 1] = stdArr[insertionIndex];
				insertionIndex--;
			}
			
			stdArr[insertionIndex + 1] = key;
		}
		
		return stdArr;
	}
	
	public StudentClass[] runSelectionSort()
	{
		int inIndex, outIndex, selectionIndex;
		
		StudentClass[] stdArr = new StudentClass[ capacity ];
        copyArrayData( stdArr, studentArr );
        
        for (outIndex = 0; outIndex < size - 1; outIndex++)
        {
        	selectionIndex = outIndex;
        	for (inIndex = outIndex + 1; inIndex < size; inIndex++)
        	{
        		if (stdArr[inIndex].compareTo(stdArr[selectionIndex]) < 0)
        		{
        			selectionIndex = inIndex;
        		}
        	}
        	swapValues(stdArr, outIndex, selectionIndex);
        }
        
        return stdArr;
	}
	
	/**
     * Uses Shell's sorting algorithm to sort an array of integers
     * <p>
     * Shell's sorting algorithm is an optimized insertion algorithm
     * 
     * <p>
     * Note: Creates new StudentClass array, sorts contents of array, 
     * and returns the sorted result; 
     * does not modify (this) object student array
     * 
     * @return new StudentClass array with sorted items
     */
    public StudentClass[] runShellSort()
       {
        int gap, gapPassIndex, insertionIndex;
        StudentClass tempItem;
        
        StudentClass[] stdArr = new StudentClass[ capacity ];
        
        copyArrayData( stdArr, studentArr );

        for( gap = size / 2; gap > 0; gap /= 2 )
           {
            for( gapPassIndex = gap; gapPassIndex < size; gapPassIndex++ )
               {
                tempItem = stdArr[ gapPassIndex ];

                insertionIndex = gapPassIndex;

                while( insertionIndex >= gap 
                && stdArr[ insertionIndex - gap].compareTo( tempItem ) > 0 ) 
                   {
                    stdArr[ insertionIndex ] 
                                           = stdArr[ insertionIndex - gap ];
                   
                    insertionIndex -= gap;
                   }  // end search loop

                stdArr[ insertionIndex ] = tempItem;
               }  // end list loop
           
           }  // end gap size setting loop   
       
        return stdArr;
       }
	
	private void swapValues(StudentClass[] stdArray, int indexOne, int indexOther)
	{
		StudentClass storedStudent = stdArray[indexOne];
		
		stdArray[indexOne] = stdArray[indexOther];
		stdArray[indexOther] = storedStudent;
	}
}
