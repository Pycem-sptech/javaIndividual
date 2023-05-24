# Imagem base
FROM openjdk:17

# Copiando jar para container
COPY target/pycemIndividual.jar /app/pycemIndividual.jar

# Definindo o diretório de trabalho
WORKDIR /app

# Definindo o comando de execução 
ENTRYPOINT ["java", "-jar", "pycemIndividual.jar"]
