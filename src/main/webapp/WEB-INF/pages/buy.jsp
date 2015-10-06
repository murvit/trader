<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buy</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<ul class="nav nav-pills">
    <li role="presentation"><a href="/hello">Portfolio</a></li>
    <li role="presentation"><a href="/analytic">Analytic</a></li>
    <li role="presentation"><a href="/logout">Logout</a></li>
</ul>


<div class="panel panel-default">
    <div class="panel-heading">You may buy up to ... shares ${ticker}
    </div>
    <div class="panel-body">

        <c:if test="${error!= null}">
            <div class="alert alert-danger" role="alert">Error! Something wrong!</div>
        </c:if>

        <form role="form" class="form-horizontal" action="/buyshare" method="post">

            <div class="input-group" style="float:none; margin:0 auto">

                <div class="input-group">
                    <span class="input-group-addon">@</span>
                    <input type="text" id="quantity" name="quantity" class="form-control" placeholder="Quantity" required pattern="^[ 0-9]+$">
                </div>
            </div>

            <input type="hidden"
                   name="ticker"
                   value="${ticker}"/>

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <ul class="pager">
                <button type="submit" class="btn btn-primary btn-lg">Buy</button>
            </ul>
        </form>
    </div>
</div>

</body>
</html>