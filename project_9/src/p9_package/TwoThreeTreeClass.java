package p9_package;

public class TwoThreeTreeClass {
	private final int ONE_DATA_ITEM = 1;
	private final int TWO_DATA_ITEM = 2;
	private final int THREE_DATA_ITEM = 3;
	
	private static final int LEFT_CHILD_SELECT = 101;
	private static final int RIGHT_CHILD_SELECT = 102;
	
	private String outputString;
	private TwoThreeNodeClass root;
	
	public TwoThreeTreeClass()
	{
		root = null;
		outputString = "";
	}
	
	public TwoThreeTreeClass(TwoThreeTreeClass copied)
	{
		outputString = "";
		
		root = copyConstructorHelper(copied.root);
	}
	
	private TwoThreeNodeClass copyConstructorHelper(TwoThreeNodeClass workingCopiedRef)
	{
		TwoThreeNodeClass localRef;
		
		if (workingCopiedRef == null)
		{
			return null;
		}
		
		localRef = new TwoThreeNodeClass(workingCopiedRef);

		localRef.leftChildRef = copyConstructorHelper(workingCopiedRef.leftChildRef);
		
		if (localRef.leftChildRef != null)
		{
			localRef.leftChildRef.parentRef = localRef;
		}
		
		if (workingCopiedRef.numItems == TWO_DATA_ITEM)
		{
			localRef.numItems = TWO_DATA_ITEM;
			localRef.leftData = workingCopiedRef.leftData;
			localRef.rightData = workingCopiedRef.rightData;
			
			localRef.centerChildRef = copyConstructorHelper(workingCopiedRef.centerChildRef);
			
			if (localRef.centerChildRef != null)
			{
				localRef.centerChildRef.parentRef = localRef;
			}
		}
		
		localRef.rightChildRef = copyConstructorHelper(workingCopiedRef.rightChildRef);
		
		if (localRef.rightChildRef != null)
		{
			localRef.rightChildRef.parentRef = localRef;
		}
		
		return localRef;
	}
	
	private void addAndOrganizeData(TwoThreeNodeClass localRef, String itemStr)
	{
		if (localRef.numItems == ONE_DATA_ITEM)
		{
			if (compareStrings(itemStr, localRef.centerData) < 0)
			{
				localRef.leftData = itemStr;
				localRef.rightData = localRef.centerData;
			}
			else
			{
				localRef.leftData = localRef.centerData;
				localRef.rightData = itemStr;
			}
		}
		else
		{
			if (compareStrings(itemStr, localRef.leftData) < 0)
			{
				localRef.centerData = localRef.leftData;
				localRef.leftData = itemStr;
			}
			else if (compareStrings(itemStr, localRef.leftData) > 0)
			{
				localRef.centerData = localRef.rightData;
				localRef.rightData = itemStr;
			}
			else
			{
				localRef.centerData = itemStr;
			}
		}
	}
	
	public void addItem(String itemStr)
	{
		addItemHelper(root, itemStr);
	}
	
	private void addItemHelper(TwoThreeNodeClass localRef, String itemStr)
	{		
		if (localRef.leftChildRef == null)
		{
			addAndOrganizeData(localRef, itemStr);
			fixUpInsert(localRef);
		}
		else
		{
			if (localRef.numItems == ONE_DATA_ITEM)
			{
				if (compareStrings(itemStr, localRef.centerData) < 0)
				{
					addItemHelper(localRef.leftChildRef, itemStr);
				}
				else
				{
					addItemHelper(localRef.rightChildRef, itemStr);
				}
			}
			else
			{
				if (compareStrings(itemStr, localRef.leftData) < 0)
				{
					addItemHelper(localRef.leftChildRef, itemStr);
				}
				else if (compareStrings(itemStr, localRef.rightData) > 0)
				{
					addItemHelper(localRef.rightChildRef, itemStr);
				}
				else
				{
					addItemHelper(localRef.centerChildRef, itemStr);
				}
			}
		}
	}
	
	public void clear()
	{
		root = null;
		outputString = "";
	}
	
	public int compareStrings(String leftStr, String rightStr)
	{
        int leftSize = leftStr.length();
        int rightSize = rightStr.length();
        int difference, index = 0;
       
        while(index < leftSize && index < rightSize)
           {
            difference = leftStr.charAt(index) - rightStr.charAt(index);
           
            if(difference != 0)
               {
                return difference;
               }
           
            index++;
           }
       
        return leftSize - rightSize;
	}
	
	private void fixUpInsert(TwoThreeNodeClass localRef)
	{
		TwoThreeNodeClass wkgRef;
		
		if (localRef.numItems == THREE_DATA_ITEM)
		{
			if (localRef == root)
			{
				wkgRef = new TwoThreeNodeClass(localRef.centerData);
				wkgRef.parentRef = root;
				localRef.parentRef = root;
				
				root = wkgRef;
				
				localRef.leftChildRef = new TwoThreeNodeClass(LEFT_CHILD_SELECT, localRef);
				localRef.rightChildRef = new TwoThreeNodeClass(RIGHT_CHILD_SELECT, localRef);
			}
			else if (localRef.parentRef.numItems == ONE_DATA_ITEM)
			{
				localRef.parentRef.numItems = TWO_DATA_ITEM;
				
				if (localRef == localRef.parentRef.leftChildRef)
				{
					localRef.parentRef.rightData = localRef.parentRef.centerData;
					localRef.parentRef.leftData = localRef.centerData;
					
					localRef.parentRef.centerChildRef = localRef.rightChildRef;
					localRef.parentRef.leftChildRef = localRef.leftChildRef;
				}
				else
				{
					localRef.parentRef.leftData = localRef.parentRef.centerData;
					localRef.parentRef.rightData = localRef.centerData;

					localRef.parentRef.centerChildRef = localRef.leftChildRef;
					localRef.parentRef.leftChildRef = localRef.rightChildRef;
				}
			}
			else
			{
				if (localRef == localRef.parentRef.leftChildRef)
				{
					localRef.parentRef.centerData = localRef.parentRef.leftData;
					localRef.parentRef.leftData = localRef.centerData;
					
					localRef.parentRef.auxLeftRef = localRef.rightChildRef;
					localRef.parentRef.leftChildRef = localRef.leftChildRef;
				}
				else if (localRef == localRef.parentRef.centerChildRef)
				{
					localRef.parentRef.centerData = localRef.centerData;
					
					localRef.parentRef.auxLeftRef = localRef.leftChildRef;
					localRef.parentRef.auxRightRef = localRef.rightChildRef;
				}
				else
				{
					localRef.parentRef.centerData = localRef.parentRef.rightData;
					localRef.parentRef.rightData = localRef.centerData;
					
					localRef.parentRef.rightChildRef = localRef.rightChildRef;
					localRef.parentRef.auxRightRef = localRef.leftChildRef;
				}
			}
			
			fixUpInsert(localRef.parentRef);
		}
	}
	
	private boolean foundInNode(TwoThreeNodeClass localRef, String searchString)
	{
		if (localRef.numItems == ONE_DATA_ITEM)
		{
			if (searchString == localRef.centerData)
			{
				return true;
			}
		}
		else
		{
			if (searchString == localRef.leftData || searchString == localRef.rightData)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public String inOrderTraversal()
	{
		outputString = "";
		
		if (root == null)
		{
			return outputString;
		}
		
		inOrderTraversalHelper(root);
		
		return outputString;
	}
	
	private void inOrderTraversalHelper(TwoThreeNodeClass localRef)
	{
		
	}
	
	public boolean search(String searchStr)
	{
		return searchHelper(root, searchStr);
	}
	
	private boolean searchHelper(TwoThreeNodeClass localRef, String searchStr)
	{
		if (foundInNode(localRef, searchStr))
		{
			return true;
		}
		
		if (compareStrings(searchStr, localRef.centerData) < 0)
		{
			if (searchHelper(localRef.leftChildRef, searchStr))
			{
				return true;
			}
		}
		
		if (compareStrings(searchStr, localRef.centerData) > 0)
		{
			if (searchHelper(localRef.rightChildRef, searchStr))
			{
				return true;
			}
		}
		
		if (localRef.numItems == TWO_DATA_ITEM)
		{
			if (searchHelper(localRef.centerChildRef, searchStr))
			{
				return true;
			}
		}
		
		return false;
	}
}
