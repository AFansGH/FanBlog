$(document).ready(function() {
	var url = fanblog.curUrl;
    if (url.indexOf("redirect") != -1) {
	fanblog.redirect = url.substring(url.indexOf("redirect") + 9, url.length);
    }
	$("ul.tag li").click(function(event) {
		$('ul.tag li').removeClass('active');
		$(this).addClass('active');

	});

	$("#dropdown").click(function(event) {
		if(fanblog.userId!=""){
        	 showMessageList();
         }
	});
	$(document).click(function(event) {
		$("#dropdownmenu").slideUp("slow");
	});

	$('#reset').click(function(event) {
		$('#username').val('');
		$('#email').val('');
		$('#password').val('');
		$('#confirmPassword').val('');
	});

	function checkForm(content, doc) {
		var d = dialog({
			content: content,
			align: 'right',
			quickClose: true // 点击空白处快速关闭
		});
		d.show(document.getElementById(doc));
	}



	$('#login').click(function(event) {
		var account = $('#account').val();
		var password = $('#password').val();
		if (account == null || $.trim(account) == '') {
			checkForm('用户名不能为空', 'account');
			$("#account").parent().addClass('has-error');
		} else if (password == null || $.trim(password) == '') {
			$("#account").parent().removeClass('has-error');
			checkForm('密码不能为空', 'password');
			$("#password").parent().addClass('has-error');
		} else {
			$("#password").parent().removeClass('has-error');
			var loadingindex = layer.load(0, {
			  shade: [0.1,'#fff'] //0.1透明度的白色背景
			});
			$.ajax({
				url: fanblog.realpath + '/login.do',
				type: 'POST',
				timeout : 1000,
				dataType: 'json',
				data: $('#loginform').serialize(),
				success: function(data) {
					layer.close(loadingindex);
					if (data.errorMsg == null) {
						var d = dialog({
							content: "<div><img src='" + fanblog.realpath +"/resources/images/loading.gif' />&nbsp;&nbsp;&nbsp;登录成功,跳转中...</div>",
						});
						d.showModal();
						setTimeout(function() {
							d.close().remove();
							if (null == fanblog.redirect || fanblog.redirect == "") {
								fanblog.redirect = fanblog.realpath;
							    }
							    document.location.href = fanblog.redirect;
						}, 1000);
					} else {
						var d = dialog({
							width: 200,
							content: data.errorMsg,
							quickClose: true // 点击空白处快速关闭
						});
						d.show();
					}
				}
			});


		}
	});

	
	$("#userheadicon").mouseenter(function(){
		$.ajax({
	 		url:  fanblog.realpath + '/loadSignInfo',
	 		type: 'POST',
	 		dataType: 'json',
	 		success:function(result){
	 			$(".usermark").text(result.data.mark);
	 			$(".usertotalsignin em").text(result.data.userSignInCount);
	 			$(".signindate").html(result.data.curYear+"&nbsp;年&nbsp;" + result.data.curMonth + "月<em>" + result.data.curDay + "</em>日");
	 			if(result.data.haveSignInToday){	 					
	 					$("#signinimage").removeClass("unsigninimage");
	 					$("#signinimage").addClass("signinimage");
	 			}
	 			else{	 					
 					$("#signinimage").removeClass("signinimage");
 					$("#signinimage").addClass("unsigninimage");
 			}
	 		}
	 	});
	  });
	$("#userheadicon").mouseover(function(){
	    $("#mypanel").show();   
	  });
	 $("#userheadicon").mouseleave(function(event) {
	 	 fanblog.showdialog_timeout = setTimeout(function(){
			$("#mypanel").hide();
	    },100);	 	
	 });
	 $("#mypanel").mouseenter(function(){
	 	clearTimeout(fanblog.showdialog_timeout);
	    $("#mypanel").show();   
	  });
	  $("#mypanel").mouseleave(function(){
	    $("#mypanel").hide();   
	  });
	 
	//进行签到
	 $(".unsigninimage").click(function(event) {
			$.ajax({
				url: fanblog.realpath + '/signIn',
				type: 'POST',
				dataType: 'json',
				success:function(result){
					if(result.responseCode == "SUCCESS"){
					var d = dialog({
					width: $(window).width(),
			   		height: $(window).height(),
					content: "<div><img src='" + fanblog.realpath +"/resources/images/signin.gif' /></div>",
				});
					d.showModal();
					setTimeout(function() {
						d.close().remove();
					}, 1200);
					}
					else{
						var d = dialog({
								width: 200,
								content: result.errorMsg,
								quickClose: true // 点击空白处快速关闭
							});
							d.show();
					}
				}
			});
			
		});
});
function showEmotion(targetObj, textarea){
	var d = dialog({
		width:300,
	    align: 'bottom left',
	    quickClose: true// 点击空白处快速关闭
	});
	var emotion_panel = $("<div></div>")
	var emotions = fanblog.emotion_data;
	for (var i = 0, _len = emotions.length; i < _len; i++) {
		var item = $("<div data=" + emotions[i] + " class='emotion' title=" + emotions[i] + "><img src='../resources/images/emotions/" + i + ".gif'></div>").appendTo(emotion_panel).bind("click", function() {
			d.close();
			textarea.val(textarea.val() + $(this).attr("data")).focus();
		});
	}
	d.content(emotion_panel);
	d.show(targetObj);
}
function showAtUser(targetObj, textarea){
	var d = dialog({
		width:380,
	    align: 'bottom left',
	    quickClose: true// 点击空白处快速关闭
	});
	var at_panel = $("<div></div>");
	$.ajax({
		url: fanblog.realpath + '/loadUserFriend',
		type: 'POST',
		dataType: 'json',
		data: {"pageNum": 1},
		success:function(res){
			if(res.errorMsg != null){
				layer.alert(res.errorMsg, {
				  icon: 5,
				  skin: 'layer-ext-moon' 
				});
			}
			else{
			var content = "";
			var list = res.data.list;
			var _len = list.length;
			if (_len == 0) {
				content = "没有关注的用户";
			} else {
				content = at_panel;
				for (var i = 0, item; i < _len, item = list[i]; i++) {
					$("<a href='javascript:;' class='at_user'><img src='" + fanblog.realpath +"/resources/images/" + item.friendUserIcon + "' class='img-thumbnail' style='width:40px;height:40px;'><span>" + item.friendUserName + "</span></a>").appendTo(content).click(function() {
						d.close();
						textarea.val(textarea.val()).focus().val(textarea.val() + "@" + $(this).text() + " ");
					});
				}
			}
			d.content(content);
			d.show(targetObj);
		}
		}
	});
	
}
