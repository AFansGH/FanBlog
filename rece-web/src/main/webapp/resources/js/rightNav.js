$(function() {
	$(".pack").click(function(event) {
		$("#packUp").slideUp("slow");
		$("#display").show("slow");

	});
	$("#display").click(function(event) {
		$("#display").hide("slow");
		$("#packUp").slideDown("slow");
	});
	//返回顶部
	$(document).on("click", ".gotop", function() {
		$('html,body').animate({
			scrollTop: 0
		}, 400);
	});
	//定位评论框
	$(document).on("click", ".comment", function() {
		$('html,body').animate({
			scrollTop: $('#comment-content').offset().top
		}, 400);
		fzqblog.ueditor.focus();
	});
	//收藏
	$(document).on("click", ".collection", function() {
		if(fzqblog.userId=="" || fzqblog.userId == 0){
    		goLogin();
    	    return;
    	}
    	var d = dialog({
					content: "<div><img src='" + fzqblog.realpath +"/resources/images/loading.gif' />&nbsp;&nbsp;&nbsp;收藏中...</div>",
				});
				d.showModal();
				setTimeout(function() {
					d.close().remove();
				}, 1000);		
		$.ajax({
			url: fzqblog.realpath + '/doCollection',
			type: 'POST',
			dataType: 'json',
			data: {
					userId:fzqblog.userId,
				   "articleType": fzqblog.articleType,
				   "articleId": fzqblog.topicId,
				   "title":fzqblog.articleTitle,
				   "articleUserId":fzqblog.articleUserId
			},
			success:function(res){
				if(res.errorMsg != null){
					layer.alert(res.errorMsg, {
					  icon: 5,
					  skin: 'layer-ext-moon' 
					});
					return;
				}
				else{
					layer.msg('收藏成功', {icon: 6,time:1000});
					$("#collection-count").text(parseInt($("#collection-count").text()) + 1);
				}
			}
		});
		
	});
	//赞
	$(document).on("click", ".like", function() {
		if(fzqblog.userId=="" || fzqblog.userId == 0){
    		goLogin();
    	    return;
    	}
    	var d = dialog({
					content: "<div><img src='" + fzqblog.realpath +"/resources/images/loading.gif' />&nbsp;&nbsp;&nbsp;赞中...</div>",
				});
				d.showModal();
				setTimeout(function() {
					d.close().remove();
				}, 1000);		
		$.ajax({
			url: fzqblog.realpath + '/doLike',
			type: 'POST',
			dataType: 'json',
			data: {
					userId:fzqblog.userId,
				   "articleType": fzqblog.articleType,
				   "articleId": fzqblog.topicId,
				   "title":fzqblog.articleTitle
			},
			success:function(res){
				if(res.errorMsg != null){
					layer.msg(res.errorMsg, {icon: 5,time:1500});   
					return;
				}
				else{
					layer.msg('赞成功', {icon: 6,time:1000});
					$("#like-count").text(parseInt($("#like-count").text()) + 1);
				}
			}
		});
		
	});

});