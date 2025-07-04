# Курсовой проект по дисциплине "Программная инженерия"

## Веб приложение для бронирования билетов в кинотеатре

### Использованные инструменты:
   - языки программирования – Java17,  HTML5, CSS;
  - среда программирования – IntelliJ IDEA 2024.3.3;
 - фрейворки - Spring MVC, Hibernate, Apache Maven;
  - шаблонизатор - Thymeleaf;
  - СУБД – PostgrateSQL 17.3; 
  - среда проектирования – StarUML 6.3.1.
### Основной функционал:
Администратор:
- Добавление, редактирование, удаление фильмов;
- Добавление, редактирование, удаление сеансов;
- Добавление, редактирование, удаление элементов справочника: режиссеров, стран, жанров;
- Добавление кинозалов;
- Работа с конструктором схемы кинозала.
  
Пользователь:
- Просмотр афиши на все дни, на сегодня, завтра, по указанной в календаре дате;
- Просмотр доступных сеансов к выбранному фильму;
- Просмотр мест для выбранного сеанса, выбор мест;
- Бронирование мест на выбранный сеанс;
- Работа в личном кабинете: просмотр и редактирование личной информации, просмотр истории бронирования, выход из аккаунта;
- При просмотре истории бронирования пользователь может просмотреть подробную информацию о заказе и отменить бронь;
- Ознакомление со справочной информацией об авторах и о системе.

  ### Как запустить:
1. Клонировать проект
2. При запуске приложения в IDE
    - скорректировать параметры подключения к БД в файле src/main/resources/application.properties;
3. После запуска приложения главная страница доступна по адресу: http://localhost:9090/
4. Параметры входа на сайт
    - для администратора: имя - admin, пароль - admin;
    - для пользователя: зарегистрируйтесь на сайте и используйте свой новый аккаунт.
