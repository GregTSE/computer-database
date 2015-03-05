(document).ready(function() {
	$("#add").click(function(e) {
		
		var name = $("#name").val();
		
		//if (name == '') {
			e.preventDefault();
			$("#name").css("border", "solid");
			$("#name").css("border-color", "#ff0000");
			alert("Name is required.");
	//	}
//		} else if ($("#introduced").val() != '') {
//		}
	});
});
