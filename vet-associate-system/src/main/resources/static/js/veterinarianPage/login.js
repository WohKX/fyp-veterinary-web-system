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
		var userIdErr = $('#userIdErr').html();
		var passwordErr = $('#passwordErr').html();
		if ((userIdErr + passwordErr) != '') {
			noErr = false;
		}
		return noErr;
	}
});

var userId = document.getElementById("userId");
var userIdErr = document.getElementById("userIdErr");

var password = document.getElementById("password");
var passwordErr = document.getElementById("passwordErr");

var button = document.getElementById("submitButton");

function valUserId() {
	if (userId.value.length != 14) {
		userIdErr.textContent = "Please insert your IC including '-'.";
	} else {
		userIdErr.textContent = "";
	}
}

function valPassword() {
	if (password.value.length < 8) {
		passwordErr.textContent = "The length of the password must be 8-25 characters.";
	} else {
		passwordErr.textContent = "";
	}
}