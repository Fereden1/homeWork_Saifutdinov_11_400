<#include "base.ftl">

<#macro title>Main page</#macro>

<#macro content>
    <h2>Session tracker</h2>

    <#if sessionUser?has_content>
        <h3>
            Hello, ${sessionUser}!
            <br>
            Session ID = ${sessionId!}
            <br>
            Cookie user = ${cookieUser!}
        </h3>
    <#else>
        <h3>No session user. Please log in.</h3>
        <a href="login.ftl">Go to login</a>
    </#if>


</#macro>