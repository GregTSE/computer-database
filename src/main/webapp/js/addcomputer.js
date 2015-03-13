$(document).ready(function() {
	$("#add").click(function(e) {
		var name = $("#computerName").val();
		
		if (name == '') {
			e.preventDefault();
			$("#computerName").css("border", "solid");
			$("#computerName").css("border-color", "#ff0000");
			alert("Name is required.");
		} else if ($("#introduced").val() != '') {
		}
	});
});



