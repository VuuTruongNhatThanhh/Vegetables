document.getElementById('SelectOption').addEventListener('change', function () {
    val = $("#SelectOption").val();
    url = window.location.pathname + window.location.search;

    if (url.includes("page")) {
        a = url.substring(url.indexOf("page"), url.indexOf("page") + 6);
        url = url.replace(a, "");
    }
    if (!url.includes("?")) {
        url += "?";
    }
    if (url.includes("&sort")) {
        url = url.substring(0, url.indexOf("&sort"))
    }

    window.location.href = url + "&sort=" + val;
});

