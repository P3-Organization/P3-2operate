# P3-2operate
This is the github repository for the P3 project, group 5

I'm telling you now, it's gonna be AWESOME

## Connecting to the database
The project uses a PostgreSQL database running in a docker environment.
To connect to the database, create a .env file in the root directory, with the
variables DB_USERNAME and  DB_PASSWORD . By standard, you should use the
username Postgres and the password should just be password (Or change these values in the application.properties) (NOTE THIS IS ONLY NEEDED WHILE THE SYSTEM IS NOT DOCKERIZED YET!)

Once you have made the .env file, you should be able to run the docker container.
Open a terminal in the project root directory (or use the IDE's terminal) and write:
**docker compose up -d**
This runs the docker containers needed. Once this is run, the first time afterward that 
you run the application, the database will be filled with data from the CSV files (Using flyway)
