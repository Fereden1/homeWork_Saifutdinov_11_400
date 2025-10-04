<#include "base.ftl">

<#macro title>Login page</#macro>

<#macro content>
    <h3>Login page</h3>

    <form method="post" action="login">
        Login: <input type="text" name="login" placeholder="login"><br>
        Password: <input type="password" name="password" placeholder="password"><br>
        <input type="submit" value="Log in">
    </form>


</#macro>