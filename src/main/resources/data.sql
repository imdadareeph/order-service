-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO QUEUES (id,QUEUE_NAME,QUEUE_ENABLED, QUEUE_DESCRIPTION) VALUES (1,'ORDER_CREATED',false, 'order creation service');
INSERT INTO QUEUES (id,QUEUE_NAME,QUEUE_ENABLED, QUEUE_DESCRIPTION) VALUES (2,'PAYMENT_COMPLETED',false, 'payment completion service');

INSERT INTO TOPICS (ID,TOPIC_TYPE, TOPIC_NAME, LASTUPDATE,TOPIC_DESCRIPTION,QUEUES) VALUES (1,'consumer','orderservice/failed/v1/created','15-07-2023 18:47:52.69','order creation success topic','1');
INSERT INTO TOPICS (ID,TOPIC_TYPE, TOPIC_NAME, LASTUPDATE,TOPIC_DESCRIPTION,QUEUES) VALUES (2,'consumer','orderservice/success/v1/completed','15-07-2023 18:47:52.69','order creation failure topic','1');
INSERT INTO TOPICS (ID,TOPIC_TYPE, TOPIC_NAME, LASTUPDATE,TOPIC_DESCRIPTION,QUEUES) VALUES (3,'producer','paymentservice/success/v1/completed','15-07-2023 18:47:52.69','paymentservice completed success topic','2');