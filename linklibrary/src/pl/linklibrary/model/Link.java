package pl.linklibrary.model;

public class Link {
	
	private int id;
	private String name;
	private String description;
	private String url;
	
	
	public Link(int id, String name, String description, String url) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Link() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	
	
	
	

}
