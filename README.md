

## Analise do primerio desafio

1) Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela ser� feita uma consulta no banco de dados para pegar as informa��eses do usu�rio e exibi-las de forma personalizada. Quando há um pico de logins simultaneos, o carregamento desta tela fica demasiadamente lento. Na sua vis�o, como poder�amos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Reposta: Pode se pensar em escalar uma aplica��o de login em cloud conteinizando-o e configurando-o para trabalhar com balanceamento de carga (loadbalance) como o NGinx ou outro balanceador em n�vem. Um balanceador permite que muitas requisi��es 
sejam distribu�das para mais conteineres de servidores com a mesma aplica��oo de login. Sendo assim caso necess�rio atender a milhares de requisi��es ao mesmo tempo um balanceador poder�o instanciar novos conteineres com a mesma aplica��o de login e distribuindo a carga de requisi��o entre esses novos conteineres escalando de forma horizontal. 


## Modo de cria��o da API
 A id�ia inicial do projeto � criar uma api que disponibiliza validar acesso de usu�rio a api inserindo login tanto por layout quanto por requisi��o nos metodos http com resposta e Json.
 Esse projeto ser� desenvolvido local com STS Spring, vesionado pelo git, armazenado no repositorio github e build/deploy no 
 [Heroku](https://www.heroku.com/) como servidor em n�vem com alguns acessos limitados apenas para experimento.

 Para essa api precisa criar um ambiente local utilizando os seguintes recursos:

  * Ferramenta STS Spring com os recursos do Spring-boot e Java 8; 
   
  * Dependencias com Maven: -spring-boot na vers�o 2.0.3.RELEASE;

  * spring-security, spring-data-jpa, tomcat, thymeleaf;

  * [Flywaydb](https://flywaydb.org/) para gerenciamento e migra��o de banco no postgre;

  * container docker do banco de dados postgre vers�o 9.6.1 local em desenvolvimento; 
  
  Exemplo:
  docker run --name postgresql -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=sample -d postgres:9.6.1



## Passos do desenvolvimento

* Cria��o do projeto inicial com suas dependencias em maven;

* Cria��o das classes modelo(Usuario);

* Cria��o das classes Reposotory(Usuario);

* Cria��o das classes de controller(Usuario); 

* Cria��o da query que busca o usu�rio por email;

* Configura��o de seguran�a de acesso em Bacic;

* Cria��o do servi�o de UserDetail para autenticar login;

* Configura��o de permiss�o de acesso dos endpoints;

* Configura��o de filtro para habilitar Cors requisitados pelos browsers;

* Testes spring com api httpcomponents para autentica��o de login;

* Teste de requisi��es com a ferramenta Postman.

* Documenta��o no Readme;

* Manual de integra�ao pelo [Postman](https://documenter.getpostman.com/view/2826688/intelipost-api/RWEjowsV) para equipe front-end;


## Configura��es no Heroku 
[Heroku](https://www.heroku.com/) � um servidor na n�vem que hospeda aplica��es e apis de servi�os podendo configurar sua apica��o para utilizar recursos como banco de dados em n�vem e permitir acessos simultaneos escalando-os tanto verticalmente quanto horizontalmente conforme o plano adquirido.
Ela j� trabalha de maneira integrada com o GitHub para fazer pull do c�digo fonte, build e deploy 
assim quando detectado altera��o numa branch master. 

O Heroku disponibiliza configura��o de n�vem tanto pela plataforma web ou baixando o [heroku-cli](https://devcenter.heroku.com/articles/heroku-cli) para configurar o ambiente via linha de comando.


### Pelo Terminal
Dentro do projeto intelipost-api digite:

1) Fa�a o login na sua conta Heroku e siga as instru��es para criar uma nova chave p�blica SSH.

 - $heroku login

2) Fa�a algumas altera��es no c�digo dentro do projeto e implemente-as no Heroku usando o Git.

- $ git add .
- $ git commit -am "primeiro commit"
- $ git push heroku master

Pronto, j� est� deployado no Heroku.
Por�m, o heroku possui um recurso que ser� utilizado integrando o github sem precisar fazer esses passos acima apenas dando push no para o github brach master.


### Configurando banco Postgre
Para criar banco de dados na n�vem foi necessiario digitar as seguintes instru��es dentro do projeto intelipost-api

1. Criando o banco postgre free:
- $ heroku addons:create heroku-postgresql:hobby-dev

2. Buscando variavel de ambiente do banco criado:
- $ heroku config:get 

	DATABASE_URL: postgres://eshaufvsjtavac:f09a866e7d36dd2b8cc4329299bb927c72341eb066eb6e79bef9c16b5016bdef@ec2-54-225-76-243.compute-1.amazonaws.com:5432/datb3qhjdqo9qa

3. Criando variaveis de ambiente pela decomposi��o da vari�vel DATABASE_URL
A vari�vel DATABASE_URL � dividido em tres partes. Essas tres partes devem der criadas separadas conforme exemplo abaixo.

- $ heroku config:set JDBC_DATABASE_URL=jdbc:postgresql://ec2-54-225-76-243.compute-1.amazonaws.com:5432/datb3qhjdqo9qa
- $ heroku config:set JDBC_DATABASE_USERNAME=eshaufvsjtavac
- $ heroku config:set JDBC_DATABASE_PASSWORD=f09a866e7d36dd2b8cc4329299bb927c72341eb066eb6e79bef9c16b5016bdef

Pronto, j� est� criado as variaveis de ambiente para a api na n�vem

## Criando arquivo application-prod.properties
 Esse arquivo deve ser criado dentro da pasta src/main/resource. Ele � necess�rio quando o projeto for dado push para produ��o onde as vari�veis de ambiente acima ir�o conectar o projeto ao banco postgres em produ��o na n�vem.

1. Exemplo:

* spring.datasource.url={JDBC_DATABASE_URL}
* spring.datasource.username={JDBC_DATABASE_USERNAME}
* spring.datasource.password={JDBC_DATABASE_PASSWORD}
* spring.datasource.driver-class-name=org.postgresql.Driver


## Criando o arquivo Procfile
Esse arquivo � exigida pelo Heroku para configurar a api para funcionar em seu ambiente interno, configurando assim porta do servidor, arquivos de produ��o e local do jar compilado para ser rodado. Ele deve ser criado dentro do projeto intelipost-api

1. Exemplo
* web: java -Dserver.port=$PORT -Dspring.profiles.active=prod $JAVA_OPTS -jar target/intelipost*.jar


2. Ap�s essas configura��es rode:
- $ git add .
- $ git commit -am "configuracao de ambiente"
- $ git push heroku master

## Builder e Deploy
O procedimento de builder est� por conta do servi�o Heroku que ao ser notificado pelo github que uma branch master obteve uma nova altera��o faz pull do projeto direto no repositorio, testa as dependencias e faz deploy do projeto j� disponibilizando acesso. 


## LINKS
1. Endere�o da API INTELIPOST-API 
* https://intelipost-api.herokuapp.com/

2. Repositorio GitHub e Heroku
* GitHub em repositorio: https://github.com/WilliamRegesDeveloper/job-backend-developer.git
* GitHeroku em produ��o: https://git.heroku.com/intelipost-api.git

3. Manual de Integra��o
* Manual de integra��o front-end a api [Postman](https://documenter.getpostman.com/view/2826688/intelipost-api/RWEjowsV);


## Como foi resolver seu teste
 Esse teste foi bom para aumento de conhecimento sobre o que a empresa exige de n�s desenvolvedores. Para mim � um desafio poder trabalhar com apis em n�vem e poder amadurecer mais meus conhecimentos. N�o trabalhei ainda totalmente em nuvem como google cloud ou AWS. Os clientes em que trabalhei sempre trabalham de maneira hibrida tanto com sistemas erp em infra-estrutura onprimeces com algumas coisas em n�vem. Tenho convivencia com servidores de aplica��o Tomcat, Widfly, Glassfish e apis rest tanto em Apache Tomcat quanto rodando no pr�prio heroku. 

 Sobre CI/CD j� implantei Jenkins integrado com GitLab para entrega cont�nua em servidor onpremice onde a ferramenta faz builder e deploy no Apache Tomcat. Por�m, para esse teste deixei por conta do pr�prio heroku essa fun��o de entrega cont�nua.

 Sobre trabalhar com aplica��o em loadbalance � um assunto novo que estou me aprofundando em conhecer e melhorar meu conhecimento.

 Sobre criar layous no spring-boot, apenas trabalhei comm integra��o de apis rest. Layouts j� criei em outros sistemas por�m em JSF.

 Sobre trabalhar em banco de dados, j� trabalhei com outros bancos com sqlserver, mysql e firebird criando tabelas relacionais, desenvolvendo queries, store procedure e view. Postgres � novo para mim. Mas n�o percebi muita diferen�a j� que consegui crialo na n�vem e tamb�m dockeriza-lo em ambiente de desenvolvimento local. 

 Gosto do que fa�o e aprendo a cada dia mais algo novo. Assim melhoro meu conte�do e conhecimento sobre o que o mercado pode utilizar como tecnologia e inova��o.



