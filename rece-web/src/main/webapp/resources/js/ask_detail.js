$(document).ready(function() {
	$(document).on("mouseenter", ".comment-item", function() {
		$(this).find("a.accept-btn").show();
    });
   	 $(document).on("mouseleave", ".comment-item", function() {
		$(this).find("a.accept-btn").hide();
    });
    
    $(document).on("click", "a.accept-btn", function() {
		var commentid = $(this).attr("commentid");
		acceptAnswer(commentid);
    });
});

function acceptAnswer(commentid){
	$.ajax({
		url: fzqblog.realpath + '/ask/acceptAnswer',
		type: 'POST',
		dataType: 'json',
		data: {bestAnswerId : commentid,askId:fzqblog.topicId},
		success:function(res){
		if(res.errorMsg != null){
			layer.alert(res.errorMsg, {
			  icon: 5,
			  skin: 'layer-ext-moon' 
			});
			return;
			}
		else{
			layer.msg('采纳成功', {icon: 6,time:1000});
				location.reload(true);
			}
		}
	});
}