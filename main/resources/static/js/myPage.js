
    $(document).ready(function() {
    // 모든 섹션 숨기기
    $('.container > section').hide();
    // checkPassword div를 보여줍니다
    $('#checkPassword').show();

    // NAV 항목 클릭 시 해당 섹션만 보여주기
    $('nav a').click(function(event) {
    event.preventDefault();
    const targetId = $(this).attr('href');

    // 모든 섹션 숨기기
    $('.container > section').hide();
    // 체크된 ID의 섹션만 보여주기
    $(targetId).show();

    // 프로필 섹션에 비밀번호 체크 로직
    if (targetId === '#profile') {
    $('#checkPassword').show();
} else {
    $('#checkPassword').hide();
}
});

    // 비밀번호 확인 후 프로필 섹션 보여주기
    $('#checkPassword input').on('input', function() {
    const inputPassword = $(this).val();
    const storedPassword = sessionStorage.getItem('password'); // 세션에서 비밀번호 가져오기

    if (inputPassword === storedPassword) {
    $('#checkPassword').hide();
    $('#editProfile').show();
} else {
    $('#editProfile').hide();
}
});


    // 페이지 로드 시 프로필 섹션 비밀번호 확인을 위해 editProfile 숨기기
    $('#editProfile').hide();
});