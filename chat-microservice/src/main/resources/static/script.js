'use strict'
var socket = new SockJS('/ws'); // Reemplaza con la URL correcta
var stompClient = Stomp.over(socket);
/*
stompClient.connect({}, function (frame) {
    console.log('Conectado al WebSocket: ' + frame);
    stompClient.subscribe('/topic/chat', function (notification) {
        console.log(notification)
        var message = JSON.parse(notification.body);
        mostrarMensajeEnChat(message);
    });
});*/
stompClient.subscribe('/topic/chat', function (notification) {
    console.log(notification);
    var message = JSON.parse(notification.body);
    mostrarMensajeEnChat(message);
});

function enviarMensaje() {
    var senderId = document.getElementById('senderId').value;
    var receiverId = document.getElementById('receiverId').value;
    var messageContent = document.getElementById('messageContent').value;

    var message = {
        senderId: senderId,
        receiverId: receiverId,
        content: messageContent
    };

    // Enviar el mensaje al backend (AJAX, fetch, etc.)
    // Por ejemplo, podrías usar fetch y la ruta correspondiente para enviar el mensaje
    fetch('http://localhost:8080/api/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(message)
    });
}

function mostrarMensajeEnChat(message) {
    console.log("en funcion "+message)
    var chatList = document.getElementById('chatList');
    var messageListItem = document.createElement('li');
    messageListItem.textContent = `${message.senderId} -> ${message.receiverId}: ${message.content}`;
    chatList.appendChild(messageListItem);

    // Forzar actualización del scroll para mostrar el último mensaje
    chatList.scrollTop = chatList.scrollHeight;
}