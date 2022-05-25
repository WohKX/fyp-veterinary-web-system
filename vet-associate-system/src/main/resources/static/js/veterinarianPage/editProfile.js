/**
 * 
 */

$(function() {
	$('#saveButton').prop('disabled', true);
	$('#email').focus();
	$('#email')
		.bind('keyup change blur', function() {
			if ($('#email').val() != '') {
				var emailErr = $('#emailErr').html();
				if (emailErr == '') {
					$('#saveButton').prop('disabled', false);
				} else {
					$('#saveButton').prop('disabled', true);
				}
			} else {
				$('#saveButton').prop('disabled', true);
			}
		});

	$('#changePasswButton').attr('disabled', true);
	$('#oldPassw, #newPassw, #conPassw').bind('keyup change blur', function() {
		if (passwFilled()) {
			$('#changePasswButton').prop('disabled', false);
		} else {
			$('#changePasswButton').prop('disabled', true);
		}
	});

	function passwFilled() {
		var filled = true;
		$('#oldPassw, #newPassw, #conPassw').each(function() {
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
		var msg1 = $('#msg1').html();
		var msg2 = $('#msg2').html();
		var msg3 = $('#msg3').html();
		if ((msg1 + msg2 + msg3) != '') {
			noErr = false;
		}
		return noErr;
	}
});

function valEmail() {
	if (!(document.getElementById("email").value.includes("@") && (document
		.getElementById("email").value.endsWith(".com") || document
			.getElementById("email").value.endsWith(".my")))) {
		document.getElementById("emailErr").textContent = "Please key in a valid email.";
	} else {
		document.getElementById("emailErr").textContent = "";
	}
}

function toggleChangePasswDiv() {
	if (document.getElementById("changePasswDiv").style.display == 'none') {
		document.getElementById("changePasswDiv").style.display = 'block';
	} else {
		document.getElementById("changePasswDiv").style.display = 'none';
	}
}

function valPassword() {
	if (document.getElementById("oldPassw").value.length < 8) {
		document.getElementById("msg1").textContent = "The length of old password should be within 8-25 characters.";
	} else {
		document.getElementById("msg1").textContent = "";
	}

	if (document.getElementById("msg1").textContent == "") {
		if (document.getElementById("newPassw").value.length < 8) {
			document.getElementById("msg2").textContent = "The length of new password must be within 8-25 characters.";
		} else {
			document.getElementById("msg2").textContent = "";
		}
		if (document.getElementById("msg2").textContent == "") {
			if (document.getElementById("newPassw").value != document
				.getElementById("conPassw").value) {
				document.getElementById("msg3").textContent = "Confirm your password carefully.";
			} else {
				document.getElementById("msg3").textContent = "";
			}
		}
	}
}