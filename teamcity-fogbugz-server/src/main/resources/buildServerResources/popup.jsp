<%@ include file="/include.jsp" %>
<jsp:useBean id="issue" scope="request" type="jetbrains.buildServer.issueTracker.Issue" />
<bs:issueDetailsPopup issue="${issue}"
                      popupClass="fogbugz">
</bs:issueDetailsPopup>
