<%@ include file="common/header.jspf" %>
        <%@ include file="common/navigation.jspf" %>
        <div class="container">
            <hr>
            <h1>Your todos</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Completed</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.username}</td>
                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.completed}</td>
                            <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
                            <td><a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="add-todo" class="btn btn-success">add todo</a>
            <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
            <script src="webjars/jquery/3.6.2/jquery.min.js"></script>
        </div>
    </body>
</html>