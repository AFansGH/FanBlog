$(document).ready(function() {
	$("#publicTopicBtn").click(function(event) {
		if(fzqblog.userId=="" || fzqblog.userId == 0){
    		goLogin();
    	    return;
    	}
		document.location.href = fzqblog.realpath + "/bbs/prePublicTopic";
	});
});