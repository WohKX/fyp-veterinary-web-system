/**
 * 
 */
$(function() {
	$('#submitButton').prop('disabled', true);
	$("#chName").focus();
	$('#chName, #street, #poscode, #district, #states, #phoneNo, #provenImage, #description')
		.bind('keyup change blur', function() {
			if (allFilled()) {
				$('#submitButton').prop('disabled', false);
			} else {
				$('#submitButton').prop('disabled', true);
			}
		});

	function allFilled() {
		var filled = true;
		$('#chName, #street, #poscode, #district, #states, #provenImage').each(function() {
			if ($(this).val() == '')
				filled = false;
		});
		return filled;
	}
});