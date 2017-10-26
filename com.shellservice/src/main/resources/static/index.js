$(document).ready(function() {
	
	$(document).on("addContent", attachContentToUi);

	$.ajax({
		dataType : "json",
		url : "/getAllTabContribution",
		success : onAllTabContributionReceived
	});

	function onAllTabContributionReceived(data) {		
		for(var key in  data){
			let contributionObj=data[key];
			$("#buttons").append(constructEachTab(contributionObj.tabName,contributionObj.tabResource))
			
		}
	}
	
	function constructEachTab(tabValue,contentResource){
		var button = document.createElement("button");
		button.className = "tablink";
		button.innerText=tabValue;
		button.data=contentResource;
		button.resourceFetched=null;		
		button.addEventListener ("click", onEachTabClicked);
		
		return button
	}
	
	function onEachTabClicked(evnt){
		
		$.ajax({
			dataType : "script",
			url : evnt.target.data,
			cache: true
		});
	}
	
	function attachContentToUi(content){
		
		$("#tabContainer").empty();
		$("#tabContainer").append(content.message);
	}

});