# neo4j configuration

## Install neo4j using docker
docker run -d --name neo4j-container -p 7687:7687 -p 7474:7474 -e NEO4J_AUTH=neo4j/password neo4j:latest


## Neo4j configuration
spring.neo4j.uri=bolt://localhost:7687
spring.neo4j.authentication.username=neo4j
spring.neo4j.authentication.password=password