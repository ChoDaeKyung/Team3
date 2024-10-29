$(document).ready(() => {
    $('#findId').click((event) => {

        event.preventDefault();

        // 사용자가 입력한 값 가져오기
        let userName = $('#userName').val();
        let phone = $('#phone').val();

        let formData = {
            userName: userName,
            phone: phone
        }

        // 서버에 AJAX 요청 보내기
        $.ajax({
            type: 'POST',
            url: '/findId', // 서버의 엔드포인트 URL
            data: JSON.stringify(formData), // 데이터를 JSON 형식으로 변환
            contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
            dataType: 'json', // 서버에서 받을 데이터의 타입
            success: (response) => {
                console.log('res :: ', response);
                // 서버가 성공적으로 아이디를 반환한 경우
                alert(response.message + '\n귀하의 아이디는 ' + response.userId + " 입니다.\n로그인 페이지로 이동합니다.");
                 window.location.href = response.url; // 로그인 페이지로 이동
            },
            error: (xhr, err) => {
                console.log('find user :: ', err)
                // 서버가 에러를 반환한 경우
                if (xhr.status === 404) {
                    alert('일치하는 사용자 정보가 없습니다.');
                } else {
                    alert('오류가 발생했습니다. 다시 시도해 주세요.');
                }
            }
        });
    });


});