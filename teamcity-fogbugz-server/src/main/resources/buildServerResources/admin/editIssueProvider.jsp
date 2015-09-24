<%@ include file="/include.jsp"%>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>

<div>
  <table class="editProviderTable">
    <c:if test="${showType}">
      <tr>
        <th><label class="shortLabel">Connection Type:</label></th>
        <td>FogBugz</td>
      </tr>
    </c:if>
    <tr>
      <th><label for="name" class="shortLabel">Display Name: <l:star/></label></th>
      <td>
        <props:textProperty name="name" maxlength="100"/>
        <span id="error_name" class="error"></span>
      </td>
    </tr>
    <tr>
      <th><label for="host" class="shortLabel">Server URL: <l:star/></label></th>
      <td>
        <props:textProperty name="host" maxlength="100"/>
        <span id="error_host" class="error"></span>
      </td>
    </tr>
    <tr>
      <th><label for="username" class="shortLabel">Username:</label></th>
      <td>
        <props:textProperty name="username" maxlength="100"/>
        <span id="error_username" class="error"></span>
      </td>
    </tr>
    <tr>
      <th><label for="secure:password" class="shortLabel">Password:</label></th>
      <td>
        <props:passwordProperty name="secure:password" maxlength="100"/>
        <span id="error_secure:password" class="error"></span>
      </td>
    </tr>
    <tr>
      <th><label for="pattern" class="shortLabel">Issue ID Pattern: <l:star/></label></th>
      <td>
        <props:textProperty name="pattern" maxlength="100"/>
        <span id="error_pattern" class="error"></span>
        <span class="fieldExplanation">Use the regex syntax, e.g. "BugzID: (\d+)"<bs:help file="Integrating+TeamCity+with+Issue+Tracker"/></span>
      </td>
    </tr>
  </table>
</div>
