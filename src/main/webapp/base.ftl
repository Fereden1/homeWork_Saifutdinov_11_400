<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
</head>

<body>
<div id="header">
    <h2> <strong>Garbage site</strong></h2>
    <table>
        <tr>
            <form method="get" action="index">
                <input type="submit" value="Main page">
            </form>
        </tr>
        <tr>
            <form method="get" action="login">
                <input type="submit" value="Login">
            </form>
        </tr>
        <tr>
            <form method="get" action="sign_up">
                <input type="submit" value="Sign up">
            </form>
        </tr>
        <tr>
            <form method="get" action="logout">
                <input type="submit" value="Log out">
            </form>
        </tr>
    </table>
</div>

<hr>

<div id="content">
    <div class="content">
        <@content></@content>
    </div>
</div>

</body>

</html>