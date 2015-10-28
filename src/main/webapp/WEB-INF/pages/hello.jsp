<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trader</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/background.css" />">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">It's your portfolio. You may switch to Watchlist for analysis and purchase of
            shares. </h3>
    </div>

    <div class="panel-body">

        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="/hello">Portfolio</a></li>
            <li role="presentation"><a href="/analytic">Watchlist</a></li>
            <li role="presentation"><a href="/logout">Logout</a></li>
        </ul>

        <table class="table table-striped">
            <thead>
            <tr>

                <td><b>Ticker</b></td>
                <td><b>Name</b></td>
                <td><b>Quantity</b></td>
                <td><b>Price</b></td>
                <td><b>Sum</b></td>
                <td align="center" colspan="2"><b>Action</b></td>


            </tr>
            </thead>
            <c:forEach items="${shares}" var="share">
                <tr>

                    <td><a href="/buy?ticker=${share.ticker}">${share.ticker}</a></td>
                    <td>${share.name}</td>
                    <td>${share.quantity}</td>
                    <td>${share.bid}</td>
                    <td>${share.bid*share.quantity}</td>
                    <td align="center"><a href="/buy?ticker=${share.ticker}">Buy</a></td>
                    <td align="center"><a href="/sell?ticker=${share.ticker}">Sell</a></td>

                </tr>
            </c:forEach>
            <tr>
                <td>Money</td>
                <td></td>
                <td></td>
                <td></td>
                <td>${user.money}</td>
                <td></td>
                <td></td>
            </tr>

            <tr>
                <td><b>TOTAL</b></td>
                <td></td>
                <td></td>
                <td></td>
                <td><b>${user.sum}</b></td>
                <td></td>
                <td></td>
            </tr>

        </table>
    </div>

</div>
</body>
</html>
