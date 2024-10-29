$(document).ready(() => {

    let searchStr = $('#searchInput').val();
    searchRequest(searchStr);

    $('#logout').click(() => {
        alert('로그아웃 성공!!')
    });




    $('#searchButton').click(() => {
        const search = $('#searchInput').val().trim(); // 검색어 가져오기

        // 검색어가 비어있으면 요청을 보내지 않음
        if (search === "" || search === null) {
            alert("검색어를 입력해주세요.");
            return;
        }



        // displaySearchResults 함수는 이벤트 핸들러 바깥에 두어야 나중에 다른 곳에서도 재사용 가능
        function displaySearchResults(items) {
            // items가 유효한지 확인하고 배열인지 체크
            if (!items || !Array.isArray(items)) {
                console.error("유효하지 않은 데이터입니다. 검색 결과가 올바른지 확인하세요.");
                return;
            }

            // 검색 결과를 렌더링할 HTML 요소를 선택
            const resultsContainer = $('#results'); // 검색 결과를 표시할 컨테이너 (예: div)

            // 결과 컨테이너 초기화 (기존 결과 제거)
            resultsContainer.empty();

            // 검색 결과가 없을 때 처리
            if (items.length === 0) {
                resultsContainer.append('<p>검색 결과가 없습니다.</p>');
                return;
            }

            // 검색 결과를 순회하며 HTML로 생성
            items.forEach(item => {
                const itemHTML = `
                <div class="item">
                    <a href="/main/category/detail?id=${item.id}">
                        <img src="${item.imagePath}" alt="${item.item}" />
                        <p>${item.item} (${item.price}₩)</p>
                    </a>
                </div>
                `;
                resultsContainer.append(itemHTML);
            });
        }

        // AJAX 요청 보내기
        $.ajax({
            url: '/api/search',  // 서버의 검색 API 엔드포인트
            type: 'GET',
            dataType:'JSON',
            data: { search: search }, // 검색어 파라미터로 전달
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken') // 토큰이 localStorage에 저장되어 있다고 가정
            },
            success: function (data) {
                console.log("서버 응답 데이터:", data); // 데이터 출력 (디버깅용)

                if (Array.isArray(data)) {
                    displaySearchResults(data); // 배열 형태로 바로 올 경우
                } else if (data.items && Array.isArray(data.items)) {
                    displaySearchResults(data.items);
                } else {
                    console.error("유효하지 않은 데이터 형식입니다. 배열이 아닙니다.");
                }
            },
            error: function () {
                alert('검색 결과를 불러오는 데 실패했습니다.');
            }
        });

    });

    $('#searchInput').keypress((event) => {
        if (event.which === 13) {  // Enter 키
            $('#searchButton').click();  // 검색 버튼 클릭 이벤트 호출
        }
    });
});


let searchRequest = (search) => {
    // AJAX 요청 보내기
    $.ajax({
        url: '/api/search',  // 서버의 검색 API 엔드포인트
        type: 'GET',
        dataType:'JSON',
        data: { search: search }, // 검색어 파라미터로 전달
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken') // 토큰이 localStorage에 저장되어 있다고 가정
        },
        success: function (data) {
            console.log("서버 응답 데이터:", data); // 데이터 출력 (디버깅용)
            data.forEach(item => {
                let image = 'data:' + item.contentType + ';base64,' + item.base64Image;
                const itemHTML = `
                <div class="item">
                    <a href="/main/category/detail?id=${item.id}">
                        <img src="${image}" alt="${item.item}" />
                        <p>${item.item} (${item.price}₩)</p>
                    </a>
                </div>
                `;
                $('#results').append(itemHTML);
            });

        },
        error: function () {
            alert('검색 결과를 불러오는 데 실패했습니다.');
        }
    });
}