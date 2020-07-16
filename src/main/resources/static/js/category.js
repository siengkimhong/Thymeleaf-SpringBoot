$(function () {
    //Edit category

    $('.btnEdit').on('click', function (e) {
        e.preventDefault();

        let id = $(this).data('id');
        let name = $(this).data('name');
        $('#name').val(name);
        $('#form').attr('action', '/admin/categories/' + id);
    });

    $('.btnDelete').on('click', function (e) {
        e.preventDefault();
        let id = $(this).data('id');
        $('.message-body').text("Are you you want to delete Category?" + id);
        $('.btnConfirmDelete').attr('href', '/admin/categories/' + id + '/delete');
    })

})