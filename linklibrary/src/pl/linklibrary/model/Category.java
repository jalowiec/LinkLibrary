
package pl.linklibrary.model;

/**
 *
 * @author lukasz
 */
public class Category {

	String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category() {
	}

	public Category(String categoryName) {
		// this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

}
