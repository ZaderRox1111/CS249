package p7_package;

public class LL_StackClass {
	LL_IteratorClass stackData;
	
	public LL_StackClass()
	{
		stackData = new LL_IteratorClass();
	}
	
	public LL_StackClass(LL_StackClass copied)
	{
		stackData = new LL_IteratorClass(copied.stackData);
	}
	
	public void clear()
	{
		stackData.clear();
	}
	
	public boolean isEmpty()
	{
		return stackData.isEmpty();
	}
	
	public int peekTop()
	{
		stackData.setToLast();
		return stackData.getValueAtCurrent();
	}
	
	public int pop()
	{
		stackData.setToLast();
		int value = stackData.getValueAtCurrent();
		stackData.removeAtCurrent();
		
		return value;
	}
	
	public void push(int newVal)
	{
		stackData.insertAtEnd(newVal);
	}
}
