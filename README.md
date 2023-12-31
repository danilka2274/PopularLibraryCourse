Факультет: Android-разработки
Курс: Популярные библиотеки: RxJava 2, Dagger 2, Moxy
Дата начала: 7.06.2023

Домашние задания:

Домашнее задание №1

    В проекте урока есть серьёзная неточность, что делает презентер не тестируемым: ссылки на Android-фреймворк. В методе buttonClick используются конструкции типа R.id.btnCounter1, 
	которых не должно быть в презентере. Переделайте код этого метода, а также вызывающего метода представления так, чтобы в презентере остался только чистый Kotlin-код.
	
Домашнее задание №2

    Реализуйте экран пользователя, на котором отобразите его логин. Переход на экран осуществите по клику на пользователя в списке через router.navigateTo

Домашнее задание №3

    Переделайте взаимодействие модели и логики в коде из второго урока на Rx-паттерн.
    *Самостоятельно изучите оператор switchMap. Разберитесь, как он работает и чем отличается от flatMap.
	Сформулируйте и напишите ответ в комментарии к практическому заданию. Для экспериментов воспользуйтесь приведённым на уроке примером с flatMap, замените его на switchMap, а остальное оставьте без изменений.
	
Домашнее задание №4

    Соблюдая MVP, напишите маленькое приложение, которое по нажатию кнопки читает файл-картинку формата jpg из файловой системы, 
	а затем конвертирует её в png и записывает обратно в файловую систему. Чтение и запись должны производиться не в UI-потоке.
    *Добавьте в предыдущем примере возможность отказаться от проведения операции, выведя после начала в UI-поток диалоговое окно с надписью «Выполняется конвертация» и кнопкой «Отменить».
	Для наглядности замедлите процессы в фоновом потоке посредством метода sleep().
	
Домашнее задание №5

    По клику на пользователя отобразите список его репозиториев, воспользовавшись полями repos_url в api и аннотациями @Url библиотеки retrofit.
    По клику на репозиторий в списке отобразите экран с информацией о нём (например, количество форков).

Домашнее задание №6

    Вытащите кеширование в отдельные классы RoomGithubUsersCache и RoomGithubRepositoriesCache. Организуйте их внедрение в репозиторий через интерфейсы.
    Реализуйте свой кеш картинок, используя listener() библиотеки Glide. Картинки храните на диске, а в Room — CahedImage(url, localPath).
	Задание предназначено, чтобы поупражняться с Room. В реальности лучше используйте встроенный кеш Glide.
	
Домашнее задание №7

  Вынесите в модули все остальные зависимости, чтобы DI полностью сформировался через Dagger. Уберите лишние @Inject там, где они станут не нужны.




