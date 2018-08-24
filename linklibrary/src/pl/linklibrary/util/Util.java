package pl.linklibrary.util;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import pl.linklibrary.dao.CategoryDAO;
import pl.linklibrary.dao.LinkCategoryDAO;
import pl.linklibrary.dao.LinkDAO;
import pl.linklibrary.model.Category;
import pl.linklibrary.model.Link;

public class Util {

	public Set<Category> getAllCategories() {
		Set<Category> results = new TreeSet<>();
		CategoryDAO dao = new CategoryDAO();
		results = dao.readAll(0);
		return results;
	}

	public Set<Integer> getCategoriesIdForLink(int linkId) {
		Set<Integer> results = new TreeSet<>();
		LinkCategoryDAO dao = new LinkCategoryDAO();
		results = dao.readLinkCategories(linkId);
		return results;
	}

	public Set<Link> getAllLinks() {
		Set<Link> results = new LinkedHashSet<>();
		LinkDAO dao = new LinkDAO();
		results = dao.readAll(0);
		return results;
	}

	public int[] convertArray(String[] array) {
		if (array == null) {
			return null;
		} else {
			int[] result = new int[array.length];
			int i = 0;
			for (String eachElement : array) {
				result[i++] = Integer.parseInt(eachElement);
			}

			return result;
		}
	}

}
