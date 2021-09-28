<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
<c:set var="vari" value="${not empty param.edit ? null : 'disabled'}" scope="session"/>

<html>
<head>
    <title>MyProfile</title>
    <jsp:include page="../common/windowstyle.jsp"/>
</head>
<body style="background-color: black">
<jsp:include page="../common/header2.jsp"/>
<h2 class="display-3 text-center" style="color: #000102; background-color: rgba(255,238,231,0.87)"><fmt:message
        key="myProfile"/></h2>
<div class=" container justify-content-center w-50 ">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
        <div class="col ">
            <div class="card shadow-sm">
                <div class="card-body">
                    <form:form modelAttribute="userForm" method="post"
                               action="${pageContext.request.contextPath}/user/profile/update">
                        <div class="mb-3 ">
                            <label class="form-label"><fmt:message key="email"/></label>
                            <form:input type="text" class="form-control" name="email" value="${userForm.email}"
                                        path="email"
                                        disabled="${empty param.edit}"/>
                        </div>
                        <div class="mb-3 ">
                            <label class="form-label"><fmt:message key="firstName"/></label>
                            <form:input type="text" class="form-control" name="firstName" value="${userForm.firstName}"
                                        path="firstName"
                                        disabled="${empty param.edit}"/>
                        </div>
                        <div class="mb-3 ">
                            <label class="form-label"><fmt:message key="lastName"/></label>
                            <form:input type="text" class="form-control" name="lastName" value="${userForm.lastName}"
                                        path="lastName"
                                        disabled="${empty param.edit}"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><fmt:message key="password"/></label>
                            <form:input type="password" class="form-control" name="password"
                                        path="password" disabled="${empty param.edit}"/>
                        </div>
                        <c:if test="${error != null}">
                            <c:choose>
                                <c:when test="${error eq 'passwordInvalid' || error eq 'loginInvalid'}">
                                    <div class="alert alert-danger  p-1 " role="alert">
                                        <fmt:message key="${error}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-danger  p-1 " role="alert">
                                        <fmt:message key="incorrectInput"/>: "${error}"
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            ${pageContext.session.removeAttribute('error')}
                        </c:if>
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
<jsp:include page="../common/footer.jsp"/>
</body>
</html>