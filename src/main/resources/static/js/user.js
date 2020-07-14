$(function () {
    $('#btnSaveUser').on('click', function (e) {
        e.preventDefault();
        let password = $('#inputPassword').val();
        let confirmPassword = $('#inputConfirmedPassword').val();
        if (password !== confirmPassword){
            $('#messagePassbyJS').text("Passwod is not matched");
            $('#modalMessage').modal('show');
        }else {
            $('#formAddUser').submit();
        }
    });

    $('.btnUserEdit').on('click', function (e)
    {
        e.preventDefault();
        $('#modalUser').modal('show');
    });
})
