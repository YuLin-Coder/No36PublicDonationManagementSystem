<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物资出库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$.ajax({
			            cache: true,
			            type: "POST",
			            url:"${ctx}/biz/bizMaterielWarehouse/doDrawMateriel",
			            data:$('#inputForm').serialize(),
			            async: false,
			            error: function(request) {
			            	top.$.jBox.tip("领取失败", 'error');
			            },
			            success: function(dataMap) {
			            	if(dataMap.result == 1){
			            		top.$.jBox.tip("领取失败", 'error');
			        		}else if(dataMap.result == 0){
			        			top.$.jBox.tip("领取成功", 'success');
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
	<br>
	<form:form id="inputForm" modelAttribute="bizMaterielWarehouse" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="bizMateriel.id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">仓库：</label>
			<div class="controls">
				${fns:getDictLabel(bizMaterielWarehouse.warehouseId, 'warehouse', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物资类型：</label>
			<div class="controls">
				<c:out value="${bizMaterielWarehouse.bizMateriel.name}"></c:out>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出库数量：</label>
			<div class="controls">
				<form:input path="inNumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出库说明：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="biz:bizDrawMateriel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="领取"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>