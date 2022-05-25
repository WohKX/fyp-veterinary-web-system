/**
 * 
 */

const countMessageURL = window.location.protocol + "//" + window.location.host + "/messages/count";

function countNewMessage() {

	$.ajax({
		url: countMessageURL,
		headers: {  'Access-Control-Allow-Origin': '*' },
		type: "GET",
		success: function(result) {
			const notificationBadge = document.getElementById("notificationBadge");
			console.log(result.messageCount);
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