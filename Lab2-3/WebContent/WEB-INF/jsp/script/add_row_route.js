$(document)
		.ready(
				function() {
					$.get('/SummaryTask4/getStations', function(data) {
						localStorage.setItem('data', JSON.stringify(data));
					});
					var i = 1;
					$('.extrabutton')
							.click(
									function() {
										$('#stations')
												.append(
														'<tr>'
																+ '<td><fmt:message key="add_new_route_page.station"/>:</td><td><input name="station'
																+ i
																+ '" value=""maxlength="15" class="viewstations ui-autocomplete-input" required /></td>'
																+ '<td><fmt:message key="add_new_route_page.travel_time"/>:</td><td><input name="travelTime'
																+ i
																+ '" value="10" type="number" max ="5000" min="1" required /></td>'
																+ '<td><fmt:message key="add_new_route_page.stop_time"/></td><td><input name="stopTime'
																+ i
																+ '" value="2" type="number" max ="5000" required /></td><td>'
																+ '</tr>');
										i++;
										load();
									});
					load();
				});

function load() {
	var stations = localStorage.getItem('data');
	console.log(stations);
	$(".viewstations").autocomplete({
		source : JSON.parse(stations),
		minLength : 1
	});
}

// setInterval(load, 1000);
