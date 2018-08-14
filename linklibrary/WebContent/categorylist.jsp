<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List, pl.linklibrary.model.Category"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <link href="css/styles.min.css" rel="stylesheet">
      <title>Wszystkie kategorie</title>
      </head>
<title>Insert title here</title>
</head>
<body>
<div class="row">
  <div class="col-lg-6">
    <div class="input-group">
      <div class="input-group-btn">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Action <span class="caret"></span></button>
        <ul class="dropdown-menu">
          <li><a href="#">Action</a></li>
          <li><a href="#">Another action</a></li>
          <li><a href="#">Something else here</a></li>
          <li role="separator" class="divider"></li>
          <li><a href="#">Separated link</a></li>
        </ul>
      </div><!-- /btn-group -->
      <input type="text" class="form-control" aria-label="...">
    </div><!-- /input-group -->
  </div>
  <table style="width:50%">
    <tr>
    	<td>
               <div class="list-group">
          <%
               List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
                if(categoryList != null)
               	for(Category category: categoryList) {
               %>
  
            <button type="button" class="list-group-item"><%= category.getCategoryName() %></button>
	
	
		</td>
		</tr>
			</div>
			
               <%
               }
               %>

</body>
</html>