jQuery(document).ready(function($) {
	/* Check Username */
	$('#username').focusout(function() {
		$.ajax({
			url : "formcontroller",
			type : "POST",
			data : {
				action: 'checkUsername',
				data: $('#username').val()
			},
			success : function(response) {
				console.log(response);
			}
		});
	});
	/* !Check Username */
});

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
