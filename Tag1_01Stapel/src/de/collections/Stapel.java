package de.collections;

import java.util.ArrayList;
import java.util.List;

public class Stapel {
	
	private static final int DEFAULT_SIZE = 10;
	private List<Object> data;
	
	
	public Stapel() { 
		data = new ArrayList<>(DEFAULT_SIZE);
		
	}
	
	public void push(Object value) throws StapelException{
		if(isFull()) 
			throw new StapelException("Overflow");
		
		data.add(value);
	}
	
	public Object pop() throws StapelException {
		if(isEmpty())
			throw new StapelException("Underflow");
		
		return data.remove(data.size() - 1);
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
				
	}
	
	public boolean isFull() {
		return data.size() >= DEFAULT_SIZE;
	}

}
