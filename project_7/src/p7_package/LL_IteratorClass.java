package p7_package;

public class LL_IteratorClass {
	
	private NodeClass currentRef;
	private NodeClass headRef;
	private static final char LEFT_BRACKET = '[';
	private static final int NOT_FOUND = -1;
	private static final char RIGHT_BRACKET = ']';
	private static final char SPACE = ' ';
	
	class NodeClass  
	{
		private int data;
		private NodeClass nextRef;
		
		public NodeClass() 
		{
			data = 0;
			nextRef = null;
		}
		
		public NodeClass(int value) 
		{
			data = value;
			nextRef = null;
		}
		
		public NodeClass(NodeClass copied) 
		{
			data = copied.data;
			nextRef = copied.nextRef;
		}
	}
	
	public LL_IteratorClass()
	{
		currentRef = null;
		headRef = null;
	}
	
	public LL_IteratorClass(LL_IteratorClass copied)
	{
		NodeClass wkgRef, cpdRef;
		
		if (isEmpty())
		{
			currentRef = null;
			headRef = null;
		}
		else
		{
			currentRef = new NodeClass(copied.currentRef);
			headRef = new NodeClass(copied.headRef);
				
			cpdRef = copied.headRef.nextRef;
			wkgRef = headRef;
				
			while(cpdRef != null)
			{
				wkgRef.nextRef = new NodeClass(cpdRef);
				wkgRef = wkgRef.nextRef;
				cpdRef = cpdRef.nextRef;
			}
		}
		
	}
	
	public void clear()
	{
		headRef = null;
		currentRef = null;
	}
	
	public void displayIterator()
	{
		NodeClass wkgRef = headRef;
		
		System.out.print("Iterator: ");
		
		while (wkgRef != null)
		{
			if (wkgRef == currentRef)
			{
				System.out.print("" + LEFT_BRACKET + wkgRef.data + RIGHT_BRACKET + SPACE);
			}
			else
			{
				System.out.print("" + wkgRef.data + SPACE);
			}
		}
		
		System.out.println();
	}
	
	public NodeClass getCurrentPriorRef()
	{
		NodeClass wkgRef = headRef;
		if(isEmpty() || currentRef == headRef)
		{
			return null;
		}
		
		while(wkgRef.nextRef != currentRef)
		{
			wkgRef = wkgRef.nextRef;
		}
		
		return wkgRef;
	}
	
	public int getValueAtCurrent()
	{
		if (isEmpty())
		{
			return NOT_FOUND;
		}
		
		return currentRef.data;
	}
	
	public boolean hasNext()
	{
		if (currentRef.nextRef == null)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean hasPrev()
	{
		if (currentRef == headRef)
		{
			return false;
		}
		
		return true;
	}
	
	public void insertAtCurrent(int newVal)
	{
		NodeClass newRef = new NodeClass(newVal);
		
		if (isEmpty())
		{
			headRef = newRef;
			currentRef = newRef;
		}
		else if (currentRef == headRef)
		{
			newRef.nextRef = currentRef;
			currentRef = newRef;
			headRef = newRef;
		}
		else
		{
			getCurrentPriorRef().nextRef = newRef;
			newRef.nextRef = currentRef;
			currentRef = newRef;
		}
	}
	
	public void insertAtEnd(int newVal)
	{
		NodeClass newRef = new NodeClass(newVal);
		NodeClass wkgRef;
		
		if (isEmpty())
		{
			headRef = newRef;
			currentRef = newRef;
		}
		else
		{
			wkgRef = currentRef;
			
			while (wkgRef.nextRef != null)
			{
				wkgRef = wkgRef.nextRef;
			}
			
			wkgRef.nextRef = newRef;
		}
	}
	
	public void insertAtFront(int newVal)
	{
		NodeClass newRef = new NodeClass(newVal);
		
		if (isEmpty())
		{
			headRef = newRef;
			currentRef = newRef;
		}
		
		newRef.nextRef = headRef;
		headRef = newRef;
	}
	
	public boolean moveNext()
	{
		if (hasNext())
		{
			currentRef = currentRef.nextRef;
			return true;
		}
		
		return false;
	}
	
	public boolean movePrev()
	{
		if (hasPrev())
		{
			currentRef = getCurrentPriorRef();
			return true;
		}
		
		return false;
	}
	
	public boolean isEmpty()
	{
		if (headRef == null)
		{
			return true;
		}
		
		return false;
	}
	
	public int removeAtCurrent()
	{
		if (isEmpty())
		{
			return NOT_FOUND;
		}
		
		int value = currentRef.data;

		if (currentRef == headRef)
		{
			headRef = currentRef.nextRef;
			currentRef = headRef;
			
			return value;
		}
		
		NodeClass prevRef = getCurrentPriorRef();
		
		prevRef.nextRef = currentRef.nextRef;
		currentRef = prevRef.nextRef;
		
		return value;
	}
	
	public boolean setToFirst()
	{
		if (isEmpty())
		{
			return false;
		}
		
		currentRef = headRef;
		return true;
	}
	
	public boolean setToLast()
	{
		if (isEmpty())
		{
			return false;
		}
		
		while (currentRef.nextRef != null)
		{
			currentRef = currentRef.nextRef;
		}
		
		return true;
	}
}
