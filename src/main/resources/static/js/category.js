$(document).ready(function() {
    // 현재 URL에서 카테고리 값을 가져오는 함수
    let hUserId = $('hiddenUserId').val();

        function getParameterByName(name) {
            const url = window.location.href;
            name = name.replace(/[\[\]]/g, '\\$&');
            const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, ' '));
        }

        // 현재 카테고리 값을 가져옴
        const category = getParameterByName('category');

        // 카테고리명을 표시
        $('#category-name').text(category);

        // Ajax를 이용해 서버에서 상품 리스트를 가져오기
        $.ajax({
            url: '/api/main/category',  // 실제 데이터를 가져올 API 엔드포인트
            type: 'GET',
            dataType: 'json',
            data: {category: category},
            success: function (response) {
                // 상품 데이터를 받아서 테이블에 렌더링
                const items = response;
                let htmlContent = '';
                items.forEach(function (item) {
                    console.log('item.id :: ', item.id);
                    htmlContent += `
                    <div>
                    <a href="/main/category/detail?id=${item.id}">
                    <img src="https://via.placeholder.com/200x200?text=N" alt="N Logo">
                <p>${item.item} (${item.price}₩)</p>
                </a>
                </div>
                `;
                });

                // #items에 HTML 콘텐츠 추가
                $('#items').html(htmlContent);
            },
            error: function () {
                console.error("상품 데이터를 불러오는 데 실패했습니다.");
            }
        });
});