<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Резюме по ID</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
</head>
<body onload="getCv('${id}')">
<script type="application/javascript">
    function getCv(id) {
        $.ajax({
            url: "/api/cv/" + id,
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                var a = JSON.stringify(response);
                var b = JSON.parse(a);
                console.log(b);
                $('#name').first().append(b['name']);
                $('#surname').first().append(b['surname']);
                $('#contacts').first().append(b['contacts']);
                $('#bornDate').first().append(b['bornDate']);
                $('#desiredPosition').first().append(b['desiredPosition']);
                $('#aboutMe').first().append(b['aboutMe']);
            },
            error: function (err) {
                alert(err.responseText);
            }
        })
    }
</script>
Имя:
<div id="name"></div>
<br>
Фамилия:
<div id="surname"></div>
<br>
Контакты:
<div id="contacts"></div>
<br>
Дата рождения:
<div id="bornDate"></div>
<br>
Желаемая должность:
<div id="desiredPosition"></div>
<br>
Обо мне:
<div id="aboutMe"></div>
<br>
<form method="post" action="/api/delete/cv/${id}">
    <input type="submit" id="btn" value="Удалить">
</form>
<form method="get" action="/update/cv/${id}">
    <input type="submit" id="btn" value="Редактировать">
</form>
</body>
</html>