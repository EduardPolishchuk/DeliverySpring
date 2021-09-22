<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>

<html>
<head>
    <title>ClientList</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>

</head>
<body style="background-color: black">
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message key="clientList"/></h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <c:set var="counter" value="1"/>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"># </th>
                            <th scope="col"><fmt:message key="userName"/> </th>
                            <th scope="col"><fmt:message key="email"/></th>
                            <th scope="col"><fmt:message key="firstName"/></th>
                            <th scope="col"><fmt:message key="lastName"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${userList}">
                        <tr>
                     <td>${counter}</td>
                            <c:set var="counter" value="${counter + 1}"/>
                            <td><strong>${order.login}</strong></td>
                            <td>${order.email}</td>
                            <td>${order.firstName}</td>
                            <td>${order.lastName}</td>
                        </tr>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
