

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Set, pl.linklibrary.model.Link, pl.linklibrary.model.Category"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="Content-Script-Type" content="text/javascript">
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <link href="css/styles.min.css" rel="stylesheet">
      <link href="css/bootstrap-toggle.min.css" rel="stylesheet">
      <title>Wszystkie linki</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="js/bootstrap-toggle.min.js"></script>
   </head>
   <body>
      <nav class="navbar navbar-default">
         <div class="container">
            <div class="collapse navbar-collapse navCollapse">
               <ul class="nav navbar-nav navbar-left">
                  <li><a href="index.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
               </ul>
               <form class="navbar-form navbar-right" action="addlink.jsp" method="post">
                  <div class="form-group">
                     <input name="url" type="text" class="form-control" placeholder="Add link" required>
                  </div>
                  <button type="submit" class="btn btn-default btn-lg">              
                  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                  </button>
               </form>
            </div>
            <button class="navbar-toggle" data-toggle="collapse"
               data-target=".navCollapse">
            <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
         </div>
      </nav>
      <div class="container">
      <div class="panel panel-default">
         <div class="panel-heading">
       <div class="btn-group pull-right">
        <button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modalCategoryManager">Zarzadzaj</button>

      </div>
            <h4 class="panel-title">Kategorie:</h4>
         </div>
         <div class="panel-body">       
            <div class="btn-group-toggle" data-toggle="buttons-checkbox"> 
               <form action="LinkList" method="post">
               <%               
               Set<Integer> chosenCategories = (Set<Integer>)request.getAttribute("chosenCategories");   
               Set<Category> categories = (Set<Category>)request.getAttribute("allCategories");
                  if(categories != null){
                  	for(Category category: categories) {
                  		if(chosenCategories!=null && chosenCategories.contains(category.getCategoryId())){
                  %>
               <label class="btn btn-light">
               <input type="checkbox" name="chosenCategories" checked="checked" value="<%= category.getCategoryId() %>" onclick="this.form.submit();")">&nbsp <%= category.getCategoryName() %>
               </label>
               <%
                  } else {
                  %>
               <label class="btn btn-light">
               <input type="checkbox" name="chosenCategories"  value="<%= category.getCategoryId() %>" onclick="this.form.submit();")">&nbsp <%= category.getCategoryName() %>
               </label>                  
               <%
                  		}
                  	}   	                  	
                }
                   
                 %>
			</form>                  
            </div>         
         </div>
         <table class="table table-striped table-borderd" style="width: 100%" border="0">
            <thead>
               <tr>
                  <th>Adres url</th>
                  <th>Nazwa linku</th>
                  <th>Opis linku</th>
                  <th></th>
                  <th></th>
                  <th></th>
               </tr>
            </thead>
            <%
               Set<Link> links = (Set<Link>)request.getAttribute("linksToDisplay");
               if(links != null)
               	for(Link eachLink: links) {
               %>
            <tr>
               <td><a target="_blank" href="<%= eachLink.getUrl() %>"><%= eachLink.getUrl() %></a></td>
               <td><%= eachLink.getName() %></td>
               <td><%= eachLink.getDescription() %></td>
               <td>
                  <form action="editlink.jsp" method="post">
                     <input type="hidden" name="link_id" value="<%= eachLink.getId() %>" />               	   
                     <input type="hidden" name="link_name" value="<%= eachLink.getName() %>" />               	                  	   
                     <input type="hidden" name="link_description" value="<%= eachLink.getDescription() %>" />               	                  	                  	   
                     <input type="hidden" name="link_url" value="<%= eachLink.getUrl() %>" />               	                  	                  	   
                     <button type="submit" class="btn btn-link" title="Edit link">
                     <span class="glyphicon glyphicon-edit" aria-hidden="true" ></span>
                     </button>
                  </form>
               </td>
               <td>
                  <form action="GetLinkCategory" method="post">
                     <input type="hidden" name="link_id" value="<%= eachLink.getId() %>" />   
                     <input type="hidden" name="link_url" value="<%= eachLink.getUrl() %>" />
                     <button type="submit" class="btn btn-link">          	                  		  
                     <span class="glyphicon glyphicon-tags" aria-hidden="true" ></span>
                     </button>
                  </form>
               </td>
               <td>
                  <form action="DeleteLink" method="post">
                     <input type="hidden" name="link_id" value="<%= eachLink.getId() %>" />
                     <button type="submit" class="btn btn-link" onclick="return confirm('Link: <%= eachLink.getUrl() %> zostanie usunięty')">          	                  		  
                     <span class="glyphicon glyphicon-trash" aria-hidden="true" ></span>
                     </button>
                  </form>
               </td>
            </tr>
            <%
               }
               %>
         </table>
         
      </div>
              <div class="modal fade" id="modalCategoryManager" tabindex="-1" role="dialog" aria-labelledby="modalCategoryManagerTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalCategoryManagerLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
 
      <div class="modal-body">
               <form action="AddCategory" method="post" class="form-inline">       
                     <input name="category" type="text" class="form-control" placeholder="Dodaj kategorie" required style="width: 450px;">
                     <button type="submit" class="btn btn-default">Dodaj</button>
               </form>
                 <hr>
               <form action="DeleteCategories" method="post" class="form-inline">
               <select name="categories" multiple class="form-control" size='10' style="width: 450px;">
                  <%
    
                     if(categories != null){
                   	for(Category category: categories) {

                   %>
                   	              		  
                  		<option  value="<%= category.getCategoryId() %>"> <%= category.getCategoryName() %></option>
                  <%
                     }}
                     %>
               </select>
               <button type="submit" class="btn btn-default">Usun</button>
               </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
 
    </div>
  </div>
</div>
      
      
      <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
      <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
      <script src="js/bootstrap.js"></script>
    </body>
</html>

