/**
 * 
 */
$(function() {
	$('#saveButton').prop('disabled', true);
	$("#chName").focus();
	$('#chName, #street, #poscode, #district, #states, #phoneNo')
		.bind('keyup change blur', function() {
			if (allFilled()) {
				$('#saveButton').prop('disabled', false);
			} else {
				$('#saveButton').prop('disabled', true);
			}
		});

	function allFilled() {
		var filled = true;
		$('#chName, #street, #poscode, #district, #states').each(function() {
			if ($(this).val() == '')
				filled = false;
		});
		return filled;
	}
});