function confirmDelete() {
    var confirmation = confirm("정말로 탈퇴하시겠습니까?");
    if (confirmation) {
            $.ajax({
                url: '/user/delete', 
                type: 'DELETE',
                success: function(result) {
                    alert("탈퇴 처리가 완료되었습니다.");
                    window.location.href = '/user/login'; 
                },
                error: function(xhr, status, error) {
                    alert("탈퇴 처리 중 문제가 발생했습니다.");
                }
            });
        }
    }
    



