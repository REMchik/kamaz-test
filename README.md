# [Test taskEntity for an interview Kamaz](https://github.com/REMchik/kamaz-test)
![Kamaz](https://ibb.co/1fzjd3Y)

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
В [readme](./docker/README.md) файле в папке ./docker имеется информация по установке и запуске.

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

### Developer:
```
Roman Trippel

rtrippel84@gmail.com
```