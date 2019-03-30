fzqblog.tags=["shuoshuo","topic","ask","knowledge","blog","fans","focus"]
fzqblog.centerUrl={
    loadShuoShuos:fzqblog.realpath+"/user/loadShuoShuos",
    loadTopic:fzqblog.realpath+"/user/loadTopic",
    loadAsk:fzqblog.realpath+"/user/loadAsk",
    loadKnowledge:fzqblog.realpath+"/user/loadKnowledge",
    loadFans:fzqblog.realpath+"/user/loadFans",
    loadFoucs:fzqblog.realpath+"/user/loadFocus"
}
$(function(){
    tag({
	id : "tag", contentClass : "tag-content", fun : function(index) {
	    var type = fzqblog.tags[index];
	    var url = window.location.href;
	    url = url.substring(0, url.lastIndexOf("#"));
	    window.location.href = url + "#" + type;
	    dispatchLoad(type);
	}
    });
    initTagContent();

});



function tag(config) {
		var id = config.id;
		var contentClass = config.contentClass;
		var event = config.event || "click";
		var fun = config.fun || function() {};
		var tag = $("#" + id);
		var lis = tag.find("li");
		$("." + contentClass).css({
			"marginTop": "10px"
		});
		$("." + contentClass).hide();
		$("." + contentClass).eq(0).show();
		lis.each(function(i, v) {
			$(this).attr("index", i);
		});
		lis.bind(event, function() {
			lis.removeClass("active");
			$(this).addClass("active");
			if($(this).attr('index')==0){
				$("body").css('background-color', '#e9f0f5');
				$(this).css('border-bottom', '1px solid #E9F0F5');
			}
			else{
				$("body").css('background-color', '#fff');
				$(this).css('border-bottom', '1px solid #fff');
			}
			$("." + config.contentClass).hide();
			$("." + config.contentClass).eq($(this).attr("index")).show();
			fun($(this).attr("index"));
		});
	}

//初始化tag内容
function initTagContent(){
	$(".tag-content").hide();
    var url = window.location.href;
    var indexType = url.lastIndexOf("#");
    if (indexType == -1) {
    	type = "shuoshuo";
		$("body").css('background-color', '#e9f0f5');
		$(".tag-content").eq(0).show();    	
    } else {
    	type = url.substring(url.lastIndexOf("#") + 1);
		$("body").css('background-color', '#fff');
    }
    $("ul.tag li").removeClass("active");
    $("ul.tag li[type='" + type + "']").addClass("active");
    dispatchLoad(type);
}

function dispatchLoad(type) {
    switch (type) {
    case "shuoshuo":// 吐槽
	if($(".tag-content").eq(0).children().length==0){
	    loadShuoShuos(1);
	}
	break;
    case "topic":// 主题
	$(".tag-content").eq(1).show();
	if($("#topic-content").children().length==0){
	    loadTopic(1);
	}
	break;
    case "ask":// 问答
	$(".tag-content").eq(2).show();
	if($("#ask-content").children().length==0){
	    loadAsk(1);
	}
	break;
    case "knowledge":// 知识库
	$(".tag-content").eq(3).show();
	if($("#knowledge-content").children().length==0){
	    loadKnowledge(1);
	}
	break;
    case "fans":// 粉丝
	$(".tag-content").eq(5).show();
	if($("#fans-content").children().length==0){
	    loadFans();
	}
	break;
    case "focus":// 关注的人
	$(".tag-content").eq(6).show();
	if($("#focus-content").children().length==0){
	    loadFocus();
	}
	break;
    default:
	break;
    }
}


	function loadShuoShuos(pageOn){
		var tagcontent = $(".tag-content").eq(0);
		var tag_content = $('<section id="cd-timeline" class="cd-container"></section>').appendTo(tagcontent);
		fzqblog.pageOn = pageOn;
		$("#load-more").remove();
    	$('<div id="loading"> <div class="loading-con"><img src="' + fzqblog.realpath + '/resources/images/loading.gif"/><span>正在加载.......</span></div></div>').appendTo(tagcontent);
		$.ajax({
			url: fzqblog.centerUrl.loadShuoShuos,
			type: 'POST',
			dataType: 'json',
			data: {"pageOn": fzqblog.pageOn,
					"userId": fzqblog.center.userId
					},
			success:function(res){
				 $("#loading").remove();
				var list = res.data.list;
				var simplePage = res.data.page;
				 if(simplePage.pageTotal==0){
					$("<span class='no-data'>没有吐槽</span>").appendTo(tagcontent);
			    }
			    else{
					for (var i = 0, _len = list.length, data; i < _len, data = list[i]; i++) {
					new ShuoShuoItem(data).appendTo(tag_content);
					}
					if (simplePage.pageTotal > simplePage.pageOn) {
					$('<div id="load-more" class="load-more"><a href="javascript:;">⇓加载更多</a></div>').appendTo(tag_content);
					}
				}
			}
		});
	}

	/************主题******************/

	$(document).on("click", "#shuoshuoload-more", function() {
		$(this).remove();
		loadTopic(fzqblog.topicPage + 1);

	});

function topicItem(data){
    var topic_item = $("<div class='topic-item'></div>");
    var topic_item_info = $("<div class='topic-item-info'></div>").appendTo(topic_item);
    var topic_item_title = $("<div class='topic-item-title'></div>").appendTo(topic_item_info);
    if(data.grade>0){
	 $('<span class="ding">置顶</span>').appendTo(topic_item_title);
    }
    if(data.essence=="1"){
	 $('<span class="jing">精华</span>').appendTo(topic_item_title);
    }
    if(data.topicType.type==1){
	 $('<span class="vote">投票</span>').appendTo(topic_item_title);
    }
    $("<a href='"+fzqblog.realpath+"/bbs/"+data.topicId+"' target='_blank' class='title'>"+data.title+"</a>").appendTo(topic_item_title);
    $('<span class="time">'+data.createTime+'</span>').appendTo(topic_item_title);
    $('<a class="topic-cate-tag"  target="_blank"  href="'+fzqblog.realpath+'/bbs/sub_board/'+data.categoryId+'"><span>'+data.categoryName+'</span></a>').appendTo(topic_item_title);
    $('<div class="topic-item-summary">'+data.summary+'</div>').appendTo(topic_item_info);
    if(data.topicImageArray!=null){
      var topic_item_images =  $('<div class="image-thum topic-item-images"></div>').appendTo(topic_item_info);
       for(var i=0,_len=data.topicImageArray.length;i<_len;i++){
	   $('<a href="javascript:;" class="showImage img"><img class="img-thumbnail"  src="' + data.topicImageArray[i]+'"></a>').appendTo(topic_item_images);
	   if(i==2){
	       break;
	   }
       }
       $('<div class="clear"></div>').appendTo(topic_item_images);
    }
    var topic_count_info = $('<div class="topic-count-info"></div>').appendTo(topic_item_info);
    $('<span class="count"><i class="icon icon-read"></i><span>阅读：'+data.readCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-comment"></i><span>评论：'+data.commentCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-like"></i><span>喜欢：'+data.likeCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-collection"></i><span>收藏：'+data.collectionCount+'</span></span>').appendTo(topic_count_info);
    return topic_item;
}

function loadTopic(page){
    var topic_content = $("#topic-content");
    $('<div id="loading"> <div class="loading-con"><img src="' + fzqblog.realpath + '/resources/images/loading.gif"/><span>正在加载.......</span></div></div>').appendTo(topic_content);
    fzqblog.topicPage = page;
    $.ajax({
    	url: fzqblog.centerUrl.loadTopic,
    	type: 'POST',
    	dataType: 'json',
    	data: { 
    	 pageOn : page,
	   	 userId: fzqblog.center.userId
	   	},
	   	success:function(res){
		   	$("#loading").remove();
		    var list = res.data.list;
		    if (list.length == 0) {
			$("<span class='no-data'>没有帖子</span>").appendTo(topic_content);
		    }
		    var simplePage = res.data.page;
		    for (var i = 0, _len = list.length, d; i < _len, d = list[i]; i++) {
			new topicItem(d).appendTo(topic_content);
		    }
		   
		    if(simplePage.pages>simplePage.current){
			 	$('<div id="shuoshuoload-more" class="load-more"><a href="javascript:;">⇓加载更多</a></div>').appendTo(topic_content);
				}
		   	}
	
    });
}

$(document).on("click", ".showImage", function() {
	var bimg = $(this).find("img").attr("src");
		// var d = dialog({
		//     title: '图片查看',
		//     content: '<div style="text-align:center"><img src="' + bimg + '"style="max-width:1300px;"></div>'
		// });
		// d.showModal();
		layer.open({
		maxWidth:1300,
        content: '<div style="text-align:center"><a href="' + bimg  + '" target="_blank"><img src="' + bimg + '"style="max-width:1300px;"></a></div>'
    });

});


/************问答******************/
function askItem(data){
    var topic_item = $("<div class='topic-item'></div>");
    var topic_item_info = $("<div class='topic-item-info'></div>").appendTo(topic_item);
    var topic_item_title = $("<div class='topic-item-title'></div>").appendTo(topic_item_info);
    $("<a href='"+fzqblog.realpath+"/ask/"+data.askId+"' target='_blank' class='title'>"+data.title+"</a>").appendTo(topic_item_title);
    $('<span class="time">'+data.createTime+'</span>').appendTo(topic_item_title);
    $('<div class="topic-item-summary">'+data.summary+'</div>').appendTo(topic_item_info);
    if(data.topicImageArray!=null){
      var topic_item_images =  $('<div class="image-thum topic-item-images"></div>').appendTo(topic_item_info);
       for(var i=0,_len=data.topicImageArray.length;i<_len;i++){
	   $('<a href="javascript:;" class="img"><img src="'+ data.topicImageArray[i]+'"></a>').appendTo(topic_item_images);
	   if(i==2){
	       break;
	   }
       }
       $('<div class="clear"></div>').appendTo(topic_item_images);
    }
    var topic_count_info = $('<div class="topic-count-info"></div>').appendTo(topic_item_info);
    $('<span class="count"><i class="icon icon-mark"></i><span>赏金：'+data.mark+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-read"></i><span>阅读：'+data.readCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-comment"></i><span>评论：'+data.commentCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-like"></i><span>喜欢：'+data.likeCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-collection"></i><span>收藏：'+data.collectionCount+'</span></span>').appendTo(topic_count_info);
    return topic_item;
}

	$(document).on("click", "#askload-more", function() {
		$(this).remove();
		loadAsk(fzqblog.askPage + 1);

	});

function loadAsk(page){
    var topic_content = $("#ask-content");
    $('<div id="loading"> <div class="loading-con"><img src="' + fzqblog.realpath + '/resources/images/loading.gif"/><span>正在加载.......</span></div></div>').appendTo(topic_content);
        fzqblog.askPage= page;
	    $.ajax({
	    	url: fzqblog.centerUrl.loadAsk,
	    	type: 'POST',
	    	dataType: 'json',
	    	timeout: 500,
	    	data: { 
	    	 pageOn : page,
		   	 userId: fzqblog.center.userId
		   	},
		   	success:function(res){
			   	$("#loading").remove();
			    var list = res.data.list;
			    if (list.length == 0) {
				$("<span class='no-data'>没有问答</span>").appendTo(topic_content);
			    }
			    var simplePage = res.data.page;
			    for (var i = 0, _len = list.length, d; i < _len, d = list[i]; i++) {
				new askItem(d).appendTo(topic_content);
			    }
			   
			    if(simplePage.pages>simplePage.pageOn){
				 	$('<div id="askload-more" class="load-more"><a href="javascript:;">⇓加载更多</a></div>').appendTo(topic_content);
					}
			   	}
	    });
}

/************知识库******************/
function knowledgeItem(data){
    var topic_item = $("<div class='topic-item'></div>");
    var topic_item_info = $("<div class='topic-item-info'></div>").appendTo(topic_item);
    var topic_item_title = $("<div class='topic-item-title'></div>").appendTo(topic_item_info);
    $("<a href='"+fzqblog.realpath+"/knowledge/"+data.topicId+"' target='_blank' class='title'>"+data.title+"</a>").appendTo(topic_item_title);
    $('<span class="time">'+data.createTime+'</span>').appendTo(topic_item_title);
    $('<a class="topic-cate-tag" target="_blank"  href="'+fzqblog.realpath+'/knowledge/categoryId/'+data.categoryId+'">'+data.categoryName+'</a>').appendTo(topic_item_title);
    $('<div class="topic-item-summary">'+data.summary+'</div>').appendTo(topic_item_info);
    if(data.topicImageArray!=null){
      var topic_item_images =  $('<div class="image-thum topic-item-images"></div>').appendTo(topic_item_info);
       for(var i=0,_len=data.topicImageArray.length;i<_len;i++){
	   $('<a href="javascript:;" class="showImage img"><img src="'+ data.topicImageArray[i]+'"></a>').appendTo(topic_item_images);
	   if(i==2){
	       break;
	   }
       }
       $('<div class="clear"></div>').appendTo(topic_item_images);
    }
    var topic_count_info = $('<div class="topic-count-info"></div>').appendTo(topic_item_info);
    $('<span class="count"><i class="icon icon-read"></i><span>阅读：'+data.readCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-comment"></i><span>评论：'+data.commentCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-like"></i><span>喜欢：'+data.likeCount+'</span></span>').appendTo(topic_count_info);
    $('<span class="count"><i class="icon icon-collection"></i><span>收藏：'+data.collectionCount+'</span></span>').appendTo(topic_count_info);
    return topic_item;
}


	$(document).on("click", "#knowledgeload-more", function() {
		$(this).remove();
		loadKnowledge(fzqblog.knowledgePage + 1);

	});

function loadKnowledge(page){
    var topic_content = $("#knowledge-content");
    $('<div id="loading"> <div class="loading-con"><img src="' + fzqblog.realpath + '/resources/images/loading.gif"/><span>正在加载.......</span></div></div>').appendTo(topic_content);
    fzqblog.knowledgePage= page;
	    $.ajax({
	    	url: fzqblog.centerUrl.loadKnowledge,
	    	type: 'POST',
	    	dataType: 'json',
	    	data: { 
	    	 pageOn : page,
		   	 userId: fzqblog.center.userId
		   	},
		   	success:function(res){
			   	$("#loading").remove();
			    var list = res.data.list;
			    if (list.length == 0) {
				$("<span class='no-data'>没有知识文章</span>").appendTo(topic_content);
			    }
			    var simplePage = res.data.page;
			    for (var i = 0, _len = list.length, d; i < _len, d = list[i]; i++) {
				new knowledgeItem(d).appendTo(topic_content);
			    }
			   
			    if(simplePage.pages>simplePage.current){
				 	$('<div id="knowledgeload-more" class="load-more"><a href="javascript:;">⇓加载更多</a></div>').appendTo(topic_content);
					}
			   	}
		
	    });
}




/********粉丝，关注*********/

function loadFans(){
    var topic_content = $("#fans-content");
    $('<div id="loading"> <div class="loading-con"><img src="' + fzqblog.realpath + '/resources/images/loading.gif"/><span>正在加载.......</span></div></div>').appendTo(topic_content);
    $.ajax({
	 url : fzqblog.centerUrl.loadFans, 
	 type: 'POST',
	 dataType: 'json',
		data : {
	    	friendUserId:fzqblog.center.userId
		}, 
		success:function(res) {
	    $("#loading").remove();
	    topic_content.empty();
	    var list = res.data.list;
	    if (list.length == 0) {
		$("<span class='no-data'>没有粉丝</span>").appendTo(topic_content);
	    }
	    for (var i = 0, _len = list.length, d; i < _len, d = list[i]; i++) {
		$("<li><a href='"+fzqblog.realpath+"/user/"+d.userId+"'><img  src='"+fzqblog.realpath+"/resources/images/"+d.userIcon+"'><span>"+d.userName+"</span></a></li>").appendTo(topic_content);
	    }
	}
    });
}

function loadFocus(){
    var topic_content = $("#focus-content");
    $('<div id="loading"><div class="loading-con"><img src="' + fzqblog.realpath + '/resources/images/loading.gif"/><span>正在加载.......</span></div></div>').appendTo(topic_content);
    $.ajax({
    	url : fzqblog.centerUrl.loadFoucs, 
		type: 'POST',
		dataType: 'json',	
		data:{
		    userId:fzqblog.center.userId
		}, 
		success:function(res){
			 $("#loading").remove();
		    topic_content.empty();
		    var list = res.data.list;
		    if (list.length == 0) {
			$("<span class='no-data'>没有关注的人</span>").appendTo(topic_content);
		    }
		    for (var i = 0, _len = list.length, d; i < _len, d = list[i]; i++) {
			$("<li><a href='"+fzqblog.realpath+"/user/"+d.friendUserId+"'><img   src='"+fzqblog.realpath+"/resources/images/"+d.friendUserIcon+"'><span>"+d.friendUserName+"</span></a></li>").appendTo(topic_content);
		    }
		}
    });
}