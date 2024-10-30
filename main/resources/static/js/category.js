$(document).ready(function () {
    $('.category-link').on('click', function (event) {
        event.preventDefault(); // 기본 링크 동작 막기

        var tableName = $(this).data('table'); // 클릭한 카테고리의 테이블명

        // AJAX 요청을 통해 서버에서 테이블 데이터를 가져오기
        $.ajax({
            url: '/main/'+ category, // 서버의 처리 URL
            method: 'GET',
            data: { tablename: tableName }, // 테이블 이름을 서버로 전송
            success: function (response) {
                // 응답 데이터를 받아와서 화면에 표시
                var productList = $('#productList');
                productList.empty(); // 기존 내용을 비우기

                response.forEach(function (item) {
                    // 각 item을 화면에 추가
                    var productHTML = `
                        <div class="product-item">
                            <img src="${item.url}" alt="${item.item}" class="product-image">
                            <h3>${item.item}</h3>
                            <p>가격: ${item.price}원</p>
                            <p>판매자: ${item.seller}</p>
                            <p>상세 정보: ${item.detail}</p>
                            <a href="${item.url}" target="_blank">상품 보기</a>
                        </div>
                    `;
                    productList.append(productHTML); // 데이터를 화면에 추가
                });
            },
            error: function (error) {
                console.error('데이터를 가져오는 중 오류 발생:', error);
            }
        });
    });
});