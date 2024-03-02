document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.clickable-row').forEach(row => {
        row.addEventListener('click', () => {
            const postId = row.getAttribute('data-post-id');
            window.location.href = '/post-detail?postId=' + postId;
        });
    });
});