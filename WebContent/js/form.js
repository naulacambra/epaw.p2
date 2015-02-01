jQuery(document).ready(function($) {
	/* Check Username */
	$('#username').focusout(function() {
		ajaxCall({
			action : 'checkUsername',
			data : $('#username').val()
		}, $('.error[for="username"]'));
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

function ajaxCall(data, errorElement) {
	$.ajax({
		url : "ajaxcontroller",
		type : "POST",
		dataType : "json",
		data : data,
		success : function(response) {
			response = response[0];
			if (response.success) {
				console.log("no existe");
			} else {
				errorElement.show();
			}
		},
		error : function(response) {
			response = response[0];
			console.log("error");
			console.log(response);
		}
	});
}

/**
 * Ajax Example
 * 
 * $.ajax({ url: "index.php?option=com_inatal&task=checkGiftCode&format=raw",
 * type: "POST", data: $('#form-codigo-regal').serialize(), success:
 * function(response) { if (response.success) {
 * $("#dialog-form").dialog("close"); alert(response.message); } else {
 * alert(response.error_message); } } });
 * 
 */
