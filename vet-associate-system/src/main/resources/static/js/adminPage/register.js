/**
 * 
 */

$(function() {
	$('#submitButton').prop('disabled', true);
	$("#username").focus();
	$('#username, #ICsec1, #ICsec2, #ICsec3, #password, #password2')
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
		var ICErr = $('#ICErr').html();
		var passwordErr = $('#passwordErr').html();
		var passwordErr2 = $('#passwordErr2').html();
		if ((usernameErr + ICErr + passwordErr + passwordErr2) != '') {
			noErr = false;
		}
		return noErr;
	}

	$("#ICsec1").bind("keyup", function() {
		var ICsec1 = $("#ICsec1").val();
		if (ICsec1.length == 6) {
			$('#ICsec2').focus();
		}
	});

	$("#ICsec2").bind("keyup", function() {
		var ICsec2 = $("#ICsec2").val();
		if (ICsec2.length == 2) {
			$('#ICsec3').focus();
		}
	});

});

function valUsername() {
	if (document.getElementById("username").value == "") {
		document.getElementById("usernameErr").textContent = "Please key in your name.";
	} else {
		document.getElementById("usernameErr").textContent = "";
	}
}

function valIC() {
	if (document.getElementById("ICsec3").value.length != 0) {
		if (document.getElementById("ICsec1").value.length != 6
			|| document.getElementById("ICsec2").value.length != 2
			|| document.getElementById("ICsec3").value.length != 4) {
			document.getElementById("ICErr").textContent = "Please check your IC carefully!";
		} else {
			document.getElementById("ICErr").textContent = "";
		}
	} else if (document.getElementById("ICsec2").value.length != 0) {
		if (document.getElementById("ICsec1").value.length != 6
			|| document.getElementById("ICsec2").value.length != 2) {
			document.getElementById("ICErr").textContent = "Please check your IC carefully!";
		} else {
			document.getElementById("ICErr").textContent = "";
		}
	} else {
		if (document.getElementById("ICsec1").value.length != 6) {
			document.getElementById("ICErr").textContent = "Please check your IC carefully!";
		} else {
			document.getElementById("ICErr").textContent = "";
		}
	}
}

function valIC2() {
	if (document.getElementById("ICsec3").value.length != 0) {
		if (document.getElementById("ICsec1").value.length != 6
			|| document.getElementById("ICsec2").value.length != 2
			|| document.getElementById("ICsec3").value.length != 4) {
			document.getElementById("ICErr").textContent = "Please check your IC carefully!";
		} else {
			document.getElementById("ICErr").textContent = "";
		}
	} else {
		if (document.getElementById("ICsec1").value.length != 6
			|| document.getElementById("ICsec2").value.length != 2) {
			document.getElementById("ICErr").textContent = "Please check your IC carefully!";
		} else {
			document.getElementById("ICErr").textContent = "";
		}
	}

}

function valIC3() {
	if (document.getElementById("ICsec1").value.length != 6
		|| document.getElementById("ICsec2").value.length != 2
		|| document.getElementById("ICsec3").value.length != 4) {
		document.getElementById("ICErr").textContent = "Please check your IC carefully!";
	} else {
		document.getElementById("ICErr").textContent = "";
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