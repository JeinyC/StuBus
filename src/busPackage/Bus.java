package busPackage;

public class Bus {
	private String id;
	private int seating;
	
	public Bus(String id, int seating) {
		this.id = id;
		this.seating = seating;
	}
	
	public Bus() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeating() {
		return seating;
	}

	public void setSeating(int seating) {
		this.seating = seating;
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", seating=" + seating + "]";
	}

	public int deleteSeating(int i) {
		return this.seating = this.seating - i; 
	}
	
}
