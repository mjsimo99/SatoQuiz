let stompClient = null;

function connect() {
    const socket = new SockJS('http://localhost:8080/ws'); // Use the full WebSocket URL
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/1000', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
        console.log("Disconnected");
    }
}

function sendMessage() {
    const content = document.getElementById('messageInput').value;
    const salonId = 1000; // Replace with the actual salonId
    const studentId = 6; // Replace with the actual studentId

    stompClient.send("/app/chat.sendMessage/" + salonId, {}, JSON.stringify({
        content: content,
        studentId: studentId,
        salonId: salonId,
        timestamp: new Date()
    }));
}

function showMessage(message) {
    const messages = document.getElementById('messages');
    const li = document.createElement('li');
    li.textContent = message.content;
    messages.appendChild(li);
}

document.addEventListener('DOMContentLoaded', function () {
    connect();
});

window.addEventListener('beforeunload', function () {
    disconnect();
});
