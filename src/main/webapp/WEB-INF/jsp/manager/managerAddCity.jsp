<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <title>OrderView</title>
    <jsp:include page="../common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="../common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message
        key="addingCity"/></h2>
<div class="container justify-content-center w-75 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form:form method="post" modelAttribute="cityForm" id="form2"
                               action="${pageContext.request.contextPath}/manager/add_city">
                        <hr>
                        <div class="row g-3 mb-3">
                            <div class="col w-75">
                                <label class="form-label"><fmt:message key="name"/> </label>
                                <input type="text" class="form-control" name="name" value="${param.name}" required>
                            </div>
                            <div class="col w-75">
                                <label class="form-label"><fmt:message key="nameUk"/> </label>
                                <input type="text" class="form-control" name="nameUk" value="${param.nameUk}" required>
                            </div>

                        </div>
                        <c:if test="${nameError != null}">
                            <div class="alert alert-danger d-flex align-items-center" role="alert">
                                <div>
                                    <fmt:message key="nameError"/>${nameError}!
                                </div>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <div class="row g-3 mb-3">
                            <div class="col">
                                <label class="form-label"><fmt:message key="latitude"/> </label>
                                <div class="input-group mb-3 w-75">
                                    <input type="number" class="form-control" required value="${param.latDeg}" max="90"
                                           min="0" step="0.1" name="latDeg">
                                    <span class="input-group-text">°</span>
                                    <input type="number" class="form-control" required value="${param.latMin}" max="60"
                                           min="0" step="0.1" name="latMin">
                                    <span class="input-group-text">′</span>
                                    <input type="number" class="form-control" required value="${param.latSec}" max="60"
                                           min="0" step="0.1" name="latSec">
                                    <span class="input-group-text">″</span>
                                </div>
                            </div>
                            <div class="col">
                                <label class="form-label"><fmt:message key="longitude"/> </label>
                                <div class="input-group mb-3 w-75">
                                    <input type="number" class="form-control" value="${param.lngDeg}" max="1000"
                                           min="0" step="0.1" name="lngDeg" required aria-label="Username">
                                    <span class="input-group-text">°</span>
                                    <input type="number" class="form-control" value="${param.lngMin}" max="60"
                                           min="0" step="0.1" name="lngMin" required aria-label="Server">
                                    <span class="input-group-text">′</span>
                                    <input type="number" class="form-control" value="${param.lngSec}" max="60"
                                           min="0" step="0.1" name="lngSec" required aria-label="Server">
                                    <span class="input-group-text">″</span>
                                </div>
                            </div>
                            <c:if test="${coordinateError != null}">
                                <div class="alert alert-danger d-flex align-items-center" role="alert">
                                    <div>
                                        <fmt:message key="coordinateError"/>${coordinateError}!
                                    </div>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            <div class="row g-3 mb-3">
                                <div class="col">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="latParam"
                                               id="inlineRadio1" value="north" required
                                            ${param.latParam == 'north' ? 'checked' : ''}
                                        >
                                        <label class="form-check-label" for="inlineRadio1"><fmt:message
                                                key="latNorth"/></label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="latParam"
                                               id="inlineRadio2" value="south"
                                            ${param.latParam == 'south' ? 'checked' : ''}
                                        >
                                        <label class="form-check-label" for="inlineRadio2"><fmt:message
                                                key="latSouth"/> </label>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="lngParam"
                                               id="inlineRadio3" value="east"
                                               required ${param.lngParam == 'east' ? 'checked' : ''}>
                                        <label class="form-check-label" for="inlineRadio3"><fmt:message
                                                key="lngEast"/> </label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="lngParam"
                                               id="inlineRadio4"
                                               value="west" ${param.lngParam == 'west' ? 'checked' : ''}>
                                        <label class="form-check-label" for="inlineRadio4"><fmt:message
                                                key="lngWest"/> </label>
                                    </div>

                                </div>
                            </div>

                            <hr>
                        </div>
                    </form:form>
                    <button class="btn btn-primary" form="form2" type="submit" name="action" value="add">
                        <fmt:message key="submit"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>



