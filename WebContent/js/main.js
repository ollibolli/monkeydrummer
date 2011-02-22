/**
 * ollibolli 
 */
$(document).ready( function () {
	$("#hitIt").append($("<div class='pow'><h3>POW</h3></div>"));
	$(".pow").hide();
	$("#hitIt a").click(function(){
		$(".pow").show().slideUp(1000);
		$.get("/MonkeyServlet" , {drum : 1}, function(data){
			
		});
		return false;
	});
});

function init(){
	
};