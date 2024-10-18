// JavaScript에서 페이지 로드 시 상품 상세 데이터 로드
$(document).ready(() => {
    loadItemDetail();  // 페이지가 로드되면 이 함수가 호출됨
});

// 상품 상세 데이터 로드
let loadItemDetail = () => {
    let hId = $('#hiddenId').val();
    console.log('상품 ID:', hId);
        $.ajax({
            type: 'GET',
            url: '/api/main/category/' + hId,
            dataType: 'json',
            success: (response) => {
                console.log('response:', response);
                $('#item').text(response.item);
                $('#price').text('가격: ' + response.price + '원');
                $('#detail').text('상품 설명: ' + response.detail);
                $('#seller').text('판매자: ' + response.seller);
            },
            error: (error) => {
                console.error('Error:', error);  // 에러 발생 시 로그 출력
            }
        })
}