package idv.heimlich.domain;

public class OrgDo {
	
	private int id;
	private String name;
	private int year;	
	private String code;
	
	public OrgDo() {
		
	}

	public OrgDo(String name, int year, String code) {
		this.name = name;
		this.year = year;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "OrgDo [id=" + id + ", name=" + name + ", year=" + year
				+ ", code=" + code + "]";
	}

}
