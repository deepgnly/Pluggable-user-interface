$(document)
		.ready(
				function() {
				

					$('head').append('<link rel="stylesheet" type="text/css" href="/static/londonTabContribution/london.css">');
					passEvent();
					function passEvent() {

						var div = '<div id="London" class="tabcontent"> <h3>Londonn</h3> <p>London is the capital city of England.</p></div>';
						$.event.trigger({
							type : "addContent",
							message : div
						});
					}

				});
