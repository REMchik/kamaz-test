# [Test taskEntity for an interview Kamaz](https://github.com/REMchik/kamaz-test)

## Software:
```
* Java 11
* Maven 4.0
* Docker
```

It is also recommended to use:
* [Ubuntu 20.04](https://releases.ubuntu.com/20.04/) 
* DBeaver
```bash
sudo snap install dbeaver-ce
```
* Postman
```bash
sudo snap install postman
```

## Run docker:
Для работы приложения необходимо поднять БД PostgreSQL в docker-контейнереr.
В [readme](./docker/README.md) файле в папке ./docker имеется информация по установке и запуску.

## Install maven:
```bash
sudo apt install maven
```

## Commands to build app:
```bash
mvn clean install
```

## Command to run app:
```bash
java -jar ./target/kamaz-demo-1.0.0-SNAPSHOT.jar
```
## Test curls:
### Группа:
Получение списка групп:
```bash
curl --location --request GET 'localhost:8080/kamaz/group'
```
Добавление группы:
```bash
curl --location --request POST 'localhost:8080/kamaz/group' \
--header 'Content-Type: application/json' \
--data-raw '{
        "title": "Sales",
        "dateOfEmployment": "2022-04-26T01:01:59.421832"
    }'
```
### Пользователь:
Создание пользователя:
<br> Должность выбрана из списка, который был добавлен при инициализации таблицы, файл [init.sql](./docker/postgresql/init.sql) 
```bash
curl --location --request POST 'localhost:8080/kamaz/user/' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "Andrey",
        "age": 33,
        "position": "Менеджер"
    }'
```
Получение списка пользователей
```bash
curl --location --request GET 'localhost:8080/kamaz/user'
```
Изменение данных пользователя:
```bash
curl --location --request PATCH 'localhost:8080/kamaz/user' \
--header 'Content-Type: application/json' \
--data-raw '{
        "id": 42,
        "name": "Андрей",
        "age": 35,
        "position": "Конструктор"
    }'
```
Удаление пользователя:
<br>В качестве PathVariable в конце ссылки укзываем id пользователя, в данном примере userId == 1
```bash
curl --location --request DELETE 'localhost:8080/kamaz/user/1'
```
### Работа:
Создание работы:
userId - необходимо указать id пользователя на которого будет назначено задание
```bash
curl --location --request POST 'localhost:8080/kamaz/task?userId=42' \
--header 'Content-Type: application/json' \
--data-raw '{
"title": "Work!"
}'
```
Получение списка задача:
userId - необходимо указать id пользователя по которому будет предоставлен список работ
```bash
curl --location --request GET 'localhost:8080/kamaz/task?userId=42'
```
### Группа:
Получение списка групп:
```bash
curl --location --request GET 'localhost:8080/kamaz/group'
```
Создание группы:
```bash
curl --location --request POST 'localhost:8080/kamaz/group' \
--header 'Content-Type: application/json' \
--data-raw '{
"title": "Sales"
}'
```
Добавление пользователя в группу:
<br>В качестве PathVariable в ссылке указываем id группы в которую добавляем пользователя и id пользователя, в данном примере groupId == 7, userId == 42
```bash
curl --location --request PATCH 'localhost:8080/kamaz/group/8/user/42'
```
Удаление группы:
<br>В качестве PathVariable в конце ссылки указываем id группы, в данном примере groupId == 7
```bash
curl --location --request DELETE 'localhost:8080/kamaz/group/7'
```


### Developer:
```
Roman Trippel

rtrippel84@gmail.com
```