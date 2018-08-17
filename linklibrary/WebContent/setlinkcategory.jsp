

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.Set, pl.linklibrary.model.Category"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Dodawanie kategorii</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
      <link href="css/styles.css" type="text/css" rel="stylesheet">
   </head>
   <body>
      <div class="container">
      <h2>Dodawanie kategorii:</h2>
      <form action="SetLinkCategory" method="post">
         <div class="form-group">
            <label for="adres_url">Adres url:</label> <input type="text"
               class="form-control"                  
               value="<%= request.getAttribute("linkUrl")%>"
               name="url"
               id="disabledTextInput"
               readonly>
         </div>
         <input type="hidden" name="link_id" value="<%= request.getAttribute("linkId") %>" />   
         <div class="form-group">
            <label for="exampleSelect2">Wybrane kategorie:</label>
            <select name="categories" multiple class="form-control">
               <%
                  Set<Category> categories = (Set<Category>)request.getAttribute("categories");
                  Set<Integer> linkCategories = (Set<Integer>)request.getAttribute("linkCategories");
 
                  if(categories != null){
                	for(Category category: categories) {
                		if(linkCategories.contains(category.getCategoryId())){ 	
                %>
                	<option selected="selected" value="<%= category.getCategoryId() %>"> <%= category.getCategoryName() %></option>
                <% } else
                {
                %>
                	               		  
               		<option  value="<%= category.getCategoryId() %>"> <%= category.getCategoryName() %></option>
               <%
                  }}}
                  %>
            </select>
         </div>
         <button type="submit" class="btn btn-default">Dodaj</button>
      </form>
   </body>
</html>

