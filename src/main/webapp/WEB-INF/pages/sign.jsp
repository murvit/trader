<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">Error while signin in. May be user is already exist</div>
    </c:if>

    <form role="form" class="form-horizontal" action="/adduser" method="post">
        <div class="form-group"><h3>New user</h3></div>
        <div class="form-group"><input type="text" class="form-control" name="name" placeholder="Name"></div>
        <div class="form-group"><input type="text" class="form-control" name="password" placeholder="Password"></div>

        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>


        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
</div>
</body>
</html>