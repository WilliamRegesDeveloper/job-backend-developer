

## Analise do primerio desafio

1) Imagine que hoje tenhamos um sistema de login e perfis de usuÃ¡rios. O sistema conta com mais de 10 milhÃµes de usuÃ¡rios, sendo que temos um acesso concorrente de cerca de 5 mil usuÃ¡rios. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela Ã© feita uma consulta no banco de dados para pegar as informaÃ§Ãµes do usuÃ¡rio e exibi-las de forma personalizada. Quando hÃ¡ um pico de logins simultÃ¢neos, o carregamento desta tela fica demasiadamente lento. Na sua visÃ£o, como poderÃ­amos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Reposta: Pode se pensar em escalar uma aplicaÃ§Ã£o de login em cloud conteinizando-o e configurando-o para trabalhar com balanceamento de carga (loadbalance) como o NGinx ou outro balanceador em nÃºvem. Um balanceador permite que muitas requisiÃ§Ãµes 
sejam distribuÃ­das para mais conteineres de servidores com a mesma aplicaÃ§Ã£o de login. Sendo assim caso necessÃ¡rio atender a milhares de requisiÃ§Ãµes ao mesmo tempo um balanceador poderÃ¡ instanciar novos conteineres com a mesma aplicaÃ§Ã£o de login e distribuindo a carga de requisiÃ§Ã£o entre esses novos conteineres.


## Modo de criação da API
 A idéia inicial é criar uma api que será desenvolvida com o sts do spring, versionado no github feito deploy no servidor Heroku para acesso de todos.
 Para essa api precisa criar um ambiente local utilizando os seguintes recursos:

  -Ferramenta STS Spring com os recursos do Spring-boot e Java 8; 
   
   Dependencias com Maven:
  -spring-boot na versão 2.0.3.RELEASE;
  -spring-security, spring-data-jpa, tomcat, thymeleaf;
  -[Flywaydb](https://flywaydb.org/) para gerenciamento e migração de banco no postgre ;

   -container docker do banco de dados postgre versão 9.6.1 local; 
  
  Exemplo:
  docker run --name postgresql -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=sample -d postgres:9.6.1



## Passos do desenvolvimento

Criação do projeto inicial com suas dependencias em maven;
Criação das classes modelo(Usuario);
Criação das classes Reposotory(Usuario);
Criação das classes de controller(Usuario); 
Criação da query que busca o usuário por email;
Configuração de segurança de acesso em Bacic;
Criação do serviço de UserDetail para autenticar login;
Configuração de permissão de acesso dos endpoints;
Configuração de filtro para habilitar Cors requisitados pelos browsers;
Testes spring com api httpcomponents para autenticação de login;
Teste de requisições com a ferramenta Postman.
Documentação no Readme;
Documentação de integraçao no [Postman](https://documenter.getpostman.com/view/2826688/intelipost-api/RWEjowsV);


## Criando configuações no heroku para builder e deploy
Heroku é um servidor na núvem que hospeda apis de dependendo do plano de uso pode ser gratuito.
Ela já trabalha de maneira integrada com o GitHub para fazer pull do código, buildar e fazer deploy 
assim quando detectado alteração numa branch master. Funciona parecido com o Jenkins, porém em núvem.

LINK DO ENDEREÇO DA API INTELIPOST-API 
heroku create intelipost-api:
https://intelipost-api.herokuapp.com/

CRIANDO POSTGREE
heroku-postgresql:hobby-dev:
banco de dados limitado para teste com 8 
configuração de arquivo application-prod.properties no projeto para linkar ao banco de produção;


PLUGANDO HEROKU AO GITHUB para builder deploy
GitHub: https://github.com/WilliamRegesDeveloper/job-backend-developer.git
GitHeroku: https://git.heroku.com/intelipost-api.git







