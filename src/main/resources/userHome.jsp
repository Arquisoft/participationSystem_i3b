<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>ParticipationSystem_i3b</title>
</head>
<body>
<h1>User home</h1>
    <form th:action="@{/send}" th:object="${proposal}" method="POST">
        <label for="title">Title of the proposal: </label>
        <input type="text" id="title" th:field="*{title}" />
        <label for="content">Content: </label>
        <input type="text" id="content" th:field="*{content}" />
        <select id="category" th:field="*{category}" >
            <option selected value="base">Select a category</option>
            <c:forEach var="cat" items="${configuration.categories}" varStatus="i">
                <option value="${i}">$i</option>
            </c:forEach>
        </select>
        <input type="submit" value="Create" />
    </form>
    <br/>
    <br/>
    <table id="proposalsTable">
        <tbody>
        		<tr>
        			<th class="text-left">Title</th>
        			<th class="text-left">Comments</th>
        			<th class="text-left">Created</th>
        			<th class="text-left">Planned</th>
        			<th class="text-left"></th>
        		</tr>
        		<c:forEach var="entry" items="${listaTareasPorCat}" varStatus="i">
        		</c:forEach>
        <tbody/>
    </table>
</body>
</html>