<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="/WEB-INF/custom_tag.tld" prefix="custom" %>--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <title>MyOrders</title>
    <jsp:include page="../common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="../common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message
        key="myOrders"/></h2>
<div class="row " style="align-self: center">
    <div class="col">
        <h5 class="display-8 text-center" style="color: #000102"><fmt:message key="status"/></h5>
        <form>
            <div class="btn-group justify-content-center" role="group">
                <c:forEach var="statusVar" items="${orderStatuses}">
                    <input type="submit" class="btn-check active" name="status"
                           value="${param.status eq statusVar? '': statusVar}"
                           id="${statusVar}"
                           autocomplete="off">
                    <label class="btn btn-primary ${param.status == statusVar? 'active':''}"
                           for="${statusVar}"><fmt:message
                            key="${statusVar}"/></label>
                </c:forEach>
            </div>
        </form>
        <c:if test="${page.totalPages > 1}">
            <div>
                <nav aria-label="...">
                    <ul class="pagination justify-content-center">
                        <c:choose>
                            <c:when test="${page.number <= 0}">
                                <li class="page-item disabled">
                                    <span class="page-link"><fmt:message key="previous"/></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link"
                                       href="?page=${page.number - 1}&sortBy=${param.sortBy}&status=${param.status}"><fmt:message
                                            key="previous"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${page.totalPages }" var="i">
                            <c:choose>
                                <c:when test="${page.number  eq i - 1}">
                                    <li class="page-item active" aria-current="page">
                                        <span class="page-link">${i}</span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?page=${i - 1}&sortBy=${param.sortBy}&status=${param.status}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${page.number >= page.totalPages-1}">
                                <li class="page-item disabled">
                                    <span class="page-link"><fmt:message key="next"/></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link"
                                       href="?page=${page.number + 1}&sortBy=${param.sortBy}&status=${param.status}"><fmt:message
                                            key="next"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </c:if>
    </div>
</div>
<div class="container justify-content-center  ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table">
                        <c:set var="counter" value="${(page.number )*page.size + 1}"/>
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><a
                                    href="?sortBy=${param.sortBy == 'requestDate'? 'requestDateDesc':'requestDate' }&status=${param.status}"
                                    style="color: black"><fmt:message key="requestDate"/></a></th>
                            <th scope="col"><a
                                    href="?sortBy=${param.sortBy == 'receivingDate'? 'receivingDateDesc':'receivingDate' }&status=${param.status}"
                                    style="color: black"><fmt:message key="receivingDate"/></a></th>
                            <th scope="col"><a
                                    href="?sortBy=${param.sortBy == 'cityFrom.name'? 'cityFrom.nameDesc':'cityFrom.name' }&status=${param.status}"
                                    style="color: black"><fmt:message key="cityFrom"/></a></th>
                            <th scope="col"><a
                                    href="?sortBy=${param.sortBy == 'cityTo.name'? 'cityTo.nameDesc':'cityTo.name' }&status=${param.status}"
                                    style="color: black"><fmt:message key="cityTo"/></a></th>
                            <th scope="col"><fmt:message key="status"/></th>
                            <th scope="col"><a
                                    href="?sortBy=${param.sortBy == 'type'? 'typeDesc':'type' }&status=${param.status}"
                                    style="color: black"><fmt:message key="type"/></a></th>
                            <th scope="col"><fmt:message key="length"/></th>
                            <th scope="col"><fmt:message key="width"/></th>
                            <th scope="col"><fmt:message key="height"/></th>
                            <th scope="col"><fmt:message key="weight"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="client" items="${page.content}">
                        <tr>
                            <td>${counter}</td>
                            <c:set var="counter" value="${counter + 1}"/>
                            <td>${client.requestDate}
                            </td>
                            <td>${client.receivingDate}</td>
                            <td>${locale == 'uk' ? client.cityFrom.nameUk : client.cityFrom.name}</td>
                            <td>${locale == 'uk' ? client.cityTo.nameUk : client.cityTo.name}</td>
                            <td><fmt:message key="${client.status}"/></td>
                            <td>${client.parcel.type}</td>
                            <td>${client.parcel.length} <fmt:message key="mm"/></td>
                            <td>${client.parcel.width} <fmt:message key="mm"/></td>
                            <td>${client.parcel.height} <fmt:message key="mm"/></td>
                            <td>${client.parcel.weight} <fmt:message key="kg"/></td>
                        </tr>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
</footer>
</body>
</html>



