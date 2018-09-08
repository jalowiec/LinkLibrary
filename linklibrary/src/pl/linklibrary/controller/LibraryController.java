package pl.linklibrary.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.linklibrary.dao.CategoryDAO;
import pl.linklibrary.dao.LinkCategoryDAO;
import pl.linklibrary.dao.LinkDAO;
import pl.linklibrary.model.Category;
import pl.linklibrary.model.Link;

public class LibraryController {

	public boolean addCategory(String category) {
		category = category.toLowerCase();
		CategoryDAO dao = new CategoryDAO();
		return dao.create(category);
	}

	public boolean addLink(Link link) {
		LinkDAO dao = new LinkDAO();
		return dao.create(link);
	}

	public boolean deleteCategories(int[] categories) {
		CategoryDAO dao = new CategoryDAO();
		return dao.delete(categories);
	}

	public boolean deleteLink(int link) {
		LinkDAO dao = new LinkDAO();
		return dao.delete(link);
	}

	public boolean editLink(Link link) {
		LinkDAO dao = new LinkDAO();
		return dao.update(link);
	}

	public Link getLink(int link) {
		LinkDAO dao = new LinkDAO();
		return dao.read(link);
	}

	public Set<Integer> getLinksForCategory(int categoryId) {
		LinkCategoryDAO dao = new LinkCategoryDAO();
		Set<Integer> results = dao.readCategories(categoryId);
		return results;
	}

	public Set<Link> getAllLinks() {
		LinkDAO dao = new LinkDAO();
		Set<Link> results = dao.readAll(0);
		return results;
	}

	public Set<Integer> getCategoriesForLink(int linkId) {
		LinkCategoryDAO dao = new LinkCategoryDAO();
		Set<Integer> results = dao.readCategories(linkId);
		return results;
	}

	public Set<Category> getAllCategories() {
		CategoryDAO dao = new CategoryDAO();
		Set<Category> results = dao.readAll(0);
		return results;
	}

	public Set<Link> getLinksToDisplay(String[] chosenCategories) {
		Set<Link> results = new HashSet<>();
		if (chosenCategories == null) {
			return this.getAllLinks();
		} else {
			Set<Integer> linksId = new HashSet<>();
			System.out.println("przed wywalk1");
			int[] categories = Arrays.asList(chosenCategories).stream().mapToInt(Integer::parseInt).toArray();
			System.out.println("przed wywalk2");			
			for (int eachElement : categories) {
				linksId.addAll(this.getLinksForCategory(eachElement));										
			}
			System.out.println("przed wywalk3");			
			for(int eachElement : linksId) {
				results.add(this.getLink(eachElement));
			}
		}
		return results;
	}

	public Map<Integer, Integer> countLinksForAllCategories() {
		LinkCategoryDAO dao = new LinkCategoryDAO();
		Map<Integer, Integer> counterLinksForCategories = dao.countLinksForCategories();
		Map<Integer, Integer> result = new HashMap<>();
		Set<Category> allCategories = getAllCategories();
		for (Category eachCategory : allCategories) {
			Integer categoryId = eachCategory.getCategoryId();
			if (counterLinksForCategories.containsKey(categoryId)) {
				result.put(categoryId, counterLinksForCategories.get(categoryId));
			} else {
				result.put(categoryId, 0);
			}
		}
		return result;
	}

}
