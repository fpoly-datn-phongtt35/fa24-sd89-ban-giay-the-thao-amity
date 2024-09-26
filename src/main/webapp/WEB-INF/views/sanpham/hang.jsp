<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Hang</h3>

<hr>
<form:form method="post" action="/api/v1/admin/company/create" modelAttribute="hang">
    <p> Ma :
    <form:input path="ma"/>
    </p>
    <p> Ten :
        <form:input path="ten"/>
    </p>
    <p> trang thai :
        <form:input path="trangThai"/>
    </p>
    <button  >Them</button>
</form:form>
<hr>
<table border="1" width="100%">
    <tr>
        <th>###</th>
        <th>Ma</th>
        <th>Ten</th>
        <th>Trang thai</th>
        <th>Thao tac</th>
    </tr>
    <c:forEach items="${list}" var="h" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${h.ma}</td>
            <td>${h.ten}</td>
            <td>${h.trangThai}</td>
            <td>
                <a href="/api/v1/admin/company/update/${h.id}">An</a>
            </td>
        </tr>
    </c:forEach>

</table>

