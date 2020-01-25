import java.util.ArrayList;

public class NaiveListSet implements Set {
	
	ArrayList<String> data = new ArrayList<String>(); 
	
	@Override
	public void add(String element) {
		if (!contains(element))
			data.add(element);
	}

	@Override
	public boolean contains(String element) {
		return data.contains(element);
	}
	
	public String toString() {
		String s = "[ ";
		if (data.size() > 0) {
			s += data.get(0);
			for (int i = 1; i < data.size(); i++) {
				s = s + ", " + data.get(i);
			}
		}
		return s + " ]";
	}

}
