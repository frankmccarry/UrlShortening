# URL Shortening Service

A URL Shortening Service written in JAVA.

## Modules

### shortening-db

This project builds a Docker image that packages the URL Shortening Service database for deployment. It can be found in [shortening-db](shortening-db).

### url-shortening-service

This project contains the actual implementation of the URL Shortening Service. It can be found in [microprofile-config](microprofile-config).

### shortening-httpd

This project contains a simple HTTP client that can be used to interact with the URL Shortening Service. It can be found in [shortening-httpd](shortening-httpd).