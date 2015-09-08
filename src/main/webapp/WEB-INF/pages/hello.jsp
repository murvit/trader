<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trader</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>

        <td><b>Ticker</b></td>
        <td><b>Quantity</b></td>
        <td><b>Bid</b></td>
        <td><b>Ask</b></td>
        <td><b>One year target price</b></td>
        <td><b>Year low</b></td>
        <td><b>Year High</b></td>
        <td><b>Market capitalization</b></td>


    </tr>
    </thead>
    <c:forEach items="${shares}" var="share">
        <tr>

            <td>${share.ticker}</td>
            <td>${share.quantity}</td>
            <td>${share.bid}</td>
            <td>${share.ask}</td>
            <td>${share.oneYearTargetPrice}</td>
            <td>${share.yearLow}</td>
            <td>${share.yearHigh}</td>
            <td>${share.marketCapitalization}</td>

        </tr>
    </c:forEach>
</table>
${user.getMoney()}
</body>
</html>
