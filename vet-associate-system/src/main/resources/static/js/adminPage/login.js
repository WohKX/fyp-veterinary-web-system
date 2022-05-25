/**
 * 
 */
$(function() {
	$('#submitButton').prop('disabled', true);
	$('#userId').focus();
	$('#userId, #password')
		.bind('keyup change blur', function() {
			if (allFilled()) {
				$('#submitButton').prop('disabled', false);
			} else {
				$('#submitButton').prop('disabled', true);
			}
		});

	function allFilled() {
		var filled = true;
		$('body input').each(function() {
			if ($(this).val() == '')
				filled = false;
		});
		if (filled) {
			return valErr();
		}
		return filled;
	}

	function valErr() {
		var noErr = true;
		var passwordErr = $('#passwordErr').html();
		if ((passwordErr) != '') {
			noErr = false;
		}
		return noErr;
	}
});

var password = document.getElementById("password");
var passwordErr = document.getElementById("passwordErr");

var button = document.getElementById("submitButton");

function valPassword() {
	if (password.value.length < 8) {
		passwordErr.textContent = "The length of the password must be 8-25 characters.";
	} else {
		passwordErr.textContent = "";
	}
}
