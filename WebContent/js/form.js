jQuery(document).ready(function($) {
	/* Check Username */
	$('#username').focusout(function() {
		ajaxCall({
			action : 'checkUsername',
			data : $('#username').val()
		}, $('.error[for="username"]'), function(response) {
			response = parseResponse(response);
			if (response.success) {
				if (response.exists)
					$('.error[for="username"]').show();
				else
					console.log("no existe");
			} else {
				console.warn("Something went wrong");
			}
		});
	});
	/* !Check Username */
	/* Check Mail */
	$('#mail').focusout(function() {
		ajaxCall({
			action : 'checkMail',
			data : $('#mail').val()
		}, $('.error[for="mail"]'));
	});
	/* !Check Username */
});

function ajaxCall(data, errorElement, successFunction) {
	$.ajax({
		url : "ajaxcontroller",
		type : "POST",
		dataType : "json",
		data : data,
		success : successFunction,
		error : function(response) {
			response = response[0];
			console.log("error");
			console.log(response);
		}
	});
}

function parseResponse(response) {
	count = 0;
	new_response = {};
	response.forEach(function(element) {
		for (key in element) {
			new_response[key] = response[count][key];
			++count;
		}
	});
	return new_response;
}
