$(document)
		.ready(
				function() {

					$('head')
							.append(
									'<link rel="stylesheet" Content-Type="css" href="/static/parisTabContribution/paris.css">');
					passEvent();
					function passEvent() {

						var div = '<div id="Paris" class="tabcontent"> <h3>Paris</h3> <p>Paris is the capital of France..</p></div>';
						$.event.trigger({
							type : "addContent",
							message : div
						});
					}

				});
