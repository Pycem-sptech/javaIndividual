#!/bin/bash

BLUE='0;35'
NC='\033[0m'
VERSAO=11

echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Olá, seja bem vindo a Pycem, serei seu guia para instalar nossa aplicação." 
echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Verificando se possui o Java instalado."
sleep 2

java -version
if [ $? -eq 0 ]; then
    echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Você ja possui o Java instalado"
else
    echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Não encontramos nem uma versão do Java instalado, mas podemos instalar agora!"
    echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Confirme para mim que deseja instalar o Java (S/N). "
    read -r inst
        if [ "$inst" == "S" ]; then
        echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Ok, estamos instalando o Java"
        sudo add-apt-repository ppa:webupd8team/java -y
        sleep 5
       	sudo apt update -y
       	sleep 6
       	clear
       	
       	   if [ "$VERSAO" -eq 11 ]; then
           echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Estamos instalado a versão 17 Java"
       	   sudo apt install openjdk-17-jdk openjdk-17-jre -y
       	   sleep 15
       	   echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Java Instalado com sucesso."
           echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Estaremos atualizando os pacote"
           sudo apt update && sudo apt upgrade -y
           sleep 5
       	   fi
       	else
           echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Obrigado pela informação você optou por não instalar o Java, ate a proxima" 
           exit 0
	fi
fi 
#SEGUNDA PARTE
echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Estarei verificando se você possui o docker instalado"
docker --version >/dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Você ja possui o Docker Instalado"
    else
    echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Podemos instalar o docker na sua maquina (S/N) ?"
    read -r inst
    if [ "$inst" == "S" ]; then
	echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Estamos instando o Docker.."
	sleep 2
	sudo apt install docker.io -y
	sleep 3
	sudo systemctl start docker
	sleep 7
	sudo systemctl enable docker
	sleep 7
	echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Agora vamos criar um container para roda nossa aplicação"
	 sudo docker build -t pycemextractor 
         sudo docker network create redePycem
         sudo docker network connect redePycem pycemBD
         sudo docker run -d --name PyContainer -it --network redePycem -p 8080:8080 pycemextractor
        # sleep 2
    else
    	echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Obrigado pela informação você optou por não instalar o Docker, ate a proxima" 
    	exit 0
    fi
fi
echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Agora vamos verificar se você ja possui o banco "
sudo docker images
if [ "$(sudo docker ps -aqf name=pycemBD)" ]; then
        echo -e "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) VocÃª jÃ¡ possui o banco de dados em seu sistema! Vamos inicializar o container"
        sleep 2
        sudo docker start pycemBD # >/dev/null 2>&1
        sleep 2
        echo -e "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Container pycemBD inicializado!"
        sudo docker ps -a
    else
        echo -e "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) NÃ£o encontramos nenhum banco do MySQL em sua mÃ¡quina, vamos resolver isso!"
        sudo docker pull mysql:5.7
	sleep 2
	sudo docker images
        sleep 2
        echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Agora iremos criar e configurar a aplicaÃ§Ã£o do banco de dados dentro do container"
        sudo docker run -d -p 3306:3306 --name pycemBD -e "MYSQL_DATABASE=banco1" -e "MYSQL_ROOT_PASSWORD=urubu100" mysql:5.7
        sleep 10
        sudo docker start pycemBD
        sleep 3
        sudo docker ps -a
        sleep 2
        sudo docker exec -i pycemBD mysql -uroot -purubu100 -e "USE banco1; CREATE TABLE registro( idRegistro int primary key auto_increment, uso_processador varchar(45), uso_ram varchar(45), uso_hd varchar(45), data_registro datetime not null default current_timestamp);"
        sleep 10
        echo -e "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Tabelas criadas com sucesso!"
fi


#TERCEIRA PARTE
echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Vamos verificar se nossa aplicação ja esta baixada"
ls
sleep 2

if [ -f "$Aplicacao_Java" ]; then
    echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) A nossa aplicação ja exite"
else
    echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Não encontramos nossa aplicação, deseja baixa a nossa aplicação? (S/N) "
    read -r inst
    #inst=$(echo "$inst" | tr '[:upper:]' '[:lower:]')
    if [ "$inst" == "S" ]; then
    	echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Baixando o .jar nossa aplicação"
    	git clone https://github.com/Pycem-sptech/Aplicacao_Java.git
    else
    	echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Você escolheu não baixa nossa aplicação, ate a proxima!"	
    	exit 0
    fi
fi

echo "$(tput setaf 10)[Bot Pycem]:$(tput setaf 7) Iniciando nossa Aplicação. Bem Vindo a Pycem²"

#sleep 3
cd Aplicacao_Java
sleep 2
#sudo chmod +x pycemJar-1.0-SNAPSHOT-jar-with-dependencies.jar
#sleep 2
java -jar pycemJar-1.0-SNAPSHOT-jar-with-dependencies.jar


