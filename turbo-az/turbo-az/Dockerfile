FROM maven:3.8.6-openjdk-18
WORKDIR /turbo-az
COPY . .
RUN mvn clean package install
EXPOSE 8080
CMD mvn spring-boot:run