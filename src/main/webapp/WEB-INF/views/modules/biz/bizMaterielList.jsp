<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物资管理</title>
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
		<li class="active"><a href="${ctx}/biz/bizMateriel/">物资列表</a></li>
		<shiro:hasPermission name="biz:bizMateriel:edit"><li><a href="${ctx}/biz/bizMateriel/form">物资添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMateriel" action="${ctx}/biz/bizMateriel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>物资名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>物资描述：</label>
				<form:input path="description" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>物资来源地：</label>
				<form:input path="productArea" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>物资类：</label>
				<form:select path="supplyOrigin" class="input-xlarge">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('supply_origin_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物资名称</th>
				<th>物资描述</th>
				<th>物资来源地</th>
				<th>物资类型</th>
				<th>物资利润</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="biz:bizMateriel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMateriel">
			<tr>
				<td><a href="${ctx}/biz/bizMateriel/form?id=${bizMateriel.id}">
					${bizMateriel.name}
				</a></td>
				<td>
					${bizMateriel.description}
				</td>
				<td>
					${fns:getAreaNameById(bizMateriel.productArea)}
				</td>
				<td>
					${fns:getDictLabel(bizMateriel.supplyOrigin, 'supply_origin_type', '')}
				</td>
				<td>
					${bizMateriel.price}
				</td>
				<td>
					${fns:getUserById(bizMateriel.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${bizMateriel.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getUserById(bizMateriel.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${bizMateriel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizMateriel.remarks}
				</td>
				<shiro:hasPermission name="biz:bizMateriel:edit"><td>
    				<a href="${ctx}/biz/bizMateriel/form?id=${bizMateriel.id}">修改</a>
					<a href="${ctx}/biz/bizMateriel/delete?id=${bizMateriel.id}" onclick="return confirmx('确认要删除该物料吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>