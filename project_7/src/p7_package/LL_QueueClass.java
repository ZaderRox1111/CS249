package p7_package;

public class LL_QueueClass extends LL_IteratorClass {
	public LL_QueueClass()
	{
		super();
	}
	
	public LL_QueueClass(LL_QueueClass copied)
	{
		super(copied);
	}
	
	public void clear()
	{
		super.clear();
	}
	
	public int dequeue()
	{
		super.setToFirst();
		int value = super.getValueAtCurrent();
		super.removeAtCurrent();
		
		return value;
	}
	
	public void enqueue(int value)
	{
		super.insertAtEnd(value);
	}
	
	public boolean isEmpty()
	{
		return super.isEmpty();
	}
	
	public int peekFront()
	{
		super.setToFirst();
		int value = super.getValueAtCurrent();
		
		return value;
	}
}
