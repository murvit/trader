<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Trader</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<c:url value="/login" var="loginUrl"/>


<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Welcome</h3>
    </div>

    <div class="panel-body">

        <form action="${loginUrl}" method="post">

            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">Invalid username or password</div>
            </c:if>

            <c:if test="${param.logout != null}">
                <div class="alert alert-success" role="alert">You have been logged out</div>
            </c:if>

            <c:if test="${param.signed != null}">
                <div class="alert alert-success" role="alert">You successfully signed in</div>
            </c:if>

            <div class="input-group">
                <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"
                                                                        aria-hidden="true"></span></span>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username"
                       aria-describedby="basic-addon1">
            </div>


            <!--           <p>
                           <label for="username">Username</label>
                           <input type="text" id="username" name="username"/>
                       </p>
                -->

            <div class="input-group">
                <span class="input-group-addon" id="basic-addon2"><span class="glyphicon glyphicon-lock"
                                                                        aria-hidden="true"></span></span>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                       aria-describedby="basic-addon2">
            </div>

            <!--           <p>
                           <label for="password">Password</label>
                           <input type="password" id="password" name="password"/>
                       </p>
                -->

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

            <ul class="pager">
                <button type="submit" class="btn btn-primary btn-lg">Log in</button>
                Or
                <a class="btn btn-default btn-lg" href="/sign" role="button">Sign in</a>
            </ul>

        </form>

    </div>

</div>

</body>
</html>