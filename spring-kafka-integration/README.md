# Spring kafka integration

## Download Kafka Docker Image
docker pull wurstmeister/kafka

## Create a Docker Network
docker network create kafka-network

## Start ZooKeeper
docker run -d --name zookeeper --network kafka-network -p 2181:2181 -e ZOOKEEPER_CLIENT_PORT=2181 wurstmeister/zookeeper

## Start kafka
docker run -d --name kafka --network kafka-network -p 9092:9092 -e KAFKA_BROKER_ID=1 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_CREATE_TOPICS=test-topic:1:1 wurstmeister/kafka

## Verify Kafka Installation
docker ps