## Instructions for shortening-db

### Setup

To deploy the Apache Http Server at command line run:  
docker build -t apache-httpd .  
docker run -d --name httpd-app -p 80:80 apache-httpd

### Usage
You will also need to add the following line to your hosts file:
127.0.0.1       nip.me

To access the server, go to http://nip.me/ in your browser.

You will need to open your hosts file in notepad in Admin mode in order to save the change.