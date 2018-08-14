

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.List, pl.linklibrary.model.Link"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <link href="css/styles.min.css" rel="stylesheet">
      <title>Wszystkie linki</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
         <div class="panel-heading">Kategorie:</div>
         <div class="panel-body">
            <p>
            <div class="container">
               <div>
                  <button type="button" class="btn btn-primary">Java</button>
                  <button type="button" class="btn btn-primary">Wzorce projektowe</button>
                  <button type="button" class="btn btn-primary">Ksiązki informatyczne</button>
                  <button type="button" class="btn btn-primary">PHP</button>
                  <button type="button" class="btn btn-primary">Bob Martin</button>
                  <button type="button" class="btn btn-primary">Ksiązki do przeczytania</button>
               </div>
            </div>
            </p>
         </div>
         <table class="table table-striped table-borderd" style="width: 100%" border="0">
            <%
               List<Link> linkList = (List<Link>)request.getAttribute("linkList");
               if(linkList != null)
               	for(Link link: linkList) {
               %>
            <tr>
               <td><a target="_blank" href="<%= link.getUrl() %>"><%= link.getUrl() %></a></td>
               <td><%= link.getName() %></td>
               <td><%= link.getDescription() %></td>
               <td>
                  <form action="editlink.jsp" method="post">
                     <input type="hidden" name="link_id" value="<%= link.getId() %>" />               	   
                     <input type="hidden" name="link_name" value="<%= link.getName() %>" />               	                  	   
                     <input type="hidden" name="link_description" value="<%= link.getDescription() %>" />               	                  	                  	   
                     <input type="hidden" name="link_url" value="<%= link.getUrl() %>" />               	                  	                  	   
                     <button type="submit" class="btn btn-link" title="Edit link">
                     <span class="glyphicon glyphicon-edit" aria-hidden="true" ></span>
                     </button>
                  </form>
               </td>
               <td>
               <form action="AddCategoryToLink" method="post">
                     <input type="hidden" name="link_id" value="<%= link.getId() %>" />
                     <button type="submit" class="btn btn-link">          	                  		  
                     <span class="glyphicon glyphicon-tags" aria-hidden="true" ></span>
                     </button>
			</form>
			</td> 
			<td>            
                <form action="DeleteLink" method="post">
                     <input type="hidden" name="link_id" value="<%= link.getId() %>" />
                     <button type="submit" class="btn btn-link" onclick="return confirm('Link: <%= link.getUrl() %> zostanie usunięty')">          	                  		  
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
      <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
      <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
      <script src="js/bootstrap.js"></script>
   </body>
</html>

