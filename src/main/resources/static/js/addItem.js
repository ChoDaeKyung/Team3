$(document).ready(() => {

    $('#signup').click(() => {

        let userId = $('#userId').val();
        let password = $('#password').val();
        let userName = $('#userName').val();
        let email = $('#email').val();
        let phone = $('#phone').val();
        let address = $('#address').val();

        if(userId == "" || password == "" || userName == "" || email == ""
            || phone == "" || address == ""){
            alert('모든 정보를 기입해주세요.')
            return "redirect:/main/join"
        }

        let formData = {
            userId : userId,
            password : password,
            userName : userName,
            email : email,
            phone : phone,
            address : address
        }

        $.ajax({
            type: 'POST',
            url: '/join', // 서버의 엔드포인트 URL
            data: JSON.stringify(formData), // 데이터를 JSON 형식으로 변환
            contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
            dataType: 'json', // 서버에서 받을 데이터의 타입
            success: function(response) {
                // 성공 시 실행될 콜백 함수
                alert('회원가입이 성공했습니다.\n로그인해주세요.')
                // 성공 후 다른 페이지로 이동하거나 처리할 코드 작성 가능
                window.location.href = response.url;
            },
            error: function(error) {
                // 실패 시 실행될 콜백 함수
                console.error('오류 발생:', error);
                alert('회원가입 중 오류가 발생했습니다.');
                window.location.href = "/main/join"
            }
        });

    });



});