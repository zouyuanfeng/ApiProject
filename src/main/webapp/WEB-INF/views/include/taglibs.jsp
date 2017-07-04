<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--应用服务器上下文 \${ctx} -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--资源服务器上下文 \${res}-->
<c:set var="res" value="${pageContext.request.contextPath}/static" />
<script type="text/javascript">
    var $ctx= '${ctx}';
    var $res= '${res}';
</script>
<%@ page trimDirectiveWhitespaces="true" %>