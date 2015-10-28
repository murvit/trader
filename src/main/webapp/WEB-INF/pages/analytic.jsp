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
        <h3 class="panel-title">It's your watched shares. Here you may buy shares or remove them from watchlist</h3>
    </div>

    <div class="panel-body">

        <ul class="nav nav-pills">
            <li role="presentation"><a href="/hello">Portfolio</a></li>
            <li role="presentation" class="active"><a href="/analytic">Watchlist</a></li>
        </ul>

        <table class="table table-striped">
            <thead>
            <tr>

                <td><b>Ticker</b></td>
                <td><b>Name</b></td>
                <td><b>Bid</b></td>
                <td><b>Ask</b></td>

                <td colspan="2" align="center"><b>Action</b></td>

            </tr>
            </thead>
            <c:forEach items="${shares}" var="share">
                <tr>

                    <td><a href="/buy?ticker=${share.ticker}">${share.ticker}</a></td>
 <!--                   <td>${share.ticker}</td>   -->
                    <td>${share.name}</td>
                    <td>${share.bid}</td>
                    <td>${share.ask}</td>
                    <td align="center"><a href="/buy?ticker=${share.ticker}">Buy</a></td>
                    <td align="center"><a href="/remove?ticker=${share.ticker}">Remove</a></td>

                </tr>
            </c:forEach>
        </table>
        <a href="/restore">Restore tickers</a>
    </div>
</div>
</body>
</html>
