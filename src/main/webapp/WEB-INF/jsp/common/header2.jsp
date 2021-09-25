<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>

<header class="p-3 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a href="${pageContext.request.contextPath}/"
                       class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                        <svg class="bi me-2" width="40" height="60" role="img" aria-label="Bootstrap">
                            <use xlink:href="${pageContext.request.contextPath}/"></use>
                        </svg>
                    </a>
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

                        <sec:authorize access="hasRole('USER')">
                            <li><a href="${pageContext.request.contextPath}/"
                                   class="nav-link px-2 text-primary"><strong><fmt:message
                                    key="home"/> </strong></a></li>
                            <li><a href="${pageContext.request.contextPath}/user/orders"
                                   class="nav-link px-2 text-white"><fmt:message key="myOrders"/> </a></li>
                            <li><a href="${pageContext.request.contextPath}/user/receipts"
                                   class="nav-link px-2 text-white"><fmt:message key="myReceipts"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/user/profile"
                                   class="nav-link px-2 text-white"><fmt:message key="myProfile"/></a></li>
                        </sec:authorize>

                        <sec:authorize access="hasRole('MANAGER')">
                            <li><a href="${pageContext.request.contextPath}/manager/managerOrderList"
                                   class="nav-link px-2 text-white"><fmt:message key="orderList"/> </a></li>
                            <li><a href="${pageContext.request.contextPath}/manager/managerClientList"
                                   class="nav-link px-2 text-white"><fmt:message key="clientList"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/manager/managerTariffView"
                                   class="nav-link px-2 text-white"><fmt:message key="changeTariff"/> </a></li>
                            <li><a href="${pageContext.request.contextPath}/manager/managerAddCity"
                                   class="nav-link px-2 text-white"><fmt:message key="addCity"/></a></li>
                        </sec:authorize>

                    </ul>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasRole('USER')">
                            <h5 class="display-6 col-lg-auto  mb-lg-0 me-lg-2"
                                style="color: aliceblue">${userProfile.login}
                            </h5>
                            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-0">
                                <button type="button" class="btn btn-link "
                                        data-bs-toggle="modal" data-bs-target="#exampleModal2"
                                >${userProfile.balance} <fmt:message key="uah"/></button>
                            </form>
                        </sec:authorize>
                        <sec:authorize access="hasRole('MANAGER')">
                            <h6 class="display-11" style="color: aliceblue"><fmt:message key="manager"/></h6><br>
                            <h5 class="display-6 col-lg-auto  mb-lg-0 me-lg-2"
                                style="color: aliceblue">${userProfile.login}
                            </h5>
                        </sec:authorize>
                        <br>
                        <form action="${pageContext.request.contextPath}/logout"
                              class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-0">
                            <button type="submit" class="btn btn-outline-light me-2"><fmt:message
                                    key="logout"/></button>
                        </form>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <form method="get" action="${pageContext.request.contextPath}/login"
                              class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-0">
                            <button type="submit" class="btn btn-outline-light me-2"><fmt:message
                                    key="singIn"/></button>
                        </form>
                        <form method="get" action="${pageContext.request.contextPath}/signUp"
                              class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                            <button type="submit" class="btn btn-warning"><fmt:message key="singUp"/></button>
                        </form>
                    </sec:authorize>
                    <div class="text-end">
                        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                            <c:forEach var="item" items="${param}">
                                <c:if test="${item.key != 'language'}">
                                    <input type="hidden" name="${item.key}" value="${item.value}">
                                </c:if>
                            </c:forEach>
                            <button type="submit" class="btn btn-outline-light " name="language" value="en">
                                <strong><fmt:message key="en"/> </strong></button>
                            <button type="submit" class="btn btn-outline-light" name="language" value="uk">
                                <strong><fmt:message key="ukr"/></strong></button>
                        </form>
                    </div>
                </div>
            </div>
</header>

<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="changeBalance"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/Delivery/changeBalance" class="row g-3 needs-validation" novalidate>
                    <div class="mb-3">
                    </div>
                    <div class="mb-3">
                        <label for="validationCustom01" class="form-label"><fmt:message key="amount"/></label>
                        <input type="hidden" name="page" value="${pageContext.request.requestURI}">
                        <input type="number" min="1" class="form-control" id="validationCustom01" name="amount"
                               value="0"
                               required>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary"><fmt:message key="confirm"/></button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message
                                key="close"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
