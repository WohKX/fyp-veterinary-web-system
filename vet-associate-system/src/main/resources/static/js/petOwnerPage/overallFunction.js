/**
 * 
 */

document.getElementById("navOwnerVets").style.display = 'none';
document.getElementById("navOwnerCH").style.display = 'none';

function toggleVCHList() {
	if (document.getElementById("navOwnerVets").style.display == 'none') {
		document.getElementById("navOwnerVets").style.display = 'block';
		document.getElementById("navOwnerCH").style.display = 'block';

	} else {
		document.getElementById("navOwnerVets").style.display = 'none'
		document.getElementById("navOwnerCH").style.display = 'none'

	}
}

document.getElementById("navOwnerProfile").style.display = 'none';
document.getElementById("navOwnerInquiry").style.display = 'none';

function togglePersonalList() {
	if (document.getElementById("navOwnerProfile").style.display == 'none') {
		document.getElementById("navOwnerProfile").style.display = 'block';
		document.getElementById("navOwnerInquiry").style.display = 'block';

	} else {
		document.getElementById("navOwnerProfile").style.display = 'none'
		document.getElementById("navOwnerInquiry").style.display = 'none';

	}
}

const countMessageURL = window.location.protocol + "//" + window.location.host + "/messages/count";

function countNewMessage() {
	$.ajax({
		url: countMessageURL,
		headers: {  'Access-Control-Allow-Origin': '*' },
		type: "GET",
		success: function(result) {
			console.log("counting message");
			const notificationBadge = document.getElementById("notificationBadge");
			if (result.messageCount == 0) {
				notificationBadge.style.display = 'none';
			} else {
				notificationBadge.style.display = 'block';
				while (notificationBadge.firstChild) {
					notificationBadge.removeChild(notificationBadge.firstChild);
				}
				notificationBadge.appendChild(document.createTextNode(result.messageCount));
			}
		},
		error: function(error) {
			console.log('Error ' + error);
		}
	});
}

setInterval(function() {
	countNewMessage();
}, 15000);