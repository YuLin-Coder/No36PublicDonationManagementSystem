<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备档案管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${ctxStatic}/layui/css/modules/layer/layer.css" media="screen" type="text/css">
	<script type="text/javascript" src="${ctxStatic}/layui/lay2/modules/layer.js"></script>
	<script src="${ctxStatic}/layui/layui.js" charset="utf-8"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		function repair(id) {
			layer.open({
    			type: 2,
    			title: ["维修申请",false],
    			shade: [0.1],
    			area: ['100%', '100%'],
    			shift: 2,
    			shadeClose: true,
    			content: ['${ctx}/biz/bizEquipment/apply?id=' + id],
				end: function () { 
					window.location.reload();
            	}
    		});
		}
		
		function scrap(id) {
			layer.open({
    			type: 2,
    			title: ["报废申请",false],
    			shade: [0.1],
    			area: ['100%', '100%'],
    			shift: 2,
    			shadeClose: true,
    			content: ['${ctx}/biz/bizEquipment/apply?id=' + id],
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/biz/bizEquipment/">设备档案</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEquipment" action="${ctx}/biz/bizEquipment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>设备类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('equipment_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属车间：</label>
				<form:select path="plant" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('work_plant')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>设备状态：</label>
				<form:select path="status" class="input-medium required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('equipment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>摊销时间：</label>
				<form:select path="amortizationTime" class="input-medium required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('amortization_time')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开始时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizEquipment.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>设备名称</th>
				<th>设备类型</th>
				<th>所属车间</th>
				<th>设备能力</th>
				<th>设备状态</th>
				<th>摊销时间</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="biz:bizEquipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEquipment">
			<tr>
				<td><a href="${ctx}/biz/bizEquipment/form?id=${bizEquipment.id}">
					${bizEquipment.name}
				</a></td>
				<td>
					${fns:getDictLabel(bizEquipment.type, 'equipment_type', '')}
				</td>
				<td>
					${fns:getDictLabel(bizEquipment.plant, 'work_plant', '')}
				</td>
				<td>
					${bizEquipment.capacity}
				</td>
				<td>
					${fns:getDictLabel(bizEquipment.status, 'equipment_status', '')}
				</td>
				<td>
					${fns:getDictLabel(bizEquipment.amortizationTime, 'amortization_time', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizEquipment.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizEquipment.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getUserById(bizEquipment.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${bizEquipment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="biz:bizEquipment:edit"><td>
					<a href="${ctx}/biz/bizEquipment/form?id=${bizEquipment.id}">
						<button class="layui-btn layui-btn-mini"> <i class="layui-icon">&#xe642;</i>修改 </button>
					</a>
					<a href="${ctx}/biz/bizEquipment/delete?id=${bizEquipment.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">
						<button class="layui-btn layui-btn-mini layui-btn-danger"> <i class="layui-icon">&#xe640;</i>删除</button>
					</a>
					<a href="javascript:void(0)" onclick="repair('${bizEquipment.id}')">
						<button class="layui-btn layui-btn-mini layui-btn-normal">维修申请</button>
					</a>
					<a  href="javascript:void(0)" onclick="scrap('${bizEquipment.id}')">
						<button class="layui-btn layui-btn-mini layui-btn-normal">报废申请</button>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>