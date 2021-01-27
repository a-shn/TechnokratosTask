<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добавить резюме</title>
</head>
<body>
<form method="POST" action="/new/cv" style="text-align: center">
    Имя: <input type="text" name="name"/><br>
    Фамилия: <input type="text" name="surname"/><br>
    Ваши контакты: <input type="text" name="contacts"/><br>
    Дата рождения: <input type="date" name="bornDate" required/><br>
    Желаемая должность: <input type="text" name="desiredPosition"/><br>
    Обо мне: <input type="text" name="aboutMe"/><br>
    <input type="submit" value="Добавить"/><br>
</form>
</body>
</html>