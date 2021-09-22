<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom_tag.tld" prefix="custom" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <title>MyOrders</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message key="myOrders"/> </h2>
<div class="row " style="align-self: center">
    <div class="col">
        <h5 class="display-8 text-center" style="color: #000102"><fmt:message key="status"/></h5>
        <form>
            <div class="btn-group justify-content-center" role="group">
                <input type="submit" class="btn-check active" name="status" value="${param.status eq 1? '': 1}"
                       id="btnradio1"
                       autocomplete="off">
                <label class="btn btn-primary ${param.status == '1'? 'active':''}" for="btnradio1"><fmt:message
                        key="WAITING_FOR_CONFIRM"/></label>

                <input type="submit" class="btn-check active" name="status" value="${param.status eq 2? '': 2}"
                       id="btnradio2"
                       autocomplete="off">
                <label class="btn btn-primary ${param.status == '2'? 'active':''}" for="btnradio2"><fmt:message
                        key="WAITING_FOR_PAYMENT"/></label>

                <input type="submit" class="btn-check" name="status" value="${param.status eq 3? '': 3}"
                       id="btnradio3" autocomplete="off">
                <label class="btn btn-primary ${param.status == '3'? 'active':''}" for="btnradio3"><fmt:message
                        key="PARCEL_DELIVERY"/> </label>

                <input type="submit" class="btn-check" name="status" value="${param.status eq 4? '': 4}"
                       id="btnradio4" autocomplete="off">
                <label class="btn btn-primary ${param.status == '4'? 'active':''}" for="btnradio4"><fmt:message
                        key="DELIVERED"/></label>
            </div>
        </form>
        <c:if test="${noOfPages > 1}">
            <div>
                <nav aria-label="...">
                    <ul class="pagination justify-content-center">
                        <c:choose>
                            <c:when test="${currentPage <= 1}">
                                <li class="page-item disabled">
                                    <span class="page-link"><fmt:message key="previous"/></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link"
                                       href="?page=${currentPage - 1}&sortBy=${sortBy}&status=${param.status}"><fmt:message key="previous"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item active" aria-current="page">
                                        <span class="page-link">${i}</span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link"
                                                             href="?page=${i}&sortBy=${sortBy}&status=${param.status}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${currentPage >= noOfPages}">
                                <li class="page-item disabled">
                                    <span class="page-link"><fmt:message key="next"/></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="?page=${currentPage + 1}&sortBy=${sortBy}&status=${param.status}"><fmt:message key="next"/></a>
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
                        <c:set var="counter" value="${(currentPage - 1)*5 + 1}"/>
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'requestDate'? 'requestDateDesc':'requestDate' }&status=${param.status}"
                                               style="color: black"><fmt:message key="requestDate"/></a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'receivingDate'? 'receivingDateDesc':'receivingDate' }&status=${param.status}"
                                               style="color: black"><fmt:message key="receivingDate"/></a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'cityFrom'? 'cityFromDesc':'cityFrom' }&status=${param.status}"
                                               style="color: black"><fmt:message key="cityFrom"/></a></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'cityTo'? 'cityToDesc':'cityTo' }&status=${param.status}"
                                               style="color: black"><fmt:message key="cityTo"/></a></th>
                            <th scope="col"><fmt:message key="status"/></th>
                            <th scope="col"><a href="?sortBy=${param.sortBy == 'type'? 'typeDesc':'type' }&status=${param.status}"
                                               style="color: black"><fmt:message key="type"/></a></th>
                            <th scope="col"><fmt:message key="length"/></th>
                            <th scope="col"><fmt:message key="width"/></th>
                            <th scope="col"><fmt:message key="height"/></th>
                            <th scope="col"><fmt:message key="weight"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orderList}">
                        <tr>
                            <td>${counter}</td>
                            <c:set var="counter" value="${counter + 1}"/>
                            <td><custom:formatDate value="${order.requestDate}"
                                                   pattern="${locale eq 'uk' ? 'dd/MM/yyyy' : 'yyyy/MM/dd'}"/>
                            </td>
                            <td>${order.receivingDate}</td>
                            <td>${locale == 'uk' ? order.cityFrom.nameUk : order.cityFrom.name}</td>
                            <td>${locale == 'uk' ? order.cityTo.nameUk : order.cityTo.name}</td>
                            <td><fmt:message key="${order.status}"/></td>
                            <td>${order.parcel.type}</td>
                            <td>${order.parcel.length} <fmt:message key="mm"/></td>
                            <td>${order.parcel.width} <fmt:message key="mm"/></td>
                            <td>${order.parcel.height} <fmt:message key="mm"/></td>
                            <td>${order.parcel.weight} <fmt:message key="kg"/></td>
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



