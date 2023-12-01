<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物资出库管理</title>
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
		<li class="active"><a href="${ctx}/biz/bizDrawMateriel/">物资出库详情</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizDrawMateriel" action="${ctx}/biz/bizDrawMateriel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>物资：</label>
				<form:select path="bizMateriel.id" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${materielList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>出库批次：</label>
				<form:input path="drawBatch" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>出库时间：</label>
				<input name="drawTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDrawMateriel.drawTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物料</th>
				<th>出库批次</th>
				<th>出库数量</th>
				<th>出库时间</th>
				<th>操作员</th>
				<th>领料说明</th>
				<shiro:hasPermission name="biz:bizDrawMateriel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizDrawMateriel">
			<tr>
				<td>
					${bizDrawMateriel.bizMateriel.name}
				</td>
				<td>
					${bizDrawMateriel.drawBatch}
				</td>
				<td>
					${bizDrawMateriel.drawNumber}
				</td>
				<td>
					<fmt:formatDate value="${bizDrawMateriel.drawTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getUserById(bizDrawMateriel.staffId).name}
				</td>
				<td>
					${bizDrawMateriel.drawExplain}
				</td>
				<shiro:hasPermission name="biz:bizDrawMateriel:edit"><td>
					<a href="${ctx}/biz/bizDrawMateriel/delete?id=${bizDrawMateriel.id}" onclick="return confirmx('确认要删除该领料吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>