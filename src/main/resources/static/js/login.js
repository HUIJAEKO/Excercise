$(document).ready(function() {
  $('.container').fadeIn(1000); // 1초 동안 부드럽게 나타남
});


window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            var error = urlParams.get('error');
            var message = urlParams.get('message');
            if (error && message) {
                alert(decodeURIComponent(message));
            }
        };