<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生产计划审核</title>
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
		<li class="active"><a href="${ctx}/biz/bizProductionPlan/">生产计划审核</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProductionPlan" action="${ctx}/biz/bizProductionPlan/audit" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>计划名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>计划类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('plan_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计划状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('plan_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>产品：</label>
				<form:select path="bizProduct.id" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${productList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>车间：</label>
				<form:select path="plantId" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('work_plant')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开始时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizProductionPlan.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizProductionPlan.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons onclick="$('#searchForm').submit();" path="delFlag" items="${fns:getDictList('cms_del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>计划名称</th>
				<th>计划类型</th>
				<th>计划状态</th>
				<th>库存数量</th>
				<th>计划生产数量</th>
				<th>产品ID</th>
				<th>车间ID</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>审核意见</th>
				<shiro:hasPermission name="biz:bizProductionPlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProductionPlan">
			<tr>
				<td><a href="${ctx}/biz/bizProductionPlan/form?id=${bizProductionPlan.id}">
					${bizProductionPlan.name}
				</a></td>
				<td>
					${fns:getDictLabel(bizProductionPlan.type, 'plan_type', '')}
				</td>
				<td>
					${fns:getDictLabel(bizProductionPlan.status, 'plan_status', '')}
				</td>
				<td>
					${bizProductionPlan.existingNumber}
				</td>
				<td>
					${bizProductionPlan.projectNumber}
				</td>
				<td>
					${bizProductionPlan.bizProduct.name}
				</td>
				<td>
					${fns:getDictLabel(bizProductionPlan.plantId, 'work_plant', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizProductionPlan.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizProductionPlan.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizProductionPlan.remarks}
				</td>
				<shiro:hasPermission name="biz:bizProductionPlan:edit"><td>
					<c:if test="${bizProductionPlan.delFlag=='2' }">
						<a href="${ctx}/biz/bizProductionPlan/form?id=${bizProductionPlan.id}&auditFlag=1">审核</a>
					</c:if>
    				<c:if test="${bizProductionPlan.delFlag=='0' }">
						<a href="${ctx}/biz/bizProductionPlan/delete?id=${bizProductionPlan.id}" onclick="return confirmx('确认要删除该生产计划吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${bizProductionPlan.delFlag=='1' }">
						<a href="${ctx}/biz/bizProductionPlan/delete?id=${bizProductionPlan.id}&isRe=true" onclick="return confirmx('确认要恢复该生产计划吗？', this.href)">恢复审核</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>