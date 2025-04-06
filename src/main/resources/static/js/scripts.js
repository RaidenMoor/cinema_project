$(document).ready(function() {
    $('#editProfileModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Кнопка, которая открыла модальное окно
        var userId = button.data('user-id'); // Получаем ID пользователя из атрибута data-user-id
        var login = button.data('user-login'); // Получаем логин пользователя из атрибута data-user-login
        var email = button.data('user-email'); // Получаем email пользователя из атрибута data-user-email
        var firstName = button.data('user-firstName'); // Получаем имя пользователя из атрибута data-user-firstName
        var lastName = button.data('user-lastName'); // Получаем фамилию пользователя из атрибута data-user-lastName
        var modal = $(this);

        // Заполняем поля формы в модальном окне
        modal.find('#modalUserId').val(userId);
        modal.find('#modalLogin').val(login);
        modal.find('#modalEmail').val(email);
        modal.find('#modalFirstName').val(firstName);
        modal.find('#modalLastName').val(lastName);
    });
});