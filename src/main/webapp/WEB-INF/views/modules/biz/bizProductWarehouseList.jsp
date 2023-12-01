<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品入库管理</title>
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
		<li class="active"><a href="${ctx}/biz/bizProductWarehouse/">产品库存查询</a></li>
		<shiro:hasPermission name="biz:bizProductWarehouse:edit"><li><a href="${ctx}/biz/bizProductWarehouse/form">产品入库</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProductWarehouse" action="${ctx}/biz/bizProductWarehouse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>仓库：</label>
				<form:select path="warehouseId" class="input-medium"  id="productionPlanStatus">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('warehouse')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>产品：</label>
				<form:select path="bizProduct.id" class="input-medium ">
					<form:option value="" label="请选择"/>
					<form:options items="${productList}" itemLabel="name" itemValue="id" htmlEscape="false" id="bizProductId"/>
				</form:select>
			</li>
			<li><label>产品编号：</label>
				<form:input path="productMark" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>入库时间：</label>
				<input name="inTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizProductWarehouse.inTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>仓库</th>
				<th>产品</th>
				<th>单价</th>
				<th>产品编号</th>
				<th>入库批次</th>
				<th>入库数量</th>
				<th>入库时间</th>
				<th>操作员</th>
				<th>备注信息</th>
				<shiro:hasPermission name="biz:bizProductWarehouse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProductWarehouse">
			<tr>
				<td>
					${fns:getDictLabel(bizProductWarehouse.warehouseId, 'warehouse', '')}
				</td>
				<td>
					${bizProductWarehouse.bizProduct.name}
				</td>
				<td>
					${bizProductWarehouse.price}
				</td>
				<td>
					${bizProductWarehouse.productMark}
				</td>
				<td>
					${bizProductWarehouse.inBatch}
				</td>
				<td>
					${bizProductWarehouse.inNumber}
				</td>
				<td>
					<fmt:formatDate value="${bizProductWarehouse.inTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getUserById(bizProductWarehouse.staffId).name}
				</td>
				<td>
					${bizProductWarehouse.remarks}
				</td>
				<shiro:hasPermission name="biz:bizProductWarehouse:edit"><td>
    				<a href="${ctx}/biz/bizProductWarehouse/form?id=${bizProductWarehouse.id}">修改</a>
					<a href="${ctx}/biz/bizProductWarehouse/delete?id=${bizProductWarehouse.id}" onclick="return confirmx('确认要删除该产品入库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>