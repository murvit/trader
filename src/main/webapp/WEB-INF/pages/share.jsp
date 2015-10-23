<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trader</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Info about share ${share.ticker}</h3>
    </div>

    <div class="panel-body">

        <ul class="nav nav-pills">
            <li role="presentation"><a href="/hello">Portfolio</a></li>
            <li role="presentation"><a href="/analytic">Watchlist</a></li>
            <li role="presentation" class="active"><a href="">Share info</a></li>
        </ul>

        <table class="table table-striped">
            <thead>
            <tr>

                <td><b>Ticker</b></td>
                <td><b>Name</b></td>
                <td><b>Bid</b></td>
                <td><b>Ask</b></td>
                <td><b>1 Y target</b></td>
                <td><b>Year low</b></td>
                <td><b>Year High</b></td>
                <td><b>MCap</b></td>

            </tr>
            </thead>
            <tr>

                <td>${share.ticker}</td>
                <td>${share.name}</td>
                <td>${share.bid}</td>
                <td>${share.ask}</td>
                <td>${share.oneYearTargetPrice}</td>
                <td>${share.yearLow}</td>
                <td>${share.yearHigh}</td>
                <td>${share.marketCapitalization}</td>

            </tr>
        </table>

        <ul class="pager">
            <img src="http://chart.finance.yahoo.com/z?s=${share.ticker}&t=6m" alt="chart"/>
        </ul>

        <ul class="pager">
            <a class="btn btn-success btn-lg" href="/buy?ticker=${share.ticker}" role="button">Buy</a>
        </ul>

    </div>
</div>
</body>
</html>
