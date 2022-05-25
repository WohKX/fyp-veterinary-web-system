/**
 * 
 */
$(function() {
	$('#submitButton').prop('disabled', true);
	$('#username').focus();
	$('#username, #email, #password, #password2')
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
		var usernameErr = $('#usernameErr').html();
		var emailErr = $('#emailErr').html();
		var passwordErr = $('#passwordErr').html();
		var passwordErr2 = $('#passwordErr2').html();
		if ((usernameErr + emailErr + passwordErr + passwordErr2) != '') {
			noErr = false;
		}
		return noErr;
	}

});

function valUsername() {
	if (document.getElementById("username").value == "") {
		document.getElementById("usernameErr").textContent = "Please key in your name.";
	} else {
		document.getElementById("usernameErr").textContent = "";
	}
}

function valEmail() {
	if (!(document.getElementById("email").value.includes("@") && (document
		.getElementById("email").value.endsWith(".com") || document
			.getElementById("email").value.endsWith(".my")))) {
		document.getElementById("emailErr").textContent = "Please key in a valid email.";
	} else {
		document.getElementById("emailErr").textContent = "";
	}
}

function valPassword() {
	if (document.getElementById("password").value.length < 8) {
		document.getElementById("passwordErr").textContent = "The length of the password must be 8-25 characters.";
	} else {
		document.getElementById("passwordErr").textContent = "";
	}
}

function confirmPassword() {
	if (document.getElementById("password").value != document
		.getElementById("password2").value) {
		document.getElementById("passwordErr2").textContent = "Confirm your password carefully.";
	} else {
		document.getElementById("passwordErr2").textContent = "";
	}
}