<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>留言板列表</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
	<link href="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			location="${ctx}/guestbook/form?pageNo="+n+"&pageSize="+s;;
		}
	</script>
</head>
<body>
	<div style="padding:0 0 20px;">
		<h4>公共留言</h4>
		<ul>
			<c:forEach items="${page.list}" var="guestbook">
				<li>
					<h5>姓名: ${guestbook.name} &nbsp;时间：<fmt:formatDate value="${guestbook.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></h5>
					<div>内容：${guestbook.content}</div>
					<h6>回复人：${guestbook.reUser.name} 时间：<fmt:formatDate value="${guestbook.reDate}" pattern="yyyy-MM-dd HH:mm:ss"/></h6>
					<div>回复内容：${guestbook.reContent}</div>
				</li>
			</c:forEach>
			<c:if test="${fn:length(page.list) eq 0}">
				<li>暂时还没有人留言！</li>
			</c:if>
		</ul>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>