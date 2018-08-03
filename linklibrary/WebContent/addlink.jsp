

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
  <form action="linklist.jsp">
  <div class="form-group">
      <label for="email">Adres url:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>  
    <div class="form-group">
      <label for="email">Nazwa linku:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
 <div class="form-group">
  <label for="comment">Opis linku:</label>
  <textarea class="form-control" rows="3" id="comment"></textarea>
</div> 

    <button type="submit" class="btn btn-default">Dodaj</button>
  </form>
</div>

</body>
</html>
