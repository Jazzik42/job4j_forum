<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<form:form action="save" modelAttribute="post">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Название:
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>Содержание:
                <form:input path="description" />
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>