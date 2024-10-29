$(document).ready(() => {
    loadItemDetail();

    $('#likesButton').click(() => {
        alert('hi')
        const numberOfRequests = 5; // 동시 요청 수
        let hId = $('#hiddenId').val();
        let hVersion = $('#hiddenVersion').val();

        // const sendLikesRequest = () => {
            let formData = { id: hId, version: hVersion };

            $.ajax({
                type: 'PUT',
                url: '/api/likes',
                data: JSON.stringify(formData),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: (response) => {
                    console.log('응답 성공:', response);
                },
                error: (error) => {
                    console.error('오류 발생:', error);
                }
            });
        // };

        // for (let i = 0; i < numberOfRequests; i++) {
        //     sendLikesRequest();
        // }

        setTimeout(() => {
            // window.location.href = `/main/category/detail?id=${hId}`;
        }, 1000);
    });
});

let loadItemDetail = () => {
    let hId = $('#hiddenId').val();
    $.ajax({
        type: 'GET',
        url: '/api/main/category/' + hId,
        dataType: 'json',
        success: (response) => {
            $('#product-image').attr('src', response.imagePath);
            $('#likes').html(response.likes);
            $('#item').text(response.item);
            $('#price').text('가격: ' + response.price + '원');
            $('#detail').text('상품 설명: ' + response.detail);
            $('#seller').text('판매자: ' + response.seller);
            $('#hiddenVersion').val(response.version);
        },
        error: (error) => {
            console.error('에러:', error);
        }
    });
}