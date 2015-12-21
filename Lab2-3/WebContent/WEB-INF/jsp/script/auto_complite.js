	$(document).ready(function() {
		$.get('/SummaryTask4/getStations', function(data) {
			$(".viewstations").autocomplete({
				source : data,
				minLength : 1
			});
		});
	});