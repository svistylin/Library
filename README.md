# Library

Project purpose
Library is the App for exploiting library service online. 

Project Structure

1.Java 8

2.Apache-tomcat-8.5.42

3.Maven 3.5.1

4.Spring Core 5.1.4

5.Spring-context 5.1.5

6.Spring-security-config 4.2.3

7.Junit 4.12

8.Spring-test 5.1.9

9.Springfox-swagger2 2.9.2

10.Jackson-core 2.9.4

11.Jackson-annotations 2.9.4

12.Hibernate-core 5.2.10 

13.c3p0 0.9.5.2

14.Postgresql 9.4.1212.

Instructions before start:

For user. How to start?
The description will be implemented later after deployment.

For developer
1. Download project from https://github.com/svistylin/Library
2. Open in IntelliJ IDEA or other development enviroment
3. Create tomcat configuration 
4. Run project
5. Enjoy result



# Tasks
Создать CRUD приложение система учёта книг в библиотеке.
У книги есть название, автор, год выпуска, жанр(много) и количество в библиотеке. При добавлении новой книги создавать новую запись, а при добавлении уже существующей увеличивать количество. Дать возможность взять книгу почитать и потом вернуть. Добавить возможность вывода всех книг с фильтрацией по любым полям (например выведи все книги в жанре фэнтези 2018 года выпуска и тд...).
Все общение должно происходить по REST API.
Добавить  readme file с описанием как запустить проект
Технологии: Spring, Hibernate, PostgreSQL
Залить приложение на публичный GitHub репозиторий
Нужно добавить в приложение Swagger  для удобного тестирования.
Дополнительные задания:
- Прикрутить секьюрити и ввести 2 роли админ и пользователь. У пользователя есть возможность взять в аренду и вернуть книгу обратно. Админ еще может заводить новые книги на баланс библиотеки.
- Дать возможность создавать новые аккаунты.
- Тесты
- Простенький UI

