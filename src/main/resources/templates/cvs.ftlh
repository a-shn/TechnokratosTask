<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Все резюме</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

</head>
<body onload="getCvs()">
<script type="application/javascript">
    function getCvs() {
        $.ajax({
            url: "/api/cvs",
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                var a = JSON.stringify(response);
                var b = JSON.parse(a);
                console.log(b);
                $.each(b, function (idx, cv) {
                    $('#cvs').first().append('<a href=\"/cv/' + cv['_id'] + '\" <a>' + cv['name'] + ' ' + cv['surname'] + '</a><br>');
                });
            },
            error: function (err) {
                alert(err.responseText);
            }
        })
    }
</script>
<div id="cvs">
</div>
</body>
</html>

