<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Trader</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link href="<c:url value="/resources/css/background.css" />" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<c:url value="/login" var="loginUrl"/>

<div class = "container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Project trader 1.0</h3>
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

                <div class="input-group" style="float:none; margin:0 auto">
                    <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-user"
                                                      aria-hidden="true"></span></span>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Username">
                    </div>

                    <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"
                                                      aria-hidden="true"></span></span>
                        <input type="password" id="password" name="password" class="form-control"
                               placeholder="Password">
                    </div>
                    <br>

                    <div class="input-group">
                        <button type="submit" class="btn btn-primary btn-lg">Log in</button>
                        Or
                        <a class="btn btn-default btn-lg" href="/sign" role="button">Sign up</a>
                    </div>
                </div>

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>


            </form>
            <div class="jumbotron">

                <h1>Hello!</h1>

                <p>
                    This project is the emulation of stock trading.
                    When you register, you get a virtual $ 100,000.
                    With that money you may buy US stocks.
                    All quotes are real and taken from the Yahoo finance site.
                    Make your own portfolio and see how it will change over time.
                    Become the best portfolio manager!
                    If you are interested in the stock market, but you do not want to spend real money,
                    this project is for you!</p>
            </div>
        </div>

    </div>

</div>
<div class="container">
    <footer>
        <p>&copy; Vitaly Murashkin 2015</p>
    </footer>
</div>
</body>
</html>