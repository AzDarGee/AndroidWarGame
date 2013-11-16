package log;

import java.util.EmptyStackException;

/**
 * Abstract class defining the operations that must be implemented by child classes
 * @param <E>
 * 
 * TODO Extends this abstract class
 */
public abstract class AbstractStack <E>{

	public abstract void push(E e);
	
	public abstract E top() throws EmptyStackException;
	
	public abstract E pop() throws EmptyStackException;
	
	public abstract int size();
	
	public abstract boolean isEmpty();
}
