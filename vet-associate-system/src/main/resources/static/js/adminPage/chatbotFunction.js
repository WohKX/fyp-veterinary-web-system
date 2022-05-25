/**
 * 
 */
$(function() {
	$('#saveButton').prop('disabled', true);
	$('#questions').focus();
	$('#questions, #answers')
		.bind('keyup change blur', function() {
			if (allFilled()) {
				$('#saveButton').prop('disabled', false);
			} else {
				$('#saveButton').prop('disabled', true);
			}
		});

	function allFilled() {
		var filled = true;
		$('#questions, #answers').each(function() {
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
		var questionsErr = $('#questionsErr').html();
		var answersErr = $('#answersErr').html();
		if ((questionsErr + answersErr) != '') {
			noErr = false;
		}
		return noErr;
	}

});

function valQuestions() {
	if (document.getElementById("questions").value == "") {
		document.getElementById("questionsErr").textContent = "Please key in question.";
	} else {
		document.getElementById("questionsErr").textContent = "";
	}
}

function valAnswers() {
	if (document.getElementById("answers").value == "") {
		document.getElementById("answersErr").textContent = "Please key in answer.";
	} else {
		document.getElementById("answersErr").textContent = "";
	}
}
