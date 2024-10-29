$(document).ready(function() {
    // 현재 URL에서 카테고리 값을 가져오는 함수
    getItems();

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
});

let getItems = () => {
    let currentPage = 1;
    const pageSize = 9;

    // 초기 게시글 로드
    loadItems(currentPage, pageSize);

    // 다음 페이지 버튼 클릭 이벤트
    $('#nextPage').on('click', () => {
        currentPage++;
        loadItems(currentPage, pageSize);
    });

    // 이전 페이지 버튼 클릭 이벤트
    $('#prevPage').on('click', function() {
        if (currentPage > 1) {
            currentPage--;
            loadItems(currentPage, pageSize);
        }
    });
}

let loadItems = (page,size) => {

    function getParameterByName(name) {
        const url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    const category = getParameterByName('category');

    $('#category-name').text(category);

        $.ajax({
            url: '/api/main/category',  // 실제 데이터를 가져올 API 엔드포인트
            type: 'GET',
            dataType: 'json',
            data: {
                category: category,
                page: page,
                size: size
            },
            success: function (response) {
                console.log("response :: " , response)
                // 상품 데이터를 받아서 테이블에 렌더링
                let htmlContent = '';
                response.items.forEach(function (item) {
                    console.log('item.id :: ', item.id);
                    htmlContent += `
                    <div>
                    <item class=""></item>
                    <a href="/main/category/detail?id=${item.id}">
                    <img src="https://via.placeholder.com/200x200?text=N" alt="N Logo">
                <p>${item.item} (${item.price}₩)</p>
                </a>
                </div>
                `;
                });

                $('#items').html(htmlContent);

                // const totalItems = response.totalItems;
                // const lastPage = Math.ceil(totalItems / size);
                // console.log(totalItems)
                // console.log(lastPage)
                $('#pageInfo').text(page);

                $('#prevPage').prop('disabled', page === 1);
                $('#nextPage').prop('disabled', response.last);
            },
            error: function () {
                console.error("상품 데이터를 불러오는 데 실패했습니다.");
            }
        });
}