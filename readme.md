# Тестовое задание

## Описание

Данное тестовое задание предусматривает выполнение HTTP POST-запросов к API Dadata для поиска информации о компаниях по
их ИНН.
Запросы выполняются с использованием библиотеки RestAssured.

## Задача 1

1. Собрать POST-запрос на `http://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/party` с использованием
   RestAssured
   (ознакомиться с документацией API можно тут: [Dadata Find Party](https://dadata.ru/api/find-party/). Использовать заголовок
   авторизации:`Authorization: Token 884a52419509c658fa68ae44c515885a41929906`
2. Считать ИНН компаний из файла `query.json` и выполнить запрос `findById/party` для каждого ИНН.
3. Из полученного ответа извлечь данные из параметров `value`, `data.inn`, `data.kpp`, `data.ogrn`, `data.type`
   и сохранить в объект с полями `name`, `inn`, `kpp`, `ogrn`, `type`.
   Остальные параметры игнорируются.
4. Сравнить полученные объекты с данными, записанными в JSON-файл `expected-result.json`, и убедиться, что
   они идентичны. Проверка должна выполняться в тестовом методе.

## Требования

- Java
- RestAssured
- JSON-парсер
- Lombok (опционально)

## Задача 2

Провести ревью класса `ru.alfabank.afin.util.Resources_Util`