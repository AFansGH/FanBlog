fzqblog.url = {
	loadCollection: fzqblog.realpath+"/admin/load_collection.action",
	delCollection: fzqblog.realpath+"/admin/del_collection.action"
}
$(function(){
    demo(1);
    $(document).on("click",".del",function(){
	var articleId = $(this).attr("articleId");
	var articleType = $(this).attr("articleType");

	layer.confirm('确认要删除吗？', {
		btn: ['是', '否'], //按钮
		shade: false //不显示遮罩
	}, function() {
	var d = dialog({
		content: "<div><img src='" + fzqblog.realpath + "/resources/images/loading.gif' />&nbsp;&nbsp;&nbsp;删除中...</div>",
	});
	d.showModal();
	setTimeout(function() {
		d.close().remove();
	}, 1000);
		$.ajax({
			url : fzqblog.url.delCollection,
			type: 'POST',
			dataType: 'json',
		 	data : {
				articleId:articleId,
				articleType:articleType
			    },
			success: function(res) {
				if (res.errorMsg != null) {
					layer.msg(res.errorMsg, {
						icon: 5,
						time: 1500 //2秒关闭（如果不配置，默认是3秒）
					});
				} else {
					layer.msg('删除成功', {
						icon: 1,
						time: 1000 //2秒关闭（如果不配置，默认是3秒）
					}, function() {
						demo(fzqblog.curr);
					});
				}
			}
		});


	}, function() {

	});
    });
})

function search(){
	fzqblog.articleType = $("#articleType").val();
   fzqblog.startDate = $("#startDate").val();
   fzqblog.endDate = $("#endDate").val();
   fzqblog.title = $("#title").val();
	demo(1);
}



    function demo(curr) {
	fzqblog.curr = curr;
	$.getJSON(fzqblog.url.loadCollection, {
		pageNum: curr || 1, //向服务端传的参数，此处只是演示
		startDate: fzqblog.startDate,
		endDate: fzqblog.endDate,
		articleType:$("#articleType").val(),
		title:fzqblog.title
	}, function(response) {
		//此处仅仅是为了演示变化的内容
		var data = response.data;
		var simplePage = data.page;
		var list = data.list;
		$("#data-list tr").remove();
		if (list.length > 0) {
		    for (var i = 0, _len = list.length, d; i < _len, d = list[i]; i++) {
			var edit = '<a href="javascript:;" title="删除" class="del" articleId="'+d.articleId+'" articleType="'+d.articleType.type+'"><i class="icon i-del"></i></a>';
			var url = "";
			if(d.articleType.type=="T"){
			    url = fzqblog.realpath+"/bbs/"+d.articleId;
			}else if(d.articleType.type=="K"){
			    url = fzqblog.realpath+"/knowledge/"+d.articleId;
			}else if(d.articleType.type=="A"){
			    url = fzqblog.realpath+"/ask/"+d.articleId;
			}else if(d.articleType.type=="B"){
			    url = fzqblog.realpath+"/user/"+d.articleUserId+"/blog/"+d.articleId;
			}
			$("<tr><td valign='center'><a href='"+url+"' target='_blank'>"+d.title+"</a></td><td>"+d.createTimeString+"</td><td>" + edit + "</td></tr>").appendTo($("#data-list"));
		    }
		} else {
		    $('<tr><td colspan="100"><div class="no-data" >没有数据</div></td></tr>').appendTo($("#data-list"));
		}
		//显示分页
		laypage({
			cont: 'pager', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
			skin: 'molv', //皮肤
			pages: response.data.page.pageTotal, //通过后台拿到的总页数
			curr: curr || 1, //当前页
			jump: function(obj, first) { //触发分页后的回调
				if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
					demo(obj.curr);
				}
			}
		});
	});
};