
package pl.linklibrary.model;

/**
 *
 * @author lukasz
 */
public class Category {

	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category() {
	}

	public Category(String categoryName) {

		this.categoryName = categoryName;
	}

}
