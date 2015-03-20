$(document).ready(function() {
	$("#add").click(function(e) {
		var name = $("#name").val();
		$("#name").css("border", "none");
		if (name.length < 2) {
			e.preventDefault();
			$("#name").css("border", "solid");
			$("#name").css("border-color", "#ff0000");
			alert("Name is required.");
		} else {
			if (!isValidDate($("#dateIntroduced").val())) {
				e.preventDefault();
				$("#dateIntroduced").css("border", "solid");
				$("#dateIntroduced").css("border-color", "#ff0000");
				alert("Bad format.");
			}	
			if (!isValidDate($("#dateDiscontinued").val())) {
				e.preventDefault();
				$("#dateDiscontinued").css("border", "solid");
				$("#dateDiscontinued").css("border-color", "#ff0000");
				alert("Bad format.");
			}	
		}
	});
});
//
//(function($) {
//	$.fn.checkvalue0 = function() {
//			$("#name").css("border", "none");
//	};
//}(jQuery));
//
//(function($) {
//	$.fn.checkvalue1 = function() {
//			$("#dateIntroduced").css("border", "none");
//	};
//}(jQuery));
//
//(function($) {
//	$.fn.checkvalue2 = function() {
//			$("#dateDiscontinued").css("border", "none");
//	};
//}(jQuery));


function isValidDate(dateString) {
	dateArray = dateString.split("/");
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