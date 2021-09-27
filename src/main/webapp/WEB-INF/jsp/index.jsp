<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>

<html>
<head>
    <title>Delivery Service</title>
    <jsp:include page="common/windowstyle.jsp"/>
</head>
<body>


<jsp:include page="common/windowstyle.jsp"/>
<jsp:include page="common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message
        key="deliveryService"/></h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form:form  id="form2" method="get" modelAttribute="orderForm"
                               action="${pageContext.request.contextPath}/calculate">
                        <h4 class="display-5 text-center" style="align-content: center"><fmt:message
                                key="calculateTheCost"/></h4>
                        <hr>
                        <h5 class="display-7" style="align-content: center"><fmt:message key="parcelParams"/></h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="length"/>, <fmt:message key="mm"/></label>
                                <form:input class="form-control" path="parcel.length" type="number" min="1" max="1000"
                                            value="${parcel.length}"/>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="width"/>, <fmt:message key="mm"/></label>
                                <form:input class="form-control" path="parcel.width" type="number" min="1" max="1000"
                                            value="${parcel.width}"/>

                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="height"/>, <fmt:message key="mm"/></label>
                                <form:input class="form-control" path="parcel.height" type="number" min="1" max="2000"
                                            value="${parcel.height}"/>

                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="weight"/>, <fmt:message key="kg"/></label>
                                <form:input class="form-control" path="parcel.weight" type="number" min="1" max="200"
                                            value="${parcel.weight}"/>
                            </div>
                        </div>
                        <h5 class="display-7" style="align-content: center"><fmt:message key="route"/></h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="cityFrom"/> </label>
                                <select class="form-select hide-icon" name="cityFrom"
                                        aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option  value="${city.id}" ${city.id == orderForm.cityFrom.id ? 'selected':''}>${locale == 'uk'?
                                                city.nameUk : city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="cityTo"/> </label>
                                <select class="form-select" name="cityTo" aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}" ${city.id == orderForm.cityTo.id ? 'selected':''}>
                                                ${locale == 'uk'? city.nameUk : city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <hr>
                    </form:form>

                    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                        <button form="form2" type="submit" class="btn btn-primary"><fmt:message
                                key="calculate"/></button>
                        <a class="btn btn-primary" data-bs-toggle="collapse" href="#multiCollapseExample1" role="button"
                           aria-expanded="false" aria-controls="multiCollapseExample1"><fmt:message key="tariff"/> </a>
                    </div>

                    <c:choose>
                        <c:when test="${error != null}">
                            <h3 class="display-4"><fmt:message key="${error}"/></h3>
                            ${error = null}
                        </c:when>
                        <c:otherwise>
                            <c:if test="${calculatedValue != null}">
                                <h3 class="display-4">${calculatedValue} <fmt:message key="uah"/></h3>
                                ${calculatedValue = null}
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                    <div class="row">
                        <div class="col">
                            <div class="collapse multi-collapse" id="multiCollapseExample1">
                                <div class="card card-body">
                                    <fmt:message key="parcelParams"/> (<fmt:message
                                        key="width"/>: ${tariff.uahPerMillimeterWidth} <fmt:message
                                        key="uah"/>/<fmt:message key="mm"/> ,
                                    <fmt:message key="height"/>: ${tariff.uahPerMillimeterHeight} <fmt:message
                                        key="uah"/>/<fmt:message key="mm"/>,
                                    <fmt:message key="length"/>: ${tariff.uahPerMillimeterLength} <fmt:message
                                        key="uah"/>/<fmt:message key="mm"/>,
                                    <fmt:message key="weight"/>: ${tariff.uahPerKilogramWeight} <fmt:message key="uah"/>/<fmt:message
                                        key="kg"/>),
                                    <fmt:message key="distance"/>: ${tariff.uahPerKilometerDistance} <fmt:message
                                        key="uah"/>/<fmt:message key="km"/>
                                    + <fmt:message key="additional"/>: ${tariff.additional} <fmt:message key="uah"/>.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>

</html>