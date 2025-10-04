<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    <h2>Users table</h2>

    <#if users?has_content>
        Таблица рекордов:
        <br>
        Логин Имя
        <br>
        <#list users as u>
            ${u.login} ${u.name}
            <br>
        </#list>
    </#if>

</#macro>