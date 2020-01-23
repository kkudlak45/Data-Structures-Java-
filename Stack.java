import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> implements StackInterface<E> {
	
	int size;
	E[] data;
	
	public Stack() {
		clear();
	}
	
	@Override
	public void clear() {
		data = (E[])(new Object[1]);
		size = 0;
	}

	@Override
	public boolean empty() {
		return size == 0;
	}

	@Override
	public E peek() {
		if (empty())
			throw new EmptyStackException();
		return data[size - 1];
	}

	@Override
	public E pop() {
		if (empty())
			throw new EmptyStackException();
		
		if (size <= data.length/4)
			data = Arrays.copyOf(data, size*2);
		
		E temp = data[--size];
		data[size] = null;
		return temp;
	}

	@Override
	public E push(E item) {
		if (size >= data.length)
			data = Arrays.copyOf(data, size*2);
		
		data[size] = item;
		size++;
		return item;
	}

	@Override
	public int search(Object o) {
		for (int i = 1; i < size + 1; i++)
			if (o.equals(data[size - i]))
				return i;

		return -1;
	}

	@Override
	public int size() {
		return size;
	}
	
	public String toString()
    {
   	 String s = "[";
   	 for(int i = size - 1; i >= 0; i--)
   		 s += data[i] + ", ";
   	 if(s.length() > 1)
   		 s = s.substring(0, s.length() - 2);
   	 s += "]";
   	 return s;
    }


}
