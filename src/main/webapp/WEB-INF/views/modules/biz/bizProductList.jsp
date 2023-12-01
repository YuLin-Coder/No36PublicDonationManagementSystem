<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公益箱管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/biz/bizProduct/">公益箱列表</a></li>
		<shiro:hasPermission name="biz:bizProduct:edit"><li><a href="${ctx}/biz/bizProduct/form">公益箱添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProduct" action="${ctx}/biz/bizProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公益箱名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>公益箱编号：</label>
				<form:input path="model" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公益箱名称</th>
				<th>公益箱编号</th>
				<th>公益箱地址</th>
				<th>公益箱简介</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="biz:bizProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProduct">
			<tr>
				<td><a href="${ctx}/biz/bizProduct/form?id=${bizProduct.id}">
					${bizProduct.name}
				</a></td>
				<td>
					${bizProduct.model}
				</td>
				<td>
					${fns:getAreaNameById(bizProduct.area)}
				</td>
				<td>
					${bizProduct.introduce}
				</td>
				<td>
					<fmt:formatDate value="${bizProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizProduct.remarks}
				</td>
				<shiro:hasPermission name="biz:bizProduct:edit"><td>
    				<a href="${ctx}/biz/bizProduct/form?id=${bizProduct.id}">修改</a>
					<a href="${ctx}/biz/bizProduct/delete?id=${bizProduct.id}" onclick="return confirmx('确认要删除该产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>