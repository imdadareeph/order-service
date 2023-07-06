-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO QUEUES (id,QUEUE_NAME,QUEUE_ENABLED, QUEUE_DESCRIPTION) VALUES (1,'DEV.ORDERSERVICE.V1',true, 'order creation service');
INSERT INTO QUEUES (id,QUEUE_NAME,QUEUE_ENABLED, QUEUE_DESCRIPTION) VALUES (2,'DEV.PAYMENTSERVICE.V1',false, 'payment completion service');

INSERT INTO TOPICS (ID,TOPIC_TYPE, TOPIC_NAME, LASTUPDATE,TOPIC_DESCRIPTION,QUEUES) VALUES (1,'processor','DEV/orderservice/v1/created','15-07-2023 18:47:52.69','order creation success topic','1');
INSERT INTO TOPICS (ID,TOPIC_TYPE, TOPIC_NAME, LASTUPDATE,TOPIC_DESCRIPTION,QUEUES) VALUES (2,'processor','DEV/orderservice/success/v1/created','15-07-2023 18:47:52.69','order creation failure topic','1');
INSERT INTO TOPICS (ID,TOPIC_TYPE, TOPIC_NAME, LASTUPDATE,TOPIC_DESCRIPTION,QUEUES) VALUES (3,'processor','DEV/orderservice/failure/v1/created','15-07-2023 18:47:52.69','order creation failure topic','1');
INSERT INTO TOPICS (ID,TOPIC_TYPE, TOPIC_NAME, LASTUPDATE,TOPIC_DESCRIPTION,QUEUES) VALUES (4,'producer','paymentservice/success/v1/completed','15-07-2023 18:47:52.69','paymentservice completed success topic','2');