<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8" />
    <title>login page</title>
</head>
<body>
    <form action="/login" method="post">
        <table align="center">
            <tr><td colspan="2" align="center"><h2 class="form-heading">Sign in</h2></td></tr>
            <tr>
                <td>username: </td>
                <td><input name="username" type="text" autofocus="true"/></td>
            </tr>
            <tr>
                <td>password: </td>
                <td><input name="password" type="password"/></td>
            </tr>
            <tr>
                <td colspan="2">
                <#if SPRING_SECURITY_LAST_EXCEPTION?exists >
                    <div style="text-align: left;color: red;font-size: 13px;">
                    ${SPRING_SECURITY_LAST_EXCEPTION.message?default('')}
                    </div>
                </#if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
                <td><button type="submit">login</button></td>
            </tr>
        </table>
    </form>
</body>
</html>