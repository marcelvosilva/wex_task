To run the project just execute mvn install on project folder and run the .jar with java command.

To access the documentantion, visit http://localhost:8080/swagger-ui/index.html when project is running

To access health status, visit http://localhost:8080/actuator/health when project is running

Points of improvement:
- Study the possibility of caching the results. What is the periodicity of new exchange rates?
- Implement Oauth2 client with an Oauth2 server. Depends on the environment
- Explore more actuator services to monitor the application.
- Maybe explore adding logs (although I think that the responses already give a clear reason to failures or successes)

Final observations:
- Tried to implement the same task with Golang only for curiosity and learning. Couldn't finish it though.
- Of course my code has a lot of things to improve. So if you have any tips or lessons, please let me know.