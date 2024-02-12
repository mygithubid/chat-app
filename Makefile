jar:
	./mvnw package -DskipTests

build:
	docker compose build

up:
	docker compose up