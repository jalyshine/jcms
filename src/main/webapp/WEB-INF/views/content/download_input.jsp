<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jfp" uri="http://www.springframework.org/tags/form-plus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../account.jsp" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>添加/修改下载</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="${ base }assets/plugins/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="${ base }assets/css/page.css"/>
	<style type="text/css">
		.desc-box{ width: 100%; border: 0; color: gray; font-size: 10pt; background-color: transparent; }
	</style>
</head>
<body>
<c:if test="${ download.id == null }">
	<c:set value="${ base }content/Download/add" var="save"/>
</c:if>
<c:if test="${ download.id != null }">
	<c:set value="${ base }content/Download/edit" var="save"/>
</c:if>
<div class="layui-card">
	<div class="layui-card-header">
		<span class="card-title">添加 & 编辑下载</span>
		<c:import url="content_menubar.jsp">
			<c:param name="et" value="Download" />
			<c:param name="etn" value="下载" />
			<c:param name="cid" value="${ category.id }" />
			<c:param name="wfs" value="${ category.workFlow.steps }" />
			<c:param name="odr" value="${ param.odr }" />
		</c:import>
	</div>
	<div class="layui-card-body">
		<form:form action="${ save }" method="post" modelAttribute="download" cssClass="layui-form">

			<!-- 防止表单重复提交 -->
			<jfp:token/>
			<form:hidden path="id"/>
			<form:hidden path="downloadData.downloadId"/>
			<!-- 管理员添加 -->
			<form:hidden path="sysAdd" value="true"/>
			<!-- 所属栏目  -->
			<form:hidden path="categoryId" value="${ category.id }"/>
			<!-- 用于栏目权限控制 -->
			<input type="hidden" name="cid" value="${ category.id }">

			<div class="layui-form-item">
				<label class="layui-form-label">所属栏目</label>

				<div class="layui-input-inline">
					<label class="layui-form-label" style="background-color: #E6E6E6; text-align: center;">${ category.name }</label>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">下载名称</label>

				<div class="layui-input-block">
					<form:input path="title" autocomplete="off" lay-verify="title" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">关键词</label>

				<div class="layui-input-inline">
					<form:input path="keywords" autocomplete="off" class="layui-input"/>
				</div>
				<div class="layui-form-mid layui-word-aux">多关键词之间用空格或“,”隔开</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">软件平台</label>

				<div class="layui-input-block">
					<form:input path="systems" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">授权形式</label>

				<div class="layui-input-block">
					<form:input path="copyType" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">软件语言</label>

				<div class="layui-input-block">
					<form:input path="language" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">软件类型</label>

				<div class="layui-input-block">
					<form:input path="classType" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">软件版本</label>

				<div class="layui-input-block">
					<form:input path="version" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">软件大小</label>

				<div class="layui-input-block">
					<form:input path="fileSize" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">评分等级</label>

				<div class="layui-input-block">
					<form:input path="stars" autocomplete="off" class="layui-input"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">下载简介</label>

				<div class="layui-input-block">
                    <%--存放编辑器附件地址--%>
                    <input type="hidden" name="editorAttachment"/>
					<form:textarea path="description" class="layui-textarea"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">详细介绍</label>

				<div class="layui-input-block">
					<form:textarea path="downloadData.content" cssClass="ckeditor"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">下载地址</label>

				<div class="layui-input-block">
					<div class="layui-upload">
						<button type="button" class="layui-btn layui-btn-normal" id="addItem">添加下载</button>
						<button type="button" class="layui-btn" id="uploadItem">开始上传</button>
						<button type="button" class="layui-btn layui-btn-normal" id="addAddress">添加地址</button>
						<div class="layui-upload-list">
							<table lay-filter="picTab" lay-size="sm" class="layui-table">
								<thead><tr>
									<th>名称</th>
									<th>地址</th>
									<th>大小</th>
									<th>状态</th>
									<th>操作</th>
								</tr></thead>
								<tbody id="itemList">
								<c:if test="${ download.id != null }">
									<c:forEach items="${ download.downloadData.downloadItems }" var="item">
										<tr>
											<td><input type="text" name="itemName" value="${ item.name }" autocomplete="off" class="desc-box" placeholder="请输入地址名称"></td>
											<td><input type="text" name="itemUrl" value="${ item.url }" autocomplete="off" class="desc-box" placeholder="请输入下载地址"></td>
											<td>
												<fmt:formatNumber value="${ item.size/1024 }" pattern="#,#00.0#"/>KB
												<input type="hidden" name="itemSize" value="${ item.size }">
											</td>
											<td>已存储</td>
											<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button></td>
										</tr>
									</c:forEach>
								</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">推荐位</label>

				<div class="layui-input-block">
					<form:checkboxes items="${ recommendPositions }" itemLabel="name" itemValue="id" path="downloadData.positionIds" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">焦点图</label>

				<div class="layui-input-block layui-upload">
					<input type="checkbox" lay-filter="upload" title="上传原图">
					<form:hidden path="thumb"/>
					<div class="layui-upload-list">
						<img id="thumb-img" style="height: 100px"
						<c:if test="${ empty download.thumb }">
							 src="${ base }assets/css/img/upload_bk.png">
						</c:if>
						<c:if test="${ !empty download.thumb }">
							src="${ host }/${ download.thumb }">
						</c:if>
					</div>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">发布时间</label>

				<div class="layui-input-inline">
					<form:input path="publishTimeStr" autocomplete="off" class="layui-input" readonly="readonly" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">链接地址</label>

				<div class="layui-input-inline">
					<form:input path="url" autocomplete="off" class="layui-input"/>
				</div>
				<div class="layui-input-inline">
					<form:checkbox path="isLink" title="转向链接"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">允许评论</label>

				<div class="layui-input-inline">
					<form:checkbox path="downloadData.allowComment" title="允许"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">阅读收费</label>

				<div class="layui-input-inline">
					<form:input path="downloadData.readPoint" autocomplete="off" lay-verify="number" class="layui-input"/>
				</div>
				<div class="layui-form-mid layui-word-aux">点/元</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">阅读权限</label>

				<div class="layui-input-block">
					<form:checkboxes items="${ memberGroups }"
									 path="downloadData.groupIds" itemLabel="name" itemValue="id"/>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">稿件状态</label>

				<div class="layui-input-inline">
					<c:choose>
						<c:when test="${ category.workFlow.steps == 1 }">
							<jfp:JsonDataSource id="statusList" value="{'0':'退稿','1':'一审','99':'通过'}" />
						</c:when>
						<c:when test="${ category.workFlow.steps == 2 }">
							<jfp:JsonDataSource id="statusList" value="{'0':'退稿','1':'一审','2':'二审','99':'通过'}" />
						</c:when>
						<c:when test="${ category.workFlow.steps == 3 }">
							<jfp:JsonDataSource id="statusList" value="{'0':'退稿','1':'一审','2':'二审','3':'三审','99':'通过'}" />
						</c:when>
						<c:when test="${ category.workFlow.steps == 4 }">
							<jfp:JsonDataSource id="statusList" value="{'0':'退稿','1':'一审','2':'二审','3':'三审','4':'四审','99':'通过'}" />
						</c:when>
						<c:otherwise>
							<jfp:JsonDataSource id="statusList" value="{'0':'退稿','99':'通过'}" />
						</c:otherwise>
					</c:choose>
					<form:select path="status" items="${ statusList }" />
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button lay-submit id="saveBtn" class="layui-btn" >提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form:form>
	</div>
</div>

<script src="${ base }assets/plugins/layui/layui.js"></script>
<script src="${ base }assets/js/fixed.js"></script>
<script src="${ base }assets/plugins/ckeditor/ckeditor.js"></script>
<script>
    var editor = CKEDITOR.replace('downloadData.content', {
        adminUrl: '${ base }',
        imageUploadUrl: '${ host }/upload?type=ill',
        fileUploadUrl: '${ host }/upload',
        account: '${ host_account }',
        password: '${ host_password }',
        baseHref: '${ host }/'
    });

    layui.config({
        base: '${ base }assets/js/'
    }).use(['form',  'laydate', 'layPost', 'element'], function () {
		var $ = layui.$, form = layui.form,
			laydate = layui.laydate, upload = layui.upload, layPost = layui.layPost;

		laydate.render({elem: '#publishTimeStr', type: 'datetime'});

        var uploadInst = layPost.image('thumb', 'thumb', '${ base }',
            '${ host }', '${ host_account }', '${ host_password }');

        form.on('checkbox(upload)', function(data){
            if(data.elem.checked){
                uploadInst.config.url = '${ host }/upload?type=customer';
            } else {
                uploadInst.config.url = '${ host }/upload?type=thumb';
            }
        });

		$("button.delete").on("click", function(){
			$(this).parent().parent().remove();
			return false;
		});

		$("#addAddress").on("click", function(){
			var tr = $(['<tr><td><input type="text" name="itemName" autocomplete="off" class="desc-box" placeholder="请输入地址名称"></td>',
			'<td><input type="text" name="itemUrl" autocomplete="off" class="desc-box" placeholder="请输入下载地址"></td>',
			'<td><input type="text" name="itemSize" autocomplete="off" class="desc-box"></td>',
			'<td>新添加</td>',
			'<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button></td></tr>'].join(''));
			tr.find('.delete').on('click', function(){
				tr.remove();
			});
			$('#itemList').append(tr);
		});

		var itemListView = $('#itemList')
				,uploadListIns = upload.render({
					elem: '#addItem'
					,url: '${ host }/upload'
                    , data: {account: '${ host_account }', password: '${ host_password }'}
					,accept: 'file'
					,multiple: true
					,auto: false
					,bindAction: '#uploadItem'
					,choose: function(obj){
						var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
						//读取本地文件
						obj.preview(function(index, file, result){
							var tr = $(['<tr id="upload-'+ index +'">'
								,'<td><input type="text" name="itemName" class="desc-box" value="' + file.name + '"></td>'
								,'<td><input type="text" name="itemUrl" class="desc-box"></td>'
								,'<td>'+ (file.size/1024).toFixed(1) +'KB<input type="hidden" name="itemSize" value="'+ file.size +'"></td>'
								,'<td>等待上传</td>'
								,'<td><button class="layui-btn layui-btn-xs layui-btn-danger delete">删除</button>'
								,'<button class="layui-btn layui-btn-xs reload layui-hide">重传</button></td>'
								,'</tr>'].join(''));

							//单个重传
							tr.find('.reload').on('click', function(){
								obj.upload(index, file);
							});

							//删除
							tr.find('.delete').on('click', function(){
								delete files[index]; //删除对应的文件
								tr.remove();
								uploadListIns.config.elem.next()[0].value = ''; //清空 input explorer 值，以免删除后出现同名文件不可选
							});

							itemListView.append(tr);
						});
					}
					,done: function(res, index, upload){
						if(res.code == 0){
                            if(res.siteId == undefined){
                                var url = '${ base }upload/attach';
                                var arg = {'data':JSON.stringify(res)};
                                $.post(url, arg, null);
                            }
							var tr = itemListView.find('tr#upload-'+ index)
									,tds = tr.children();
							tds.eq(1).find(":text").val(res.data.src);
							tds.eq(3).html('<span style="color: #5FB878;">上传成功</span>');
							tds.eq(4).html(''); //清空操作
							return delete this.files[index]; //删除文件队列已经上传成功的文件
						}
						this.error(index, upload);
					}
					,error: function(index, upload){
						var tr = itemListView.find('tr#upload-'+ index)
								,tds = tr.children();
						tds.eq(3).html('<span style="color: #FF5722;">上传失败</span>');
						tds.eq(4).find('.reload').removeClass('layui-hide'); //显示重传
					}
				});

		form.verify({
			title: function(value, item){
				if($.trim(value) == ""){
					return "下载名称不能为空！";
				}
				var flag = false;
				$.ajaxSettings.async = false; // 异步提交
				$.post("${ base }content/Download/check", {'time': new Date(), 'ttl':value }, function(data){
					flag = data != 0 && data != $("#id").val();
				});
				$.ajaxSettings.async = true; // 恢复同步提交
				if(flag){
					return "该名称已经被占用！";
				}
			}
		});
        $("#saveBtn").on("click", function () {
            var $content = $("<div></div>").html(editor.document.getBody().getHtml());
            var filePath = "";
            $content.find("[src]").each(function (index) {
                var src = $(this).attr("src");
                if (src.indexOf("http://") == -1 && src.indexOf("https://") == -1) {
                    filePath += $(this).attr("src") + ",";
                }
            });
            $(":hidden[name='editorAttachment']").val(filePath.substring(0, filePath.length - 1));
            $("form").submit();
            return false;
        });
	});
</script>
</body>
</html>