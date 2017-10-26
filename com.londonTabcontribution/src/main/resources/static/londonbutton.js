$(document).ready(function(){
	
	
	$.ajax({
		dataType : "script",
		url : "/script/londonTabContribution/london.css"
	});
	
	var div='<div id="London" class="tabcontent"> <h3>London</h3> <p>London is the capital city of England.</p></div>';
	$.event.trigger({
		type: "addContent",
		message: div
	});
	
	
});
