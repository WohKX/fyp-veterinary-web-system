/**
 * 
 */


$(function() {
	$('#submitButton').prop('disabled', true);
	$("#title").focus();
	$('#title, #description')
		.bind('keyup change blur', function() {
			if (allFilled()) {
				$('#submitButton').prop('disabled', false);
			} else {
				$('#submitButton').prop('disabled', true);
			}
		});

	function allFilled() {
		var filled = true;
		$('#title, #description').each(function() {
			if ($(this).val() == '')
				filled = false;
		});
		return filled;
	}

});

