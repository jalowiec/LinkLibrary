
package pl.linklibrary.model;

/**
 *
 * @author lukasz
 */
public class Category implements Comparable<Category> {

	private int categoryId;
	private String categoryName;
	
	
	public Category() {
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int compareTo(Category category) {
		return this.categoryName.compareTo(category.getCategoryName());
	}





}
