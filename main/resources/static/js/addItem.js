$(document).ready(() => {


    $('#addItem').click(() => {

        let item = $('#item').val();
        let price = $('#price').val();
        let detail = $('#detail').val();
        let seller = $('#seller').val();
        let category = $('#selectBar').val();
        let image = $('#image')[0].files[0];


        if (item == "" || price == "" || detail == "" || seller == "") {
            alert('모든 정보를 기입해주세요.')
            return "redirect:/main/join"
        }

        let formData = new FormData();
        formData.append('item', item);
        formData.append('price', price);
        formData.append('detail', detail);
        formData.append('seller', seller);
        formData.append('category', category);
        formData.append('image', image);



        $.ajax({
                type: 'POST',
                url: '/api/addItem', // Backend endpoint
                data: formData,
                processData: false,
                contentType: false,
                enctype: 'multipart/form-data', // Form enctype
                success: function(response) {
                    console.log("성공:", response);
                    alert('상품이 성공적으로 등록되었습니다.');
                    window.location.href = "/main"; // Redirect to another page if needed
                },
                error: function(xhr,status,error) {
                    console.error('오류 발생:', xhr);
                    alert('상품 등록 중 오류가 발생했습니다.');
                }
            }

        );

    })



});