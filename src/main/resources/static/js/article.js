$(function () {
    $('.btnArticleEdit').on('click', function (e) {
        e.preventDefault();
        $('.modal-title').text('Edit Article');
        let articleId = $(this).data('id');
        let title = $(this).data('title');
        let description = $(this).data('description');
        let thumbnail = $(this).data('thumbnail');
        let categoryId = $(this).data('category-id');
        $('#modalArticle').modal('show');
        $('#article-form').attr("action","/admin/articles/" + articleId + "/update");
        $('#categoryId').val(categoryId);
        $('#article-title').val(title);
        $('#article-description').val(description);
        $('#thumbnail').val(thumbnail);
        console.log(categoryId);

    })

    $('.btnAddArticle').on('click', function (e) {
        e.preventDefault();
        $('.modal-title').text('Add New Article');
        $('#modalArticle').modal('show');
    })

    $('.btnArticleDelete').on('click', function (e) {
        e.preventDefault();
        let articleId = $(this).data('id');
        $('#modalConfirmDelete').modal('show');
        $('.message-body').text("Are you you want to delete Article? ," + articleId);
        $('.btnConfirmDelete').attr('href', '/admin/articles/' + articleId + '/delete');
    })

    $('.btnArticleView').on('click', function (e) {
        e.preventDefault();
        let articleId = $(this).data('id');
        let title = $(this).data('title');
        let description = $(this).data('description');
        let thumbnail = $(this).data('thumbnail');
        let author = $(this).data('author');
        let published_date = $(this).data('published-date');
        $('#modalView').modal('show');
        $('#article-view-thumbnail').attr('src', '/images/' + thumbnail);
        $('#article-view-description').text(description);
        $('#article-view-title').text(title);

    })
})