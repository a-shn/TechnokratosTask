<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактировать резюме</title>
</head>
<body>
<form method="POST" action="/update/cv/${id}" style="text-align: center">
    Имя: <input type="text" name="name" value="${name}"/><br>
    Фамилия: <input type="text" name="surname" value="${surname}"/><br>
    Ваши контакты: <input type="text" name="contacts" value="${contacts}"/><br>
    Дата рождения: <input type="date" name="bornDate" value="${bornDate}"/><br>
    Желаемая должность: <input type="text" name="desiredPosition" value="${desiredPosition}"/><br>
    Обо мне: <input type="text" name="aboutMe" value="${aboutMe}"/><br>
    <input type="submit" value="Сохранить"/><br>
</form>
</body>
</html>