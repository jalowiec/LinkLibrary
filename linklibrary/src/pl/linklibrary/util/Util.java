package pl.linklibrary.util;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import pl.linklibrary.dao.CategoryDAO;
import pl.linklibrary.dao.LinkCategoryDAO;
import pl.linklibrary.dao.LinkDAO;
import pl.linklibrary.model.Category;
import pl.linklibrary.model.Link;

public class Util {

	public Set<Category> getAllCategories() {
		CategoryDAO dao = new CategoryDAO();
		Set<Category>results = dao.readAll(0);
		return results;
	}

	public Set<Integer> getCategoriesIdForLink(int linkId) {
		LinkCategoryDAO dao = new LinkCategoryDAO();
		Set<Integer> results = dao.readLinkCategories(linkId);
		return results;
	}

	public Set<Link> getAllLinks() {
		LinkDAO dao = new LinkDAO();
		Set<Link> results= dao.readAll(0);
		return results;
	}
	
	public Map<Integer, Integer> countLinksForCategory(){
		LinkCategoryDAO dao = new LinkCategoryDAO();
		Map<Integer, Integer> result = dao.countLinksForCategories();
		return result;
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
