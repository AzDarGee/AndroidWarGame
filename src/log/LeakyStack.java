package log;

import java.util.EmptyStackException;

//Joshua Saunders, 6859287
public class LeakyStack<E> extends AbstractStack<E> {

	//head is the top of the stack
	//tail is the bottom where the leak is
	NodeList<E> stack = new NodeList<E>();
	
	private int capacity;
	public LeakyStack(int capacity)
	{
		this.capacity = capacity;
	}
	
	@Override
	public void push(E e) {
		stack.addFirst(e);
		
		if(stack.size()-1 == capacity)
		{
			stack.remove(stack.last());
		}
	}

	@Override
	public E top() throws EmptyStackException {
		return stack.first().getElement();
	}

	@Override
	public E pop() throws EmptyStackException {
		E result = stack.first().getElement();
		stack.remove(stack.first());
		return result;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public String toString()
	{
		return stack.toString();
	}
	
}
