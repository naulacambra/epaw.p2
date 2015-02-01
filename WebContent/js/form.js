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
					$('.error_label[for="username"]').show();
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
		}, $('.error_label[for="mail"]'));
	});
	/* !Check Username */
	/*Check repeat password*/
	$('#pwd_check').keyup(function() {
		if($(this).val() != $('#pwd').val())
			$('.error_label[for="pwd_check"]').show();
		else
			$('.error_label[for="pwd_check"]').hide();
	});
	/*!Check repeat password*/
	/*Reset error labels*/
	$('input').focus(function(){
		$('label.error_label[for="' + $(this).attr('name')+ '"]').hide();
	});
	/*!Reset error labels*/
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
