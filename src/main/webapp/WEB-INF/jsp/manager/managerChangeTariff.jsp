<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
<c:set var="vari" value="${not empty param.edit ? null : 'disabled'}" scope="session"/>
<html>
<head>
    <title>OrderView</title>
    <jsp:include page="../common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="../common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message key="tariff"/></h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form:form modelAttribute="tariff" method="post"  id="form2"
                               action="${pageContext.request.contextPath}/manager/tariff/change">
                        <form:hidden path="id"/>
                        <hr>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label">
                                    <fmt:message key="weight"/>,
                                    <fmt:message key="uah"/>/<fmt:message key="kg"/>
                                </label>
                                <form:input path="uahPerKilogramWeight" type="number" min="0" step = "0.1" class="form-control"
                                            value="${tariff.uahPerKilogramWeight}"  disabled="${empty param.edit}"/>
                            </div>
                            <div class="col">
                                <label class="form-label">
                                    <fmt:message key="distance"/>,  <fmt:message key="uah"/>/<fmt:message key="km"/>
                                </label>
                                <form:input path="uahPerKilometerDistance" type="number" min="0" step = "0.1" class="form-control"
                                            value="${tariff.uahPerKilometerDistance}"  disabled="${empty param.edit}"/>
                            </div>
                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="length"/>, <fmt:message key="uah"/>/<fmt:message key="mm"/>
                                </label>
                                <form:input path="uahPerMillimeterLength" type="number" min="0" step = "0.1" class="form-control"
                                            value="${tariff.uahPerMillimeterLength}"  disabled="${empty param.edit}"/>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="height"/>, <fmt:message key="uah"/>/<fmt:message key="mm"/>
                                </label>
                                <form:input path="uahPerMillimeterHeight" type="number" min="0" step = "0.1" class="form-control"
                                            value="${tariff.uahPerMillimeterHeight}"  disabled="${empty param.edit}"/>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="width"/>, <fmt:message key="uah"/>/<fmt:message key="mm"/>

                                <form:input path="uahPerMillimeterWidth" type="number" min="0" step = "0.1" class="form-control"
                                            value="${tariff.uahPerMillimeterWidth}"  disabled="${empty param.edit}"/>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="additional"/>, <fmt:message key="uah"/></label>
                                <form:input path="additional" type="number" min="0" step = "0.1" class="form-control"
                                            value="${tariff.additional}"  disabled="${empty param.edit}"/>
                            </div>
                        </div>
                        <hr>
                        <button type="submit" class="btn btn-primary"  ${vari}><fmt:message key="update"/></button>
                    </form:form>
                    <form action="">
                        <button type="submit" class="btn btn-dark" name="edit" value="${vari != null  ? '1' : null}">
                            <fmt:message key="edit"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>



