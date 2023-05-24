#!/bin/bash

# Cria a imagem do Docker
sudo docker build -t pycemextractor .

# Criando a rede
sudo docker network create redePycem

# Conectando os containers na rede
sudo docker network connect redePycem pycemBD

# Executa o contêiner
sudo docker run -d --name PyContainer -it --network redePycem -p 8080:8080 pycemextractor

# Executa o contêiner do MySql
#docker run -d -p 3306:3306 --name pycemBD -e "MYSQL_DATABASE=banco1" -e "MYSQL_ROOT_PASSWORD=urubu100" mysql:5.7
