

## Analise do primerio desafio

1) Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela é feita uma consulta no banco de dados para pegar as informações do usuário e exibi-las de forma personalizada. Quando há um pico de logins simultâneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Reposta: Pode se pensar em escalar uma aplicação de login em cloud conteinizando-o e configurando-o para trabalhar com balanceamento de carga (loadbalance) como o NGinx ou outro balanceador em núvem. Um balanceador permite que muitas requisições 
sejam distribuídas para mais conteineres de servidores com a mesma aplicação de login. Sendo assim caso necessário atender a milhares de requisições ao mesmo tempo um balanceador poderá instanciar novos conteineres com a mesma aplicação de login e distribuindo a carga de requisição entre esses novos conteineres.


## Modo de cria��o da API
 A id�ia inicial � criar uma api que ser� desenvolvida com o sts do spring, versionado no github feito deploy no servidor Heroku para acesso de todos.
 Para essa api precisa criar um ambiente local utilizando os seguintes recursos:

  -Ferramenta STS Spring com os recursos do Spring-boot e Java 8; 
   
   Dependencias com Maven:
  -spring-boot na vers�o 2.0.3.RELEASE;
  -spring-security, spring-data-jpa, tomcat, thymeleaf;
  -[Flywaydb](https://flywaydb.org/) para gerenciamento e migra��o de banco no postgre ;

   -container docker do banco de dados postgre vers�o 9.6.1 local; 
  
  Exemplo:
  docker run --name postgresql -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=sample -d postgres:9.6.1



## Passos do desenvolvimento

Cria��o do projeto inicial com suas dependencias em maven;
Cria��o das classes modelo(Usuario);
Cria��o das classes Reposotory(Usuario);
Cria��o das classes de controller(Usuario); 
Cria��o da query que busca o usu�rio por email;
Configura��o de seguran�a de acesso em Bacic;
Cria��o do servi�o de UserDetail para autenticar login;
Configura��o de permiss�o de acesso dos endpoints;
Configura��o de filtro para habilitar Cors requisitados pelos browsers;
Testes spring com api httpcomponents para autentica��o de login;
Teste de requisi��es com a ferramenta Postman.
Documenta��o no Readme;
Documenta��o de integra�ao no [Postman](https://documenter.getpostman.com/view/2826688/intelipost-api/RWEjowsV);


## Criando configua��es no heroku para builder e deploy
Heroku � um servidor na n�vem que hospeda apis de dependendo do plano de uso pode ser gratuito.
Ela j� trabalha de maneira integrada com o GitHub para fazer pull do c�digo, buildar e fazer deploy 
assim quando detectado altera��o numa branch master. Funciona parecido com o Jenkins, por�m em n�vem.

LINK DO ENDERE�O DA API INTELIPOST-API 
heroku create intelipost-api:
https://intelipost-api.herokuapp.com/

CRIANDO POSTGREE
heroku-postgresql:hobby-dev:
banco de dados limitado para teste com 8 
configura��o de arquivo application-prod.properties no projeto para linkar ao banco de produ��o;


PLUGANDO HEROKU AO GITHUB para builder deploy
GitHub: https://github.com/WilliamRegesDeveloper/job-backend-developer.git
GitHeroku: https://git.heroku.com/intelipost-api.git







