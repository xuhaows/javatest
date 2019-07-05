<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="farm" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="farm" value ="${farm}"/>
<c:set var="language" value="<s:property value='#session.WW_TRANS_I18N_LOCALE'/>"/>
<input type="hidden" id="language" value ="${language}"/>
<input type="hidden" id="iFisheryPath" value ="http://120.76.23.18/iFishery_2"/>
<input type="hidden" id="iFisheryWeiXin"	value="http://www.kunyuwulian.com"/>
