<#include "base.ftl">

<#macro title>Incorrect password</#macro>

<#macro content>
    <h3>Incorrect password, try to log in again.</h3>

    <form method="get" action="login">
        <input type="submit" value="Log in page">
    </form>

    <form method="get" action="index">
        <input type="submit" value="Main page">
    </form>
</#macro>