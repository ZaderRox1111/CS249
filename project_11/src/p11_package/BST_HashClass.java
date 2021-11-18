package p11_package;

public class BST_HashClass {
	/**
	 * Table size default
	 */
	private static final int DEFAULT_TABLE_SIZE = 11;
	/**
	 * Array for hash table
	 */
	private BST_Class[] tableArray;
	/**
	 * Capacity (size) of the base table
	 */
	private int tableSize;
	
	/**
	 * Default Constructor
	 * Initializes array to default table size; initializes all BST elements
	 */
	public BST_HashClass()
	{
		tableArray = new BST_Class[DEFAULT_TABLE_SIZE];
		tableSize = DEFAULT_TABLE_SIZE;
		
		int index;
		
		for (index = 0; index < DEFAULT_TABLE_SIZE; index++)
		{
			tableArray[index] = new BST_Class();
		}
	}
	
	/**
	 * Initialization constructor
	 * initializes array to specified table size; initializes all BST elements
	 * @param inTableSize - sets table size
	 */
	public BST_HashClass(int inTableSize)
	{
		tableSize = inTableSize;
		tableArray = new BST_Class[inTableSize];

		int index;
		
		for (index = 0; index < inTableSize; index++)
		{
			tableArray[index] = new BST_Class();
		}
	}
	
	/**
	 * copy constructor
	 * @param copied - copied BST_HashClass
	 */
	public BST_HashClass(BST_HashClass copied)
	{
		int index;
		
		tableSize = copied.tableSize;
		
		for (index = 0; index < tableSize; index++)
		{
			tableArray[index] = new BST_Class(copied.tableArray[index]);
		}
	}
	
	/**
	 * Adds item to hash table
	 * Overloaded
	 * @param inName - name of student
	 * @param studentID - ID of student
	 * @param inGender - gender of student
	 * @param inGPA - gpa of student
	 */
	public void addItem(String inName, int studentID, char inGender, double inGPA)
	{
		int hash;
		StudentClassNode newItem = new StudentClassNode(inName, studentID, inGender, inGPA);
		
		hash = generateHash(newItem);
		
		tableArray[hash].insert(newItem);
	}
	
	/**
	 * Adds item to hash table
	 * Overloaded
	 * @param newItem - student class object
	 */
	public void addItem(StudentClassNode newItem)
	{
		int hash;

		hash = generateHash(newItem);
		
		tableArray[hash].insert(newItem);
	}
	
	/**
	 * Clears all BST elements
	 */
	public void clearHashTable()
	{
		int index;
		
		for (index = 0; index < tableSize; index++)
		{
			tableArray[index].clearTree();
		}
	}
	
	/**
	 * Searches for item in hash table using student ID as key
	 * @param studentID - student's ID
	 * @return StudentClassNode object found, or null if not found
	 */
	public StudentClassNode findItem(int studentID)
	{
		int hash;
		StudentClassNode found;
		
		StudentClassNode dummyNode = new StudentClassNode("", studentID, 'x', 0);
		
		hash = generateHash(dummyNode);
		
		found = tableArray[hash].search(dummyNode);
		
		return found;
	}
	
	/**
	 * Method uses student ID to generate hash value for use as index in hash table
	 * Process sums the Unicode values of each of the student ID digits converted to characters
	 * and then creates a hash index
	 * @param studentData - StudentClassNode object from which hash value will be generated
	 * @return integer hash value
	 */
	public int generateHash(StudentClassNode studentData)
	{
		int studentID = studentData.studentID;
		int localValue;
		int sum = 0;
		
		while (studentID > 0)
		{
			localValue = studentID % 10;
			sum += (int) (localValue + '0');
			studentID /= 10;
		}
		
		return sum % tableSize;
	}
	
	/**
	 * Removes item from hash table, using student ID as key
	 * @param studentID - student's ID
	 * @return StudentClassNode object removed, or null if not found
	 */
	public StudentClassNode removeItem(int studentID)
	{
		StudentClassNode found;
		int hash;
		
		found = findItem(studentID);
		
		if (found == null)
		{
			return null;
		}
		
		hash = generateHash(found);
		
		return tableArray[hash].removeNode(found);
	}
	
	/**
	 * traverses through all array bins, finds counts from each BST, then displays as table
	 * Shows table of list lengths, then shows table size, then shows the number of empty bins
	 * and the longest linked list of the set; adapts to any size array
	 */
	public void showHashTableStatus()
	{
		int index, curNum;
		int largest = 0, smallest = 0, numEmpty = 0;
		
		System.out.print("Array node report:\n");
		
		System.out.print("        ");
		for (index = 0; index < tableSize; index++)
		{
			curNum = tableArray[index].countNodes();
			
			if (curNum == 0)
			{
				numEmpty++;
			}
			else if (curNum > largest)
			{
				largest = curNum;
			}
			else if (curNum < smallest)
			{
				smallest = curNum;
			}
			
			System.out.format("    %3d", curNum);
		}
		System.out.println();
		
		System.out.print("         ");
		for (index = 0; index < tableSize; index++)
		{
			System.out.print("  -----");
		}
		System.out.println();
		
		System.out.print(" Index: ");
		for (index = 0; index < tableSize; index++)
		{
			System.out.format("    %3d", index);
		}
		System.out.println();
		System.out.println();
		
		System.out.format("With a table size of %d,\n", tableSize);
		System.out.format("The number of empty bins was %d.\n", numEmpty);
		System.out.format("The largest BST count was %d node(s).\n", largest);
		System.out.format("The smallest BST count was %d node(s).\n", smallest);
		
		System.out.println();
	}
}
