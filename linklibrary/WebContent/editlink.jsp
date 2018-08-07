

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Edycja linku</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
      <link href="css/styles.css" type="text/css" rel="stylesheet">
   </head>
   <body>

<div class="container">
  <h2>Edycja linku:</h2>
  <form action="EditLink" method="post">
  <div class="form-group">
      <input type="hidden" name="link_id" value="<%= request.getParameter("link_id") %>" />    
      <label for="adres_url">Adres url:</label>
      <input type="text" class="form-control" id="link_url"  name="url" value="<%= request.getParameter("link_url")%>" required>
    </div>  
    <div class="form-group">
      <label for="link_name">Nazwa linku:</label>
      <input type="text" class="form-control" id="link_name"  name="name" value="<%= request.getParameter("link_name")%>">
    </div>
 <div class="form-group">
  <label for="comment">Opis linku:</label>
  <textarea class="form-control" rows="3" id="comment" name="description"><%= request.getParameter("link_description")%></textarea>
</div> 

    <button type="submit" class="btn btn-default">Zmień</button>
  </form>
</div>

</body>
</html>
