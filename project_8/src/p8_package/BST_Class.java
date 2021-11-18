package p8_package;

public class BST_Class {
	private StudentClassNode BST_Root;
	private String outputString;
	
	public BST_Class()
	{
		BST_Root = null;
		outputString = "";
	}
	
	public BST_Class(BST_Class copied)
	{
		outputString = copied.outputString;
		
		if (copied.BST_Root == null)
		{
			BST_Root = null;
		}
		else
		{
			BST_Root = copyConstHelper​(copied.BST_Root);
		}
	}
	
	private StudentClassNode copyConstHelper​(StudentClassNode copiedRef)
	{
		StudentClassNode wkgRef = new StudentClassNode(copiedRef);
		
		if (copiedRef.leftChildRef != null)
		{
			wkgRef.leftChildRef = copyConstHelper​(copiedRef.leftChildRef);
		}
		if (copiedRef.rightChildRef != null)
		{
			wkgRef.rightChildRef = copyConstHelper​(copiedRef.rightChildRef);
		}
		
		return wkgRef;
	}
	
	public void clearTree()
	{
		BST_Root = null;
	}
	
	public void insert​(StudentClassNode inData)
	{
		if (isEmpty())
		{
			BST_Root = new StudentClassNode(inData);
		}
		
		insertHelper(BST_Root, inData);
	}
	
	private void insertHelper(StudentClassNode localRoot, StudentClassNode inData)
	{
		if (inData.studentID < localRoot.studentID)
		{
			if (localRoot.leftChildRef == null)
			{
				localRoot.leftChildRef = new StudentClassNode(inData);
			}
			else
			{
				insertHelper(localRoot.leftChildRef, inData);
			}
		}
		else
		{
			if (localRoot.rightChildRef == null)
			{
				localRoot.rightChildRef = new StudentClassNode(inData);
			}
			else
			{
				insertHelper(localRoot.rightChildRef, inData);
			}
		}
	}
	
	public boolean isEmpty()
	{
		if (BST_Root == null)
		{
			return true;
		}
		
		return false;
	}
	
	public String outputInOrder()
	{
		outputString = "";
		
		outputInOrderHelper(BST_Root);
		
		return outputString;
	}
	
	private void outputInOrderHelper(StudentClassNode localRoot)
	{
		if (localRoot != null)
		{
			outputInOrderHelper(localRoot.leftChildRef);
			
			outputString += System.out.format("%s/%d/%c/%f \n", localRoot.name, 
											  localRoot.studentID, localRoot.gender, localRoot.gpa);
			
			outputInOrderHelper(localRoot.rightChildRef);
		}
	}
	
	public String outputPostOrder()
	{
		outputString = "";
		
		outputPostOrderHelper(BST_Root);
		
		return outputString;
	}
	
	private void outputPostOrderHelper(StudentClassNode localRoot)
	{
		if (localRoot != null)
		{
			outputInOrderHelper(localRoot.leftChildRef);
			
			outputInOrderHelper(localRoot.rightChildRef);
			
			outputString += System.out.format("%s/%d/%c/%f \n", localRoot.name, 
					  localRoot.studentID, localRoot.gender, localRoot.gpa);
		}
	}
	
	public String outputPreOrder()
	{
		outputString = "";
		
		outputPostOrderHelper(BST_Root);
		
		return outputString;
	}
	
	private void outputPreOrderHelper​(StudentClassNode localRoot)
	{
		if (localRoot != null)
		{
			outputString += System.out.format("%s/%d/%c/%f \n", localRoot.name, 
					  localRoot.studentID, localRoot.gender, localRoot.gpa);
			
			outputInOrderHelper(localRoot.leftChildRef);
			
			outputInOrderHelper(localRoot.rightChildRef);
		}
	}
	
	private StudentClassNode removeFromMax​(StudentClassNode parent, StudentClassNode child)
	{
		StudentClassNode removed = new StudentClassNode(child);
		
		if (child.rightChildRef != null)
		{
			return removeFromMax​(child, child.rightChildRef);
		}
		
		if (child.rightChildRef.leftChildRef == null)
		{
			parent.rightChildRef = null;
		}
		else
		{
			parent.rightChildRef = child.rightChildRef.leftChildRef;
		}
		
		return removed;
	}
	
	public StudentClassNode removeNode​(StudentClassNode inData)
	{
		StudentClassNode toBeRemoved;
		
		toBeRemoved = search(inData);
		
		if (toBeRemoved == null)
		{
			return null;
		}
		
		return removeNodeHelper​(toBeRemoved, inData);
	}
	
	private StudentClassNode removeNodeHelper​(StudentClassNode localRoot, StudentClassNode outData)
	{
		StudentClassNode removeMax;
		
		if (localRoot.leftChildRef == null)
		{
			return localRoot.rightChildRef;
		}
		if (localRoot.rightChildRef == null)
		{
			return localRoot.leftChildRef;
		}
		
		if (localRoot.leftChildRef.rightChildRef != null)
		{
			removeMax = removeFromMax​(localRoot.leftChildRef, localRoot.leftChildRef.rightChildRef);
			
			localRoot.gender = removeMax.gender;
			localRoot.gpa = removeMax.gpa;
			localRoot.studentID = removeMax.studentID;
			localRoot.name = removeMax.name;
		}
		else
		{
			localRoot.gender = localRoot.leftChildRef.gender;
			localRoot.gpa = localRoot.leftChildRef.gpa;
			localRoot.studentID = localRoot.leftChildRef.studentID;
			localRoot.name = localRoot.leftChildRef.name;
			
			localRoot = localRoot.leftChildRef.leftChildRef;
		}
		
		return localRoot;
	}
	
	public StudentClassNode search(StudentClassNode searchData)
	{
		if (isEmpty())
		{
			return null;
		}
		
		return searchHelper(BST_Root, searchData);
	}
	
	private StudentClassNode searchHelper(StudentClassNode localRoot, StudentClassNode searchData)
	{
		if (searchData.studentID < localRoot.studentID)
		{
			return searchHelper(localRoot.leftChildRef, searchData);
		}
		else if (searchData.studentID > localRoot.studentID)
		{
			return searchHelper(localRoot.rightChildRef, searchData);
		}
		
		return localRoot;
	}
}
