<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Trader</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<table class="table table-striped">
  <thead>
  <tr>

    <td><b>1</b></td>
    <td><b>2</b></td>
    <td><b>3</b></td>
    <td><b>4</b></td>
    <td><b>5</b></td>

  </tr>
  </thead>
  <c:forEach items="${shares}" var="share">
    <tr>

      <td>11</td>
      <td>${share.quantity}</td>
      <td>${share.ask}</td>
      <td>${share.bid}</td>
      <td>${share.marketCapitalization}</td>

    </tr>
  </c:forEach>
</table>
</body>
</html>
