# spring rest skeleton

spring-boot based REST skeleton app

#### # installation
 * setup `src/main/resources/application-dev.properties` from template (`src/main/resources/application-dev.template.properties`)
 * run `SPRING_PROFILES_ACTIVE=dev,default mvn spring-boot:run`

#### # supported libs/technologies
 * logging framework (http://logback.qos.ch/)
 * database migrations framework (https://flywaydb.org/)
 * code-generation library (https://projectlombok.org/)
 * [internationalization](#internationalization)
 * [emailing](#emailing)
 * [rest-api](#rest-api)

----

#### `internationalization`
 * app supports i18n messages, just fill them in `src/main/resources/i18n/messages_{locale}.properties`
 * locale is firstly retrieved from *Accept* header and then stored in cookie
 * locale can be changed by *GET* parameter `?setLocale=en`
 * example is available at `GET /demo/i18n`  

----

#### `emailing`
 * mailService for sending templatable email messages
 * email supports: thymeleaf templating engine, i18n messages, absolute linked images, attachments
 * you can setup your own mail message template preparator ([example](src/main/java/com/example/framework/email/preparator/ExampleMailPreparator.java)) For every type of message (template) you need to implement separate MailPreparator.
 * usage: `mailService.send(new ExampleMailPreparator());`
 * an example endpoint to send a demo email: `GET /demo/mail`

----

#### `rest-api`
 1. create your own entity (e.g. [Post.java](src/main/java/com/example/framework/model/Post.java))
 2. Implement your own repository (e.g. [PostRepository.java](src/main/java/com/example/framework/repository/PostRepository.java)) and expose it as `@RepositoryRestResource`
 3. check `/api/posts` (or rest of the HATEOAS REST endpoints)
   * some examples of valid EPs (`GET|POST|PATCH|PUT|DELETE /api/{repo}/{id}/{property}/{propertyId}`)
   * `GET|POST /api/posts`
   * `GET /api/posts/1/tags`
   * `GET|PUT|PATCH|DELETE /api/posts/1`
   * `GET|DELETE /api/posts/1/tags/1`
