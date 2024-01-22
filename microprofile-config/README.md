
# abstract  

This URL shortener is based on the `microprofile-config` quickstart project.  

## What is it?  
For a given long URL, this application generates a short URL that is easier to remember and use.

## Build and Deploy the Quickstart  

at the command line run 'mvn clean package'  

at the command line run 'docker build -t wildfly-app .'  

at the command line run 'docker run -d --name "short-url-service" -p 8080:8080 -p 8787:8787 wildfly-app'  

