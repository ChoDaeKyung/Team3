$(document).ready(() => {
    checkinfo();
    changePW();
});

let checkinfo = () => {
    $('#checkinfo').click(function () {
        // 필드에 값이 입력되었는지 확인
        var userName = $('#userName').val();
        var userId = $('#userId').val();
        var phone = $('#phone').val();

        if (userName && userId && phone) {
            // 입력 필드 비활성화 및 스타일 변경
            $('#userName, #userId, #phone').prop('disabled', true).addClass('disabled');

            // 비밀번호 입력 섹션 표시
            $('#password-section').show();
        } else {
            alert('모든 정보를 입력해주세요.'); // 필드가 비어있을 경우 경고
        }
    });
}

let changePW = () => {
    $('#changePW').click(() => {

        let userId = $('#userId').val();
        let userName = $('#userName').val();
        let phone = $('#phone').val();
        let password = $('#password').val();

        let formData = {
            userId: userId,
            userName: userName,
            phone: phone,
            password: password
        }
        $.ajax({
            type: 'PUT',
            url: '/changePw', // 서버의 엔드포인트 URL
            data: JSON.stringify(formData), // 데이터를 JSON 형식으로 변환
            contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
            dataType: 'json', // 서버에서 받을 데이터의 타입
            success: function (response) {
                // 성공 시 실행될 콜백 함수
                alert(response.message)
                // 성공 후 다른 페이지로 이동하거나 처리할 코드 작성 가능
                window.location.href = response.url;
            },
            error: function (error) {
                // 실패 시 실행될 콜백 함수
                console.error('오류 발생:', error);
                alert('비밀번호 수정 중 오류가 발생하였습니다.');
            }
        });
    });
}