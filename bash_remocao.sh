#!/bin/bash

# Parando o container
sudo docker stop PyContainer

# Remoção de imagem
sudo docker rmi pycemextractor --force

# Remoção do container
sudo docker rm PyContainer

clear
