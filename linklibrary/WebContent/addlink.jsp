

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
   <head>
      <title>Dodawanie linku</title>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
      <link href="css/styles.css" type="text/css" rel="stylesheet">
   </head>
   <body>
      <div class="container">
         <h2>Dodawanie linku:</h2>
         <form action="AddLink" method="post">
            <div class="form-group">
               <label for="adres_url">Adres url:</label> <input type="text"
                  class="form-control"                  
                  value="<%= request.getParameter("url")%>"
                  name="url"
                  id="disabledTextInput"
                  readonly>
            </div>
            <div class="form-group">
               <label for="link_name">Nazwa linku:</label> <input type="text"
                  class="form-control" id="link_name" name="name">
            </div>
            <div class="form-group">
               <label for="comment">Opis linku:</label>
               <textarea class="form-control" rows="3" id="comment"
                  name="description"></textarea>
            </div>
    
            <button type="submit" class="btn btn-default">Dodaj</button>
         </form>
      </div>
   </body>
</html> 