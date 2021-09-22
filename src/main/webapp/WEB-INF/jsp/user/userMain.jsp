<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <title>UserMain</title>
    <jsp:include page="/WEB-INF/common/windowstyle.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message key="deliveryService"/></h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">

                    <form id="form2" action="${pageContext.request.contextPath}/user/userMain">
                        <h4 class="display-6 text-center" style="align-content: center"><fmt:message key="makeOrder"/> </h4>
                        <h5 class="display-7" style="align-content: center"><fmt:message key="sender" /></h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="firstName"/></label>
                                <input type="number" min="1" class="form-control"
                                       placeholder="${userProfile.firstName}" disabled>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="lastName"/></label>
                                <input type="number" min="1" class="form-control"
                                       placeholder="${userProfile.lastName}" disabled>
                            </div>
                        </div>
                        <h5 class="display-7" style="align-content: center"><fmt:message key="parcelParams"/></h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="length"/>, <fmt:message key="mm"/></label>
                                <input name="length" value="${param.length}" type="number" min="1" class="form-control"

                                       aria-label="First name" required>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="width"/>, <fmt:message key="mm"/> </label>
                                <input name="width" value="${param.width}" type="number" min="1" class="form-control"

                                       aria-label="Last name" required>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="height"/>, <fmt:message key="mm"/> </label>
                                <input name="height" value="${param.height}" type="number" min="1" class="form-control"

                                       aria-label="Last name" required>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="weight"/>, <fmt:message key="kg"/> </label>
                                <input name="weight" value="${param.weight}" type="number" min="0,1"
                                       class="form-control "
                                       aria-label="Last name" required>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="type"/></label>
                                <input name="type" value="${param.type}" type="text"
                                       class="form-control "
                                        >
                            </div>
                        </div>
                        <h5 class="display-7" style="align-content: center"><fmt:message key="route"/> </h5>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="cityFrom"/> </label>
                                <select class="form-select hide-icon" name="cityFrom"
                                        aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}" ${city.id == cityFrom ? 'selected':''}>${locale == 'uk'?
                                                city.nameUk : city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="cityTo"/> </label>
                                <select class="form-select" name="cityTo" aria-label="Default select example">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}" ${city.id == param.cityTo ? 'selected':''}>${locale == 'uk'?
                                                city.nameUk : city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <hr>
                    </form>
                    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                        <button form="form2" type="submit" name="action" value="calculate" class="btn btn-primary"><fmt:message key="calculate"/></button>
                        <button form="form2" type="submit" name="action" value="makeOrder" class="btn btn-primary">
                            <fmt:message key="order"/>
                        </button>
                        <a class="btn btn-primary" data-bs-toggle="collapse" href="#multiCollapseExample1" role="button"
                           aria-expanded="false" aria-controls="multiCollapseExample1"><fmt:message key="tariff"/></a>
                    </div>
                    <c:if test="${not empty calculatedValue}">
                        <h3 class="display-4">${calculatedValue} <fmt:message key="uah"/></h3>
                        ${calculatedValue = null}
                    </c:if>
                    <div class="row">
                        <div class="col">
                            <div class="collapse multi-collapse" id="multiCollapseExample1">
                                <div class="card card-body">
                                    <fmt:message key="distance"/>: ${tariff.uahPerKilometerDistance} <fmt:message key="uah"/>/<fmt:message key="km"/> ,
                                    <fmt:message key="width"/>: ${tariff.uahPerMillimeterWidth} <fmt:message key="uah"/>/<fmt:message key="mm"/> ,
                                    <fmt:message key="height"/>: ${tariff.uahPerMillimeterHeight} <fmt:message key="uah"/>/<fmt:message key="mm"/>,
                                    <fmt:message key="length"/>: ${tariff.uahPerMillimeterLength} <fmt:message key="uah"/>/<fmt:message key="mm"/>,
                                    <fmt:message key="weight"/>: ${tariff.uahPerKilogramWeight} <fmt:message key="uah"/>/<fmt:message key="kg"/>
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

<jsp:include page="/WEB-INF/common/footer.jsp"/>
</body>

</html>