<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备维修/报废申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$.ajax({
			            cache: true,
			            type: "POST",
			            url:"${ctx}/biz/bizEquipment/saveBizEquipment?id=" + '${bizEquipmentId}',
			            data:$('#inputForm').serialize(),
			            async: false,
			            error: function(request) {
			            	top.$.jBox.tip("申请失败", 'warring');
			            },
			            success: function(dataMap) {
			            	if(dataMap.result == 1){
			            		top.$.jBox.tip("申请失败", 'warring');
			        		}else if(dataMap.result == 0){
			        			top.$.jBox.tip("申请成功", 'success');
		        				setTimeout(function(){backNext()},800);
			        		}
			            }
			        });
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
		
		function backNext(){
			var index = parent.layer.getFrameIndex(window.name);
	    	parent.layer.close(index);
		}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="bizEquipment"  method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<fieldset>
			<legend>申请单</legend>
			<table class="table-form">
				<tr>
					<td class="tit">设备名称</td><td>${bizEquipment.name}</td>
					<td class="tit">设备类型</td><td>${fns:getDictLabel(bizEquipment.type, 'equipment_type', '')}</td>
					<td class="tit">所属车间</td><td>${fns:getDictLabel(bizEquipment.plant, 'work_plant', '')}</td>
				</tr>
				<tr>
					<td class="tit">设备能力</td>
					<td colspan="5">${bizEquipment.capacity}</td>
				</tr>
				<tr>
					<td class="tit" rowspan="2">运行时间</td>
					<td class="tit">开始时间</td>
					<td><fmt:formatDate value="${bizEquipment.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="tit" rowspan="2">维护时间</td>
					<td class="tit">登记时间</td>
					<td><fmt:formatDate value="${bizEquipment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="tit">结束时间</td>
					<td><fmt:formatDate value="${bizEquipment.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="tit">更新时间</td>
					<td><fmt:formatDate value="${bizEquipment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="tit">摊销时间</td>
					<td colspan="5">${fns:getDictLabel(bizEquipment.amortizationTime, 'amortization_time', '')}</td>
				</tr>
				<tr>
					<td class="tit">维护人员</td>
					<td colspan="5">${fns:getUserById(bizEquipment.createBy.id).name}</td>
				</tr>
				
				<tr>
					<td class="tit">设备状态</td>
					<td colspan="5">
						<form:select path="status" class="input-xlarge required">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('equipment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="tit">申请理由</td>
					<td colspan="5">
						<form:textarea path="remarks" class="required" rows="5" maxlength="20" cssStyle="width:500px"/>
					</td>
				</tr>
			</table>
		</fieldset>
		 <div class="form-actions">
			<shiro:hasPermission name="biz:bizEquipment:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="确认申请" />&nbsp;
			</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>
