$(document).ready(() => {
    $('#logout').click(() => {
        alert('로그아웃 성공!!');
    });

    $('#searchButton').click(() => {
        const search = $('#searchInput').val().trim(); // 검색어 가져오기

        // 검색어가 비어있으면 요청을 보내지 않음
        if (search === "" || search === null) {
            alert("검색어를 입력해주세요.");
            return;
        }

        window.location.href = `/main/search?search=` + search;

        //??

    });

    $('#searchInput').keypress((event) => {
        if (event.which === 13) {  // Enter 키
            $('#searchButton').click();  // 검색 버튼 클릭 이벤트 호출
        }
    });

    // 디바운스를 위한 타임아웃 변수
    let timeout;

    // 달러 입력란에 실시간으로 이벤트 리스너 추가
    $('#dollar').on('input', function() {
        clearTimeout(timeout); // 이전 타임아웃 클리어

        // 새로운 타임아웃 설정 (500ms 후에 실행)
        timeout = setTimeout(() => {
            const dollarAmount = $(this).val(); // 입력된 달러 금액 가져오기
            if (!dollarAmount || isNaN(dollarAmount)) {
                $('#won').val(''); // 결과를 비움
                return;
            }

            $.ajax({
                url: `api/exchange-rates`, // 환율 API URL
                method: 'GET',
                dataType : 'json',
                success: (data) => {
                    // data가 환율 값이라고 가정
                    const krwRate = parseFloat(data); // 받은 KRW 값을 숫자로 변환
                    if (isNaN(krwRate)) {
                        alert('유효한 환율 정보를 가져오는 데 실패했습니다.'); // 환율 정보가 유효하지 않을 경우 경고
                        return;
                    }

                    const wonAmount = (dollarAmount * krwRate).toFixed(1); // 환율 계산
                    $('#won').val(wonAmount + '₩'); // 결과를 입력란에 표시
                },
                error: (err) => {
                    console.error('Error fetching exchange rates:', err);
                    alert('Slowly Please    ');
                }
            });
        }, 300); // 500ms 후에 AJAX 호출
    });
});