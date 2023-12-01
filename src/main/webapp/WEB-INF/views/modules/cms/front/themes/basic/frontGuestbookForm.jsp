<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我要留言</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
	<link href="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					content: {required: "请填写留言内容"},
					validateCode: {remote: "验证码不正确"}
				},
				submitHandler: function(form){
					$.ajax({
			            cache: true,
			            type: "POST",
			            url:"${ctx}/guestbook/save",
			            data:$('#inputForm').serialize(),
			            async: false,
			            error: function(request) {
			            	top.$.jBox.tip("发表失败",'warring');
			            },
			            success: function(dataMap) {
			            	if(dataMap.result == 0){
			            		top.$.jBox.tip("发表失败",'warring');
			        		}else if(dataMap.result == 1){
			        			top.$.jBox.tip("发表成功",'success');
			        			setTimeout(function(){
			        				location.reload();
			        			},800);
			        		}
			            }
			        });
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#main_nav li").each(function(){
				$(this).toggleClass("active", $(this).text().indexOf('公共留言')>=0);
			});
		});
		function page(n,s){
			location="${ctx}/guestbook/form?pageNo="+n+"&pageSize="+s;;
		}
	</script>
</head>
<body>
	<div style="padding:0 0 20px;">
		<h4>我要留言</h4>
		<form:form id="inputForm" method="post" class="form-horizontal">
			<div class="control-group">
				<label class="control-label">名称:</label>
				<div class="controls">
					<input type="text" name="name" maxlength="50" class="required" style="width:300px;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">邮箱:</label>
				<div class="controls">
					<input type="text" name="email" maxlength="50" class="required email" style="width:300px;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">电话:</label>
				<div class="controls">
					<input type="text" name="phone" maxlength="50" class="required phone" style="width:300px;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">单位:</label>
				<div class="controls">
					<input type="text" name="workunit" maxlength="50" class="required" style="width:300px;"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">留言分类:</label>
				<div class="controls">
					<select name="type" class="txt required" style="width:100px;">
						<option value="">请选择</option>
						<c:forEach items="${fns:getDictList('cms_guestbook')}" var="type">
							<option value="${type.value}">${type.label}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">留言内容:</label>
				<div class="controls">
					<textarea name="content" rows="4" maxlength="200" class="required" style="width:400px;"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">验证码:</label>
				<div class="controls">
					<sys:validateCode name="validateCode" />
				</div>
			</div>
			<div class="form-actions">
				<input class="btn" type="submit" value="提 交"/>&nbsp;
			</div>
			<div id="messageBox" class="alert alert-error" style="display:none">输入有误，请先更正。</div>
		</form:form>
	</div>
</body>
</html>