# order-service
order-service is a microservice following event driven architectural pattern using Solace as event broker
This service is created to listen to ORDER_CREATED event from topic: orderservice/success/v1/completed and publish to topic: orderservice/success/v1/completed

# Getting Started
By default, the project will run on port 8081 which is configured in application.yml file

## Run the demo
Build the project using maven-plugin
  ```
  mvn clean install -Dmaven.test.skip=true
  java -jar target/order-service.jar
  ```

### Architecture
  * System Design : To be updated
  * Architecture Diagram  : To be updated

### Documentation
  * Solace Queues and topics yaml contains all yml files as git configs
  * solace-topic-queue is used to create queues and topics using git above config automatically.
    This also serves as both configs-server and configs-client to create and fetch queues and topics
  * order-service is a event driven microservice which consumes an event and produces another event using a central [Solace Event orchestration Framework](https://github.com/imdadareeph/event-orchestration-framework)


## Environment for Development
 Those projects were developed with followings.
 * Java SDK 17
 * Spring-boot 3.1
 * Maven 3.8
 * Reactive Spring Webflux
 *

 ## Links to consider
* [Solace Queues and topics yaml](https://github.com/imdadareeph/solace-topic-queue-config)
* [Solace Queues and topic Creation using git config](https://github.com/imdadareeph/solace-topic-queue)
* [Solace Event Driven Framework](https://github.com/imdadareeph/event-orchestration-framework)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/#build-image)
* [Getting started with Solace and Spring](https://www.solace.dev/start-spring-io-help/)
* [Solace Developer Portal](https://solace.dev)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#features.docker-compose)

### Notes
* Keystore `.keystore` is self generated - this shall be used to self sign the application in future.
* Although the service is invoked by publishing an event to the respective topics, this service accepts a http request. However, using http request shall not publish an event, instead, it will have a http response.
* More will be documented along with a demo in future.
* Queues and topics are available in git configuration which will be fetched from the config-server.
  Sample is available at `application.yml`


### Clone the application codes
 You need a new directory to clone the codes and you can get the codes from git repo.
 ```
 git clone https://github.com/imdadareeph/solace-topic-queue.git
 ```

### Curl to test the service
  You need a new directory to clone the codes and you can get the codes from git repo.
  ```
  curl --location --request GET 'http://localhost:8081/api/order-service/' \
 --header 'moduleName: orderService' \
 --header 'channelName: WEB' \
 --header 'correlationId: someCorrelationId' \
 --header 'ApplicationCode: Appcodevalue1' \
 --header 'CorrelationId: correaltionValue1' \
 --header 'serviceName: orderService' \
 --header 'Content-Type: application/json' \
 --header 'Cookie: XSRF-TOKEN=bde58e09-aae9-4faf-a6d9-59ec12638b5c' \
 --data-raw '{
     "name": "imdadareephget"
 }'
  ```

### Screenshots  
  * H2 Database configured in `application.properties`
  * orderservice.api.h2-console-port=8082
  * Access http://localhost:8082/
  * `schema.sql`  and `data.sql` are pre the sql scripts and runs on application start.
  ```
  SELECT QUEUE_NAME ,TOPIC_NAME ,TOPIC_TYPE ,QUEUE_DESCRIPTION   FROM QUEUES q join TOPICS t on t.queues = q.id
  ```

### Screenshots
<p>
<img src="https://raw.githubusercontent.com/imdadareeph/order-service/main/screenshots/1-solace-docker.png" align="center" width="800" alt="alt text" title="1-solace-docker.png" />
<img src="https://raw.githubusercontent.com/imdadareeph/order-service/main/screenshots/2-maven-build-and-run.png" align="center" width="800" alt="alt text" title="2-maven-build-and-run.png" />
<img src="https://raw.githubusercontent.com/imdadareeph/order-service/main/screenshots/3-postman-test.png" align="center" width="800" alt="alt text" title="3-postman-test.png" />
<img src="https://raw.githubusercontent.com/imdadareeph/order-service/main/screenshots/4-h2db.png" align="center" width="800" alt="alt text" title="4-h2db.png" />
</p>

