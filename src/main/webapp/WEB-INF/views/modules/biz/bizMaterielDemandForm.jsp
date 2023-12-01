<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物料需求管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var auditFlag = "${auditFlag}";
			console.log(auditFlag);
			
			if(auditFlag == '1'){
				$("#productionPlanName").attr("disabled",true);
				$("#productionPlanType").attr("disabled",true);
				$("#productionPlanStatus").attr("disabled",true);
				$("#existingNumber").attr("disabled",true);
				$("#projectNumber").attr("disabled",true);
				$("#bizProductId").attr("disabled",true);
				$("#plantId").attr("disabled",true);
				$("#beginTime").attr("disabled",true);
				$("#endTime").attr("disabled",true);
				$("#auditOpinion").show();
				$("#auditOpinion").addClass("required");
			} else {
				$("#productionPlanName").attr("disabled",false);
				$("#productionPlanType").attr("disabled",false);
				$("#productionPlanStatus").attr("disabled",false);
				$("#existingNumber").attr("disabled",false);
				$("#projectNumber").attr("disabled",false);
				$("#bizProductId").attr("disabled",false);
				$("#plantId").attr("disabled",false);
				$("#beginTime").attr("disabled",false);
				$("#endTime").attr("disabled",false);
				$("#auditOpinion").removeClass("required");
				$("#auditOpinion").hide();
			}
			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/biz/bizMaterielDemand/">物料需求列表</a></li>
		<li class="active"><a href="${ctx}/biz/bizMaterielDemand/form?id=${bizMaterielDemand.id}">物料需求<shiro:hasPermission name="biz:bizMaterielDemand:edit">${not empty bizMaterielDemand.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="biz:bizMaterielDemand:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMaterielDemand" action="${ctx}/biz/bizMaterielDemand/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="delFlag"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">物料：</label>
			<div class="controls">
				<form:select path="bizMateriel.id" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${materielList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存数量：</label>
			<div class="controls">
				<form:input path="existingNumber" htmlEscape="false" maxlength="11" class="input-medium required number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">需求数量：</label>
			<div class="controls">
				<form:input path="projectNumber" htmlEscape="false" maxlength="20" class="input-medium required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急度：</label>
			<div class="controls">
				<form:select path="emergency" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('emergency_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车间：</label>
			<div class="controls">
				<form:select path="plantId" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('work_plant')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交货时间：</label>
			<div class="controls">
				<input name="deliveryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizMaterielDemand.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" id="auditOpinion">
			<label class="control-label">审核意见：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${auditFlag=='0' }">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交申请"/>
			</c:if>
			<c:if test="${auditFlag=='1' }">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="通过"  onclick="$('#delFlag').val('0')"/>
				<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳回"  onclick="$('#delFlag').val('2')"/>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>