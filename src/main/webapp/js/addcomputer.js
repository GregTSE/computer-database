//Validator form
(function ( $ ) {

    $("#submit").click(function(e) {
    	if( $("#computerName").val().length == 0 ) {
    		alert("BOUM");
    	}
    });
    }(jQuery));
    
  