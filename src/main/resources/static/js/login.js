$(document).ready(function() {
  $('.container').fadeIn(1000); // 1초 동안 부드럽게 나타남
});

document.addEventListener('DOMContentLoaded', function () {
    var messageElement = document.getElementById('loginMessage');
    // messageElement가 존재하고, data-message 속성이 있는지 확인
    if (messageElement && messageElement.dataset.message) {
        var message = messageElement.dataset.message;
        var messageType = messageElement.dataset.type; // 'error'

        // 메시지 타입이 'error'인 경우에만 경고창 띄우기
        if (messageType === 'error') {
            Swal.fire({
                title: '로그인 오류',
                text: message,
                icon: messageType, // 'error'
                confirmButtonText: '닫기'
            });
        }
    }
});

