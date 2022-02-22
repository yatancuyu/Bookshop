# Описание сценариев использования
1. Поиск книг по жанру, названию, автору, году издания
    - На главной странице заполнить поисковую строку, выбрать 1/4 категорий поиска
    - Нажать кнопку "Найти".
2. Возможность зарегистрироваться на сайте на странице "Регистрация"
    - Нажать на кнопку "Регистрация" в хедере
    - **переход** на страницу "Регистрация".
    - Заполнение обязательных полей
    - Нажать кнопку "Зарегистрироваться"
3. Добавление книги в заказ (*Для авторизованных пользователей*)
    - На странице с книгой нажать на кнопку "Заказать"
    - **переход** на страницу с выбором открытого заказа
    - Нажать на кнопку выбранного заказа
    - **переход** на страницу выбранного заказа с добавленной книгой
4. Добавление новой книги в каталог (*Для администратора*)
    - Нажать на кнопку "Добавить книгу" в хедере
    - **переход** на страниц книги с пустыми полями
    - Заполнить обязательные поля с информацией о книге
    - Нажать на кнопку "Редактировать"
5. Оформеление заказа
    - Нажать на кнопку "Оформить заказ" на странице выбранного заказа со статусом "Открыт"
    - **переход** на страницу оформление заказа
    - Заполнить обязательные поля с информацие о получение заказа
    - Нажать кнопу "Оформить"
    - **переход** на страницу выбранного заказа, изменение его статуса
___
# Описание страниц
#### 0. Хедер сайта
- В левом углу ссылка - **переход** на главную страницу
- В правом углу:
    - Для вошедших пользователей:
        - **переход** в личный кабинет
        - кнопка "Выйти" - **переход** на главную страницу
    - Для невошедних пользователей
        - кнопка "Регистрация" - **переход** на страницу регистрации
        - кнопка "Войти" - **переход** на страницу Log In
    - Для администратора:
        -  кнопка "Добавить книгу" - **переход** на страницу книги с пустыми полями
        -  кнопка "Заказы" - **переход** на страницу заказов пользователей
#### 1. Главная страница
1. Каталог всех книг. Для каждой книги: название, автор, жанр, цена,  ссылка с **переходом** на страницу книги.
2. Поисковая строка с Radio Button - категория поиска (Название, автор, жанр, год издания) и кнопкой "Найти" - отображение на главной странице списка подходящих книг
#### 2. Регистрация:

Страница регистрации с формой для заполнения.
- ФИО
- E-mail
- Номер телефона
- Логин
- Пароль
- Повторите пароль
- Адрес
- Кнопка "Зарегестрироваться" - **переход** на главную страницу
#### 3. Log in:

Страница входа пользователя на сайт под своим логином и паролем.
Кнопка "Войти" - **переход** на главную страницу
#### 4. Страница книги:

Страница со всей информацией о книге: название, список авторов, жанр, издательство, год издания, количество страниц, изображением обложки, цена книги, наличие.
- Для вошедших пользователей:
    - Кнопка "Заказать" - **переход** к странице выбора открытого заказа, в который положить книгу ИЛИ образование нового заказа
- Для адиминистратора:
    - Все поля с информацией о книге редактируемы
    - Кнопка с подтверждением редактирования
    - Кнопка удалить книгу
#### 5. Личный кабинет:
1. Текущая информация о пользователе, каждое поле можно редактировать; Кнопка "Изменить" для подтверждения редактирования полей.
2. Список заказов - номер заказа, ссылка-**переход** на страницу заказа.
#### 6. Cтраница заказа
Страница с информацией о заказе : статус, список заказанных книг со счетчиком количества, цена заказа;
Для заказа со статусом **Открыт**: Кнопка "Оформить заказ" - **переход** на страницу оформление заказа.
#### 7. Страница оформления заказа
Обязательные поля:
- Адрес доставки
- Время и дата доставки
- Кнопка "оформить" - **переход** на страницу заказа, изменение статуса
#### 8. Страница заказов пользователей
Список всех пользовательских заказов, напротив каждого - кнопка изменения статуса заказа.
#### 9. Страница выбора открытого заказа
- Список открытых заказов - напротив каждого кнопка "Добавить" - **переход** к странице заказа
- Кнопка "Новый заказ" - **переход** к странице нового заказа
___
# Схема базы данных приложения
![](/docs/images/database.png)