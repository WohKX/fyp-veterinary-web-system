/**
 * 
 */

const receiveMessageURL = window.location.protocol + "//" + window.location.host + "/messages/receive";
const sendMessageURL = window.location.protocol + "//" + window.location.host + "/messages/send";

function receiveMessage(activeUser) {
	let dataToBeSend = { currentUser: activeUser };
	console.log("receiving message");
	$.ajax({
		url: receiveMessageURL,
		headers: {  'Access-Control-Allow-Origin': '*' },
		type: "POST",
		data: dataToBeSend,
		dataType: 'json',
		success: function(result) {
			if (result.count > 0) {
				let messages = result.messages;
				const chatboxDiv = document.getElementById("chatbox");
				const newMessageDiv = document.createDocumentFragment();
				const scrollDiv = document.getElementById("chatboxContainer");

				let new_row = document.createElement('div');
				let new_col = document.createElement('div');
				let new_text = document.createElement('div');
				let message_text = document.createElement('p');
				let message_image = document.createElement('img');
				let message_time = document.createElement('p');

				new_row.className = "row justify-content-start mb-2";
				new_col.className = "col-12 col-sm-7 justify-content-start";
				new_text.className = "d-flex flex-row bd-highlight";
				message_time.className = "p-2 bd-highlight fs-6";
				

				messages.map(function(message) {
					var messageType = message.messageType;
					if (messageType == 'TEXT') {
						message_text.className = "fs-5";
						message_text.innerHTML = message.messageText;
						new_text.appendChild(message_text);
					} else {
						message_image.style = "width: 480px;";
						message_image.src = message.messageImage;
						new_text.appendChild(message_image);
					}

					message_time.innerHTML = message.timeString;
					new_text.appendChild(message_time);
					new_col.appendChild(new_text);
					new_row.appendChild(new_col);
					newMessageDiv.appendChild(new_row);
				});
				chatboxDiv.appendChild(newMessageDiv);
				scrollDiv.scrollTop = scrollDiv.scrollHeight;

			}
		},
		error: function(error) {
			console.log('Error ' + error);
		}
	});
}


function sendText(contentText, activeUser) {
	let dataToBeSend = { messageContent: contentText, messageType: "TEXT", currentUser: activeUser };
	$.ajax({
		url: sendMessageURL,
		headers: {  'Access-Control-Allow-Origin': '*' },
		type: "POST",
		data: dataToBeSend,
		dataType: 'json',
		success: function(result) {
			let message = result.message;
			const chatboxDiv = document.getElementById("chatbox");
			const newMessageDiv = document.createDocumentFragment();

			let new_row = document.createElement('div');
			let new_col = document.createElement('div');
			let new_text = document.createElement('div');
			let message_text = document.createElement('p');
			let message_time = document.createElement('p');
			new_row.className = "row justify-content-end mb-2";
			new_col.className = "col-12 col-sm-7 justify-content-end";
			new_text.className = "d-flex flex-row-reverse bd-highlight";
			message_text.className = "fs-5";
			message_time.className = "p-2 bd-highlight fs-6";

			message_text.innerHTML = message.messageText;
			message_time.innerHTML = message.timeString;
			new_text.appendChild(message_text);
			new_text.appendChild(message_time);
			new_col.appendChild(new_text);
			new_row.appendChild(new_col);
			newMessageDiv.appendChild(new_row);
			chatboxDiv.appendChild(newMessageDiv);
			initializing();
		},
		error: function(error) {
			console.log('Error ' + error);
		}
	});
}

function sendImage(contentImage, activeUser) {
	let dataToBeSend = { messageContent: contentImage, messageType: "IMAGE", currentUser: activeUser };
	$.ajax({
		url: sendMessageURL,
		headers: {  'Access-Control-Allow-Origin': '*' },
		type: "POST",
		data: dataToBeSend,
		dataType: 'json',
		success: function(result) {
			let message = result.message;
			const chatboxDiv = document.getElementById("chatbox");
			const newMessageDiv = document.createDocumentFragment();

			let new_row = document.createElement('div');
			let new_col = document.createElement('div');
			let new_text = document.createElement('div');
			let new_img = document.createElement('img');
			let message_time = document.createElement('p');
			new_row.className = "row justify-content-end mb-2";
			new_col.className = "col-12 col-sm-7 justify-content-end";
			new_text.className = "d-flex flex-row-reverse bd-highlight";
			new_img.style = "width: 480px;";
			message_time.className = "p-2 bd-highlight fs-6";

			message_time.innerHTML = message.timeString;
			new_img.src = message.messageImage;
			new_text.appendChild(new_img);
			new_text.appendChild(message_time);
			new_col.appendChild(new_text);
			new_row.appendChild(new_col);
			newMessageDiv.appendChild(new_row);
			chatboxDiv.appendChild(newMessageDiv);
			initializing();
		},
		error: function(error) {
			console.log('Error ' + error);
		}
	});
}

function sendContent(activeUser) {
	if (document.getElementById("contentText").value == "") {
		return processImage(activeUser);
	} else if (document.getElementById("contentImage").value == "") {
		return sendText(document.getElementById("contentText").value, activeUser);
	}
}


function processImage(activeUser) {
	const file = document.querySelector('input[type=file]').files[0];
	const reader = new FileReader();

	reader.addEventListener("load", function() {
		console.log(reader.result);
		sendImage(reader.result, activeUser);
	}, false);

	reader.readAsDataURL(file);
}

function initializing() {
	const scrollDiv = document.getElementById("chatboxContainer");
	const contentText = document.getElementById("contentText");
	const contentImage = document.getElementById("contentImage");
	const textInputDiv = document.getElementById("textInputDiv");
	const imageInputDiv = document.getElementById("imageInputDiv");
	const sendButton = document.getElementById("sendButton");

	contentText.value = "";
	contentImage.value = "";
	scrollDiv.scrollTop = scrollDiv.scrollHeight;
	textInputDiv.className = "col-12 col-sm-6";
	imageInputDiv.className = "col-12 col-sm-4";
	contentText.style.display = "block";
	contentImage.style.display = "block";
	sendButton.disabled = true;

}
