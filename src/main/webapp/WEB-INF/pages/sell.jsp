<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sell</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="panel panel-danger">
    <div class="panel-heading">You may sell up to ${quantity} shares ${ticker}
    </div>
    <div class="panel-body">

        <ul class="nav nav-pills">
            <li role="presentation"><a href="/hello">Portfolio</a></li>
            <li role="presentation"><a href="/analytic">Analytic</a></li>
            <li role="presentation" class="active"><a href="">Sell</a></li>

        </ul>

        <c:if test="${error!= null}">
            <div class="alert alert-danger" role="alert">Error! Something wrong!</div>
        </c:if>

        <form role="form" class="form-horizontal" action="/sellshare" method="post">

            <ul class="pager">
                <img src="http://chart.finance.yahoo.com/z?s=${ticker}&t=6m" alt="chart"/>
            </ul>

            <div class="input-group" style="float:none; margin:0 auto">

                <div class="input-group">
                    <span class="input-group-addon">You have ${quantity}</span>
                    <input type="text" id="quantity" name="quantity" class="form-control" placeholder="Quantity"
                           required pattern="^[ 0-9]+$">
                </div>
            </div>

            <input type="hidden"
                   name="ticker"
                   value="${ticker}"/>

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <ul class="pager">
                <button type="submit" class="btn btn-danger btn-lg">Sell</button>
            </ul>
        </form>
    </div>
</div>

</body>
</html>
