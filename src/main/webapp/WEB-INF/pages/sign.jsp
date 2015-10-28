<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/background.css" />">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Sign in new user</h3>
    </div>
    <div class="panel-body">

        <form role="form" class="form-horizontal" action="/adduser" method="post">

            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">Error while signin in. May be user is already exist</div>
            </c:if>

            <div class="input-group" style="float:none; margin:0 auto">

                <div class="input-group">
                    <span class="input-group-addon">
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
                    <input type="text" class="form-control" name="name" placeholder="Username" required pattern="^[a-zA-Z0-9]+$">
                </div>

                <div class="input-group">
                    <span class="input-group-addon">
                    <span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
                    <input type="password" class="form-control" name="password" placeholder="Password">
                </div>

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>

            <ul class="pager">
                <button type="submit" class="btn btn-primary btn-lg">Add new user</button>
                Or
                <a class="btn btn-default btn-lg" href="/login" role="button">Log in</a>
            </ul>

        </form>
    </div>
</div>
</body>
</html>