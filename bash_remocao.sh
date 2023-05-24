#!/bin/bash

# Remoção de imagem
docker rmi pycemextractor --force

# Remoção do container
docker rm PyContainer

clear