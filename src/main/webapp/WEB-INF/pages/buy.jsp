<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buy</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">You may buy up to ${quantity} shares ${ticker}</h3>
    </div>

    <div class="panel-body">

        <ul class="nav nav-pills">
            <li role="presentation"><a href="/hello">Portfolio</a></li>
            <li role="presentation"><a href="/analytic">Watchlist</a></li>
            <li role="presentation" class="active"><a href="">Buy</a></li>

        </ul>

        <c:if test="${error!= null}">
            <div class="alert alert-danger" role="alert">Error! Something wrong!</div>
        </c:if>

        <form role="form" class="form-horizontal" action="/buyshare" method="post">

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
                <img src="http://chart.finance.yahoo.com/z?s=${ticker}&t=6m" alt="chart"/>
            </ul>

            <div class="input-group" style="float:none; margin:0 auto">

                <div class="input-group">
                    <span class="input-group-addon">Up to ${quantity}</span>
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
                <button type="submit" class="btn btn-success btn-lg">Buy</button>
            </ul>

        </form>
    </div>
</div>

</body>
</html>