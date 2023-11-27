# spring_boot_api_application

## Описание проекта

RESTful API приложение для управления документами и их позициями.

## Используемые технологии

- IntelliJ IDEA
- Docker и Docker Compose
- Postman

## Установка и запуск

1. **Настройка БД:**
    - Склонируйте репозиторий. Убедитесь, что у вас установлен Docker и Docker Compose.
    - В корневой папке проекта находится файл `docker-compose.yml`, поднимающий postgres контейнер c БД, а также Flyway контейнер, накатывающий миграции (`./db/migration/V1__Create_DB.sql`). Поднимем контейнеры:
      ```
      docker-compose up -d
      ```
    - В случае работы на Linux и отсутствии текущего пользователя в группе Docker использовать следующую команду:
      ```
      sudo docker-compose up -d
      ```
2. **Сборка и запуск проекта:**
    - Откройте проект с помощью IDEA. Среда рзаработки автоматически подгрузит зависимости и сбилдит проект.
    - метод main находится в `BdApiApplication.java`, запустите приложение.
## Использование API (описание методов и эндпоинты)
### Документы

1. **Создать документ**
```
POST: http://localhost:8080/docs/add
Body:
{
    "number": 25,
    "note": "my_document"
}
```
2. **Получить все документы**
```
GET: http://localhost:8080/docs/
```
3. **Получить документ по номеру**
```
GET: http://localhost:8080/docs?number=25
```
4. **Редактировать документ**
```
PUT: http://localhost:8080/docs/update/25
Body:
{
    "note": "updated_doc"
}
```
5. **Удалить документ по номеру**
```
DELETE: http://localhost:8080/docs/delete/25
```
6. **Удалить все документы**
```
DELETE: http://localhost:8080/docs/delete-all
```
### Позиции
1. **Создать позицию**
```
POST: http://localhost:8080/positions/add?doc_num=25
Body:
{
    "pos_num": 1,
    "pos_name": "my_position"
}
```
2. **Получить все позиции документа**
```
GET: http://localhost:8080/positions/25/
```
3. **Получить определенную позицию документа**
```
GET: http://localhost:8080/positions/25/1
```
4. **Редактировать позицию**
```
PUT: http://localhost:8080/positions/update/25
Body:
{
    "pos_num": 1,
    "pos_name": "updated_pos"
}
```
5. **Удалить определенную позицию в документе**
```
DELETE: http://localhost:8080/positions/delete/25/1
```
6. **Удалить все позиции в документе**
```
DELETE: http://localhost:8080/positions/delete/25/
```
7. **Удалить все позиции**
```
DELETE: http://localhost:8080/positions/delete-all
```
### Логи
1. **Получить все логи**
```
GET: http://localhost:8080/logs/
```
2. **Получить все логи по документу**
```
GET: http://localhost:8080/logs?doc_num=25
```
3. **Удалить все логи**
```
DELETE: http://localhost:8080/logs/delete-all
```