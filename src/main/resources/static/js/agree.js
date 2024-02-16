function submitForm() {
	var agreeTerms = document.getElementById('agreeTerms').checked;
	var agreePrivacy = document.getElementById('agreePrivacy').checked;

	if (agreeTerms && agreePrivacy) {
		// 모든 약관에 동의했다면, 폼 제출 로직을 추가
		alert('약관에 동의하였습니다.');
		window.location.href="/user/signup";
	} else {
		alert('모든 약관에 동의해야 합니다.');
	}
}
