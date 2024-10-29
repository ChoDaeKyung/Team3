$(document).ready(() => {
    checkSession();
    getParameterByUserId(userId);
});

let checkSession = () => {
    let hUserId = $('#hiddenUserId').val();
    if (hUserId == null || hUserId === '')
        alert('session이 없습니다.')
}

$(document).ready(function() {

    // 카테고리명을 표시
    $('#nowUserId').text($('#hiddenUserId').val());

    const seller = $('#hiddenUserId').val();
    // Ajax를 이용해 서버에서 상품 리스트를 가져오기
    $.ajax({
        url: '/api/itemList',  // 실제 데이터를 가져올 API 엔드포인트
        type: 'GET',
        dataType: 'json',
        data: {seller: seller},
        success: function (response) {
            // 상품 데이터를 받아서 테이블에 렌더링
            const items = response;
            let htmlContent = '';
            items.forEach(function (item) {
                console.log('item.id :: ', item.id);
                htmlContent += `
                    <div>
                    <item class="" id="deleteThisItem"></item>
                    <button onclick="deleteThisItem(${item.id})"> 
                    , ${item.item} , ${item.price} , ${item.category} , ${item.detail} 
                    </button>
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

let deleteThisItem = (itemId) => {
    console.log('item id :: ',itemId)
    $.ajax({
        url: '/api/delete',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({id: itemId}),
        success: function (response) {
            if (response.success) {
                alert('Item deleted successfully.');
                location.reload();
            } else {
                alert('Failed to delete item: ' + response.message);
            }
        },
        error: function (xhr, status, error) {
            console.error('Error deleting item:', error);
        }
    });
}