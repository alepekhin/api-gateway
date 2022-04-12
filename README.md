# api-gateway

API gateway microservice with Spring Cloud Contract Verifier

## Инициализация

Проект построен с помощью https://start.spring.io/

Spring Cloud Contract Verifier не работает в начальном проекте  
пришлось мменять `build.gradle` на более свежую версию из  

https://github.com/spring-cloud-samples/spring-cloud-contract-samples/blob/main/producer_testng/build.gradle

Так как Spring Cloud Contract Verifier подразумевает TDD то надо написать простенький 
контракт и его реализацию.

После этого приложение можно запустить.

## Архитектура

Предполагаемый клиент обращается к приложению по ендпойнту `/users` и получает `List<UserDTO>`  
Для этого приложение обращается к бекенду, у нас это `https://jsonplaceholder.typicode.com`,  
по ендпойнту `/users` и получает `List<User>` которое затем преобразуется `List<UserDTO>` с помощью библиотеки `mapstruct`

## Цель проекта

Целью данного проекта не является построение `api-gateway`, хотя это тоже интересно
- задействовать `actuator`, получить git hash в `/actuator/info`, получить метрики, в частности время выполнения ендпойнта 
- подключить сваггер
- получить покрытие кода тестами и пр.

На самом деле целью проекта является научиться наконец пользоваться Spring Cloud Contract  
и написать тесты для Producer и Consumer, учитывая, что само приложение является продьюсером для своего клиента
 и консьюмером для бекенда.

## Доступные ендпойнты

После старта приложения  `./gradlew bootRun` доступны следующие ендпойнты

- /users основной ендпойнт API
- /actuator
- /actuator/health
- /actuator/info должен показать git hash
- /actuator/metrics/ показывает все метрики
- /actuator/metrics/users - время выполнения ендпойнта `/users'
- /actuator/httptrace - показывает последние запросы к API
- /swagger-ui.html

Прикольно, что кроме `/users` все остальное получено даром.

## Тесты

### Producer test

В соответствии со Spring Cloud Contract пишем спецификацию контракта
`shouldReturnListOfUserDTO.grovy` и соответствующий метод в контролдлере.   

Contract Verifier пишет тест `UserTest.java` в папке `build/generated-test-sources`

Этот тест выполняется в таске градла `contractTest`

Стаб для клиента генерируется в папке `build/stubs`

### Consumer test

Мы не имеем стаба для бекенда, поэтому пишем его сами - это файл `backendStub.json`

Тест для консьюмера пишется руками используя этот стаб - также как наш клиент 
должен написать тест используя нами сгенерированный стаб.

Код теста `BackendContractYTest.java`

После выполнения тестов покрытие можно найти в `build/reports/jacoco/test/html`

## Литература

- https://microservices.io/patterns/apigateway.html
- https://spring.io/projects/spring-cloud-contract
- https://cloud.spring.io/spring-cloud-contract/2.0.x/multi/multi__spring_cloud_contract_verifier_introduction.html











