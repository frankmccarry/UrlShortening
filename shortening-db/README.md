## Instructions for shortening-db

### Setup
to deploy Database at command line run:

docker build -t shorten-db .  
docker run -d -p 5532:5432 --name "shorten-postgres" shorten-db  
