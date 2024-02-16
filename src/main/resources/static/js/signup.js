document.addEventListener('DOMContentLoaded', function() {
    const regionSelect = document.getElementById('region');
    const subregionSelect = document.getElementById('subregion');

    const regions = {
        'seoul': ['노원구', '강남구', '서초구', '송파구', '영등포구', '구로구', '도봉구', '은평구'],
        'gyeonggi': ['수원시', '성남시', '용인시', '파주시', '평택시', '의정부시', '동두천시']
    };

    regionSelect.addEventListener('change', function() {
        const selectedRegion = regionSelect.value;
        const subregions = regions[selectedRegion] || [];
        
        subregionSelect.innerHTML = '<option value="">먼저 지역을 선택해주세요</option>';
        
        subregions.forEach(function(subregion) {
            const option = document.createElement('option');
            option.value = subregion.toLowerCase().replace(/ /g, '');
            option.textContent = subregion;
            subregionSelect.appendChild(option);
        });
    });
});

function signup(){
	alert("회원가입이 완료되었습니다.");
	window.location.href="/user/login"
}

const usernameDuplicate = () =>{
	const dupleName = document.getElementById("username").value;
	const checkResult = document.getElementById("username_duplicate");
	const signupButton = document.getElementById("signupButton");
	$.ajax({
		type: "post",
		dataType:"text",
		url: "/user/usernameCheck",
		data: {
			"username":dupleName
		},
		success: function(result){
			if(result =="ok"){
				checkResult.innerHTML="사용가능합니다";
				checkResult.style.color="green";
				signupButton.disabled = false;
			}else{
				checkResult.innerHTML="이미 존재하는 아이디입니다";
				checkResult.style.color="red";
				signupButton.disabled = true;
			}
		},
		
		error: function(){
			alert("ajax실패");
		}
	});
}


