function changeImage(id) {
    let imagePath = document.getElementById(id).getAttribute('src');
    document.getElementById('img-main').setAttribute('src', imagePath);
}

document.getElementById('selectWeight').addEventListener('change', function () {
    val = $("#selectWeight").val();
    url = window.location.pathname + window.location.search;

    url = url.substring(0, url.indexOf("&idW="));

    window.location.href = url + "&idW=" + val;

});

$('.input-qty').each(function () {
    var $this = $(this), qty = $this.parent().find('.is-form'), min = Number($this.attr('min')),
        max = Number($this.attr('max'))
    $(qty).on('click', function () {
        var num = Number($this.val());
        if ($(this).hasClass('minus')) {
            num = (num > min) ? num - 1 : num;
        }
        if ($(this).hasClass('plus')) {
            num = (num < max) ? num + 1 : num;
        }
        console.log(num)
        $this.val('' + num)
    })
})

function addCart(idP, idW, url) {
    var num = $(".input-qty").val();
    window.location.href = "/AddCart?url=" + url + "&idP=" + idP + "&idW=" + idW + "&num=" + num;
}

function loadMore(idP) {
    var amount = $(".comment").length
    $.ajax({
        url: "/ReviewControl",
        type: "get",
        data: {
            idP: idP,
            amount: amount
        },
        success: function (data) {
            $(".comment-list").html(data);
            $(".check").attr('checked', true);
        },
        error: function () {
            console.log(amount)
        }
    })
}

function addReview(idP, idU) {
    var starVal = Number($(".star-check:checked").val());
    var mess = $("#message").val();
    $.ajax({
        url: "/ReviewControl",
        type: "post",
        data: {
            idP: idP,
            idU: idU,
            star: starVal,
            mess: mess
        }
    })
}

$(document).ready(function () {
    $(".check").attr('checked', true);
})