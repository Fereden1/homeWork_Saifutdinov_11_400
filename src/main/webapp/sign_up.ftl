<#include "base.ftl">

<#macro title>Sign up page</#macro>

<#macro content>
    <h3>Sign Up page</h3>

    <form method="post" action="sign_up">
        Login: <input type="text" name="login" placeholder="login"><br>
        Password: <input type="password" name="password" placeholder="password"><br>
        Name: <input type="text" name="name" placeholder="name"><br>
        Lastname: <input type="text" name="lastname" placeholder="lastname"><br>
        <input type="submit" value="Sign up">
    </form>

</#macro>