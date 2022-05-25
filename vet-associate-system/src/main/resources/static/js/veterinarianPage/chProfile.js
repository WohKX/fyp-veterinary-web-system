/**
 * 
 */

$(function() {
	$('#submitButton').prop('disabled', true);
	$('#description, #provenImage')
		.bind('keyup change blur', function() {
			if (allFilled()) {
				$('#submitButton').prop('disabled', false);
			} else {
				$('#submitButton').prop('disabled', true);
			}
		});

	function allFilled() {
		var filled = true;
		$('#description, #provenImage').each(function() {
			if ($(this).val() == '')
				filled = false;
		});
		return filled;
	}
});

function formToggle() {
	if (document.getElementById("formDiv").style.display == 'block') {
		document.getElementById("formDiv").style.display = 'none';
		document.getElementById("commentDiv").style.display = 'block';
	} else {
		document.getElementById("formDiv").style.display = 'block';
		document.getElementById("commentDiv").style.display = 'none';
	}
}