<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale/resources"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>SingUp</title>
    <jsp:include page="common/windowstyle.jsp"/>
</head>
<body class="text-center" style="background-color: black">
<jsp:include page="common/header2.jsp"/>
<h2 class="display-3" style="color: #000000; background-color: rgba(255,238,231,0.87)"><fmt:message key="registration"/></h2>
    <div class="container justify-content-center w-50 ">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-1 g-3">
            <div class="col ">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h3 class="display-5"><fmt:message key="enterYourData"/></h3>
                        <form:form modelAttribute="userForm" method="post"  action="${pageContext.request.contextPath}/signUp/up">
                            <div class="mb-2 ">
                                <label  class="form-label"><fmt:message key="userName"/> </label>
                                <form:input type="text" path="login" class="form-control" name="login"
                                            value="${param.login}"  />
                            </div>
                            <div class="mb-2 ">
                                <label  class="form-label"><fmt:message key="email" /></label>
                                <form:input type="text" class="form-control" name="email" value="${param.email}"  path="email"/>
                            </div>
                            <div class="mb-2 ">
                                <label  class="form-label"><fmt:message key="firstName"/> </label>
                                <form:input type="text" class="form-control" name="firstName" value="${param.firstName}" path="firstName" />
                            </div>
                            <div class="mb-2 ">
                                <label  class="form-label"><fmt:message key="lastName"/></label>
                                <form:input type="text" class="form-control" name="lastName" value="${param.lastName}" path="lastName" />
                            </div>
                            <div class="mb-2">
                                <label for="exampleInputPassword1" class="form-label"><fmt:message key="password"/> </label>
                                <form:input type="password" class="form-control" id="exampleInputPassword1" name="password" path="password" />
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
                            </c:if>
                            <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>