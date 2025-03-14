document.addEventListener("DOMContentLoaded", function () {
    let chatBubble = document.getElementById("chatBubble");
    let chatBox = document.getElementById("chatBox");
    let closeChat = document.getElementById("closeChat");

    chatBubble.addEventListener("click", function () {
        chatBox.style.display = "block";
    });

    closeChat.addEventListener("click", function () {
        chatBox.style.display = "none";
    });
});
