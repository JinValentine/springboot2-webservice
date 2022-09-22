var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};
// 중복된 함수이름이 발생할 수 있기 때문에 js만의 유효범위를 만들기 위해 var 변수로 객체를 만들어 function을 선언한다.
// 이렇게 하면 index 객체 안에서만 function이 유효하기 때문에 다른 js와 겹칠 위험이 사라진다.

main.init();