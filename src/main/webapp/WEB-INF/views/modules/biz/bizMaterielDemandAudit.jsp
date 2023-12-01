<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物料需求审核</title>
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
		<li class="active"><a href="${ctx}/biz/bizMaterielDemand/">物料需求审核</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterielDemand" action="${ctx}/biz/bizMaterielDemand/audit?auditFlag=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>物料：</label>
				<form:select path="bizMateriel.id" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${materielList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterielDemand.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>物料</th>
				<th>库存数量</th>
				<th>计划数量</th>
				<th>紧急度</th>
				<th>车间ID</th>
				<th>交货时间</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>审核意见</th>
				<shiro:hasPermission name="biz:bizMaterielDemand:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterielDemand">
			<tr>
				<td>
					${bizMaterielDemand.bizMateriel.name}
				</td>
				<td>
					${bizMaterielDemand.existingNumber}
				</td>
				<td>
					${bizMaterielDemand.projectNumber}
				</td>
				<td>
					${fns:getDictLabel(bizMaterielDemand.emergency, 'emergency_type', '')}
				</td>
				<td>
					${fns:getDictLabel(bizMaterielDemand.plantId, 'work_plant', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizMaterielDemand.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getUserById(bizMaterielDemand.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${bizMaterielDemand.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizMaterielDemand.remarks}
				</td>
				<shiro:hasPermission name="biz:bizMaterielDemand:edit"><td>
					<c:if test="${bizMaterielDemand.delFlag=='2' }">
						<a href="${ctx}/biz/bizMaterielDemand/form?id=${bizMaterielDemand.id}&auditFlag=1">审核</a>
					</c:if>
    				<c:if test="${bizMaterielDemand.delFlag=='0' }">
						<a href="${ctx}/biz/bizMaterielDemand/delete?id=${bizMaterielDemand.id}" onclick="return confirmx('确认要删除该物料需求吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${bizMaterielDemand.delFlag=='1' }">
						<a href="${ctx}/biz/bizMaterielDemand/delete?id=${bizMaterielDemand.id}&isRe=true" onclick="return confirmx('确认要恢复该物料需求吗？', this.href)">恢复审核</a>
					</c:if>
				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>