$(document).ready(function() {
	$("#edit").click(function(e) {
		var name = $("#name").val();
		var introducedError = !isValidDate($("#dateIntroduced").val());
		var discontinuedError = !isValidDate($("#dateDiscontinued").val())
		$("#name").css("border", "none");
		if (name.length < 1) {
			e.preventDefault();
			$("#name").css("border", "solid");
			$("#name").css("border-color", "#ff0000");
			alert(strings['error.name']);
		} else {
			if (introducedError || discontinuedError) {
				if (introducedError) {
					$("#dateIntroduced").css("border", "solid");
					$("#dateIntroduced").css("border-color", "#ff0000");
				}	
				if (discontinuedError) {
					$("#dateDiscontinued").css("border", "solid");
					$("#dateDiscontinued").css("border-color", "#ff0000");
				}
				e.preventDefault();
				alert(strings['error.date']);
			}	
		}
	});
});

(function($) {
	$.fn.initName = function() {
			$("#name").css("border", "none");
	};
}(jQuery));

(function($) {
	$.fn.initIntroduced = function() {
			$("#dateIntroduced").css("border", "none");
	};
}(jQuery));

(function($) {
	$.fn.initDiscontinued = function() {
			$("#dateDiscontinued").css("border", "none");
	};
}(jQuery));


function isValidDate(dateString) {
	if (dateString == '') {
		return true;
	}
	dateArray = dateString.split("-");
	var y = dateArray[0];
	var m = dateArray[1];
	var d = dateArray[2];
	var date = new Date(y,m-1,d);
	var currentMonth = date.getMonth()+1;
	var currentDay = date.getDate();
	if (currentMonth < 10) { currentMonth = '0' + currentMonth; }
	if (currentDay < 10) { currentDay = '0' + currentDay; }

	var convertedDate =""+date.getFullYear() + currentMonth + currentDay;
	var givenDate = "" + y + m + d;
	return ( givenDate == convertedDate);
}