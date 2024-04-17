# Introduction

This application uses web sockets to implement chat service. Web sockets are used to provide
bidirectional , full duplex, persistent connection between client(user) and server.

This is useful for providing real-time messages.

# Prerequsities

- Java 17
- Docker
- Any web browser
Tested in Macbook Pro with intel chip,

# Architecture

I have used basic MVC design with model/service/controller . WebsocketConfig is handled for configurations
needed for message brokers and stomp protocols for websockets.
The flow is attached in this [image](chat_flow.png)

# Database

Have used Mongo DB to save and persist chat messages. The choice is just because it could be faster and in real-time
it is better to use NOSQL databases for performance reasons. We might also SQL databases by proper performance tuning and 
replications.

# How to run

- Run docker-compose yaml file by `docker-compose up` which will spin up mongo DB and express in respective ports.
open `localhost:8081` and provide admin:pass as credentials to open mongo DB using mongo express web interface.
Create database named `chat_app` for our app to use. This name is configured in our docker compose.
- Now use `localhost:8088` to access our application, which wil show the basic front end.
- Once the user entered chatroom, mongo database gets updated. with user details 
- Also attaching video [here](https://www.loom.com/share/c97e865dbd7e42ec9ce4aa6a81ab967c?sid=d048d298-01eb-4b55-8bc0-e3f588950f65)
to understand how front end and back end works

# Frontend and backend connections

I have used basic java script with HTMl/CSS. While user interacts, on specific events, events are sent to
internal message brokers of web sockets. Also web socket endpoint is invoked to perform certain actions
like connectUser, disconnectUser, createChat, etc

You can check main functionality in `usercontroller` and `chatcontroller` and respective service implementation.


# Improvements/Current Issues

- Due to complete front end and back end, I couldn't spend time on adding unit tests. 
- Only few custom exceptions are added
- More logging can be added
- User image wasn't located by javascript 
- Server is not logged by default. In real scenario server will be logged in always
- Basic FAQs can be handled in chat by providing set of static Question and Answers.
- Nowadays, AI is also booming, chat can be handled using AI chatbots by training with sample datasets.
