<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物资入库管理</title>
	<meta name="decorator" content="default"/>
	
	<link rel="stylesheet" href="${ctxStatic}/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${ctxStatic}/layui/css/modules/layer/layer.css" media="screen" type="text/css">
	<script type="text/javascript" src="${ctxStatic}/layui/lay2/modules/layer.js"></script>
	<script src="${ctxStatic}/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		function drawMateriel(id){
			layer.open({
    			type: 2,
    			title: ["领取物资操作",false],
    			shade: [0.1],
    			area: ['80%', '80%'],
    			shift: 2,
    			shadeClose: true,
    			content: ['${ctx}/biz/bizMaterielWarehouse/toDrawMateriel?id=' + id],
				end: function () {
					window.location.reload();
            	}
    		});
		}
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style type="text/css">
		a {
		    outline: none;
		    color: #157ab5;
		    text-decoration: none;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/biz/bizMaterielWarehouse/">物资库存查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterielWarehouse" action="${ctx}/biz/bizMaterielWarehouse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>仓库：</label>
				<form:select path="warehouseId" class="input-medium required"  id="productionPlanStatus">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('warehouse')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>物资类型：</label>
				<form:select path="bizMateriel.id" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${materielList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>入库批次：</label>
				<form:input path="inBatch" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>入库时间：</label>
				<input name="inTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterielWarehouse.inTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>物资类型</th>
				<th>入库批次</th>
				<th>入库数量</th>
				<th>入库时间</th>
				<th>操作员</th>
				<th>备注信息</th>
				<shiro:hasPermission name="biz:bizMaterielWarehouse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterielWarehouse">
			<tr>
				<td>
					${fns:getDictLabel(bizMaterielWarehouse.warehouseId, 'warehouse', '')}
				</td>
				<td>
					${bizMaterielWarehouse.bizMateriel.name}
				</td>
				<td>
					${bizMaterielWarehouse.inBatch}
				</td>
				<td>
					${bizMaterielWarehouse.inNumber}
				</td>
				<td>
					<fmt:formatDate value="${bizMaterielWarehouse.inTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getUserById(bizMaterielWarehouse.staffId).name}
				</td>
				<td>
					${bizMaterielWarehouse.remarks}
				</td>
				<shiro:hasPermission name="biz:bizMaterielWarehouse:edit"><td>
    				<a href="${ctx}/biz/bizMaterielWarehouse/form?id=${bizMaterielWarehouse.id}">修改</a>
					<a href="${ctx}/biz/bizMaterielWarehouse/delete?id=${bizMaterielWarehouse.id}" onclick="return confirmx('确认要删除该物料入库吗？', this.href)">删除</a>
					<a href="javascript:void(0)" onclick="drawMateriel('${bizMaterielWarehouse.id}')">领取</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>