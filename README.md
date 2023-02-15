# trial task project
 API  allow:
- create account;
- update account;
- delete account;
- get account;
- addition, view, modification, and deletion of quotes;
- voting on quotes (either upvote or downvote);
- view of the top 10 bset quotes and top 10 worst
- get random quote
- get line chart

## Docker:
[DockerHub image](https://hub.docker.com/repository/docker/niktia/trial-task/general)

The application uses ports: 7777.

## Rest service:
<details>
    <summary><h3>Examples of methods and endpoints  API:</h3></summary>

- [(POST) create quote] - http://localhost:7777/quote
- [(GET) get quote] - http://localhost:7777/quote/{quoteId}
- [(GET) get all quotes] - http://localhost:7777/quote
- [(GET) get top 10 best] - http://localhost:7777/quote/score/best10
- [(GET) get top 10 worst] - http://localhost:7777/quote/score/worst10
- [(PATCH) update quote] - http://localhost:7777/quote/update/{quoteId}
- [(DELETE) delete quote] - http://localhost:7777/quote/delete/{quoteId}
- [(GET) get line chart] - http://localhost:7777/quote/chart/{quoteId}
- [(POST) create user] - http://localhost:7777/users

</details>
