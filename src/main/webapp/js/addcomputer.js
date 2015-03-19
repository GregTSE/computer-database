$(document).ready(function() {
	$("#add").click(function(e) {
		var name = $("#Pname").val();
		
		if (name == '') {
			e.preventDefault();
			$("#name").css("border", "solid");
			$("#name").css("border-color", "#ff0000");
			alert("Name is required.");
		}
	});
});



