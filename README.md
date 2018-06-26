

## Analise do primerio desafio

1) Imagine que hoje tenhamos um sistema de login e perfis de usuÃ¡rios. O sistema conta com mais de 10 milhÃµes de usuÃ¡rios, sendo que temos um acesso concorrente de cerca de 5 mil usuÃ¡rios. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela será feita uma consulta no banco de dados para pegar as informaçõeses do usuário e exibi-las de forma personalizada. Quando hÃ¡ um pico de logins simultaneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Reposta: Pode se pensar em escalar uma aplicação de login em cloud conteinizando-o e configurando-o para trabalhar com balanceamento de carga (loadbalance) como o NGinx ou outro balanceador em núvem. Um balanceador permite que muitas requisições 
sejam distribuídas para mais conteineres de servidores com a mesma aplicaçãoo de login. Sendo assim caso necessário atender a milhares de requisições ao mesmo tempo um balanceador poderão instanciar novos conteineres com a mesma aplicação de login e distribuindo a carga de requisição entre esses novos conteineres escalando de forma horizontal. 


## Modo de criação da API
 A idéia inicial do projeto é criar uma api que disponibiliza validar acesso de usuário a api inserindo login tanto por layout quanto por requisição nos metodos http com resposta e Json.
 Esse projeto será desenvolvido local com STS Spring, vesionado pelo git, armazenado no repositorio github e build/deploy no 
 [Heroku](https://www.heroku.com/) como servidor em núvem com alguns acessos limitados apenas para experimento.

 Para essa api precisa criar um ambiente local utilizando os seguintes recursos:

  * Ferramenta STS Spring com os recursos do Spring-boot e Java 8; 
   
  * Dependencias com Maven: -spring-boot na versão 2.0.3.RELEASE;

  * spring-security, spring-data-jpa, tomcat, thymeleaf;

  * [Flywaydb](https://flywaydb.org/) para gerenciamento e migração de banco no postgre;

  * container docker do banco de dados postgre versão 9.6.1 local em desenvolvimento; 
  
  Exemplo:
  docker run --name postgresql -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=sample -d postgres:9.6.1



## Passos do desenvolvimento

* Criação do projeto inicial com suas dependencias em maven;

* Criação das classes modelo(Usuario);

* Criação das classes Reposotory(Usuario);

* Criação das classes de controller(Usuario); 

* Criação da query que busca o usuário por email;

* Configuração de segurança de acesso em Bacic;

* Criação do serviço de UserDetail para autenticar login;

* Configuração de permissão de acesso dos endpoints;

* Configuração de filtro para habilitar Cors requisitados pelos browsers;

* Testes spring com api httpcomponents para autenticação de login;

* Teste de requisições com a ferramenta Postman.

* Documentação no Readme;

* Manual de integraçao pelo [Postman](https://documenter.getpostman.com/view/2826688/intelipost-api/RWEjowsV) para equipe front-end;


## Configurações no Heroku 
[Heroku](https://www.heroku.com/) é um servidor na núvem que hospeda aplicações e apis de serviços podendo configurar sua apicação para utilizar recursos como banco de dados em núvem e permitir acessos simultaneos escalando-os tanto verticalmente quanto horizontalmente conforme o plano adquirido.
Ela já trabalha de maneira integrada com o GitHub para fazer pull do código fonte, build e deploy 
assim quando detectado alteração numa branch master. 

O Heroku disponibiliza configuração de núvem tanto pela plataforma web ou baixando o [heroku-cli](https://devcenter.heroku.com/articles/heroku-cli) para configurar o ambiente via linha de comando.


### Pelo Terminal
Dentro do projeto intelipost-api digite:

1) Faça o login na sua conta Heroku e siga as instruções para criar uma nova chave pública SSH.

 - $heroku login

2) Faça algumas alterações no código dentro do projeto e implemente-as no Heroku usando o Git.

- $ git add .
- $ git commit -am "primeiro commit"
- $ git push heroku master

Pronto, já está deployado no Heroku.
Porém, o heroku possui um recurso que será utilizado integrando o github sem precisar fazer esses passos acima apenas dando push no para o github brach master.


### Configurando banco Postgre
Para criar banco de dados na núvem foi necessiario digitar as seguintes instruções dentro do projeto intelipost-api

1. Criando o banco postgre free:
- $ heroku addons:create heroku-postgresql:hobby-dev

2. Buscando variavel de ambiente do banco criado:
- $ heroku config:get 

	DATABASE_URL: postgres://eshaufvsjtavac:f09a866e7d36dd2b8cc4329299bb927c72341eb066eb6e79bef9c16b5016bdef@ec2-54-225-76-243.compute-1.amazonaws.com:5432/datb3qhjdqo9qa

3. Criando variaveis de ambiente pela decomposição da variável DATABASE_URL
A variável DATABASE_URL é dividido em tres partes. Essas tres partes devem der criadas separadas conforme exemplo abaixo.

- $ heroku config:set JDBC_DATABASE_URL=jdbc:postgresql://ec2-54-225-76-243.compute-1.amazonaws.com:5432/datb3qhjdqo9qa
- $ heroku config:set JDBC_DATABASE_USERNAME=eshaufvsjtavac
- $ heroku config:set JDBC_DATABASE_PASSWORD=f09a866e7d36dd2b8cc4329299bb927c72341eb066eb6e79bef9c16b5016bdef

Pronto, já está criado as variaveis de ambiente para a api na núvem

## Criando arquivo application-prod.properties
 Esse arquivo deve ser criado dentro da pasta src/main/resource. Ele é necessário quando o projeto for dado push para produção onde as variáveis de ambiente acima irão conectar o projeto ao banco postgres em produção na núvem.

1. Exemplo:

* spring.datasource.url={JDBC_DATABASE_URL}
* spring.datasource.username={JDBC_DATABASE_USERNAME}
* spring.datasource.password={JDBC_DATABASE_PASSWORD}
* spring.datasource.driver-class-name=org.postgresql.Driver


## Criando o arquivo Procfile
Esse arquivo é exigida pelo Heroku para configurar a api para funcionar em seu ambiente interno, configurando assim porta do servidor, arquivos de produção e local do jar compilado para ser rodado. Ele deve ser criado dentro do projeto intelipost-api

1. Exemplo
* web: java -Dserver.port=$PORT -Dspring.profiles.active=prod $JAVA_OPTS -jar target/intelipost*.jar


2. Após essas configurações rode:
- $ git add .
- $ git commit -am "configuracao de ambiente"
- $ git push heroku master

## Builder e Deploy
O procedimento de builder está por conta do serviço Heroku que ao ser notificado pelo github que uma branch master obteve uma nova alteração faz pull do projeto direto no repositorio, testa as dependencias e faz deploy do projeto já disponibilizando acesso. 


## LINKS
1. Endereço da API INTELIPOST-API 
* https://intelipost-api.herokuapp.com/

2. Repositorio GitHub e Heroku
* GitHub em repositorio: https://github.com/WilliamRegesDeveloper/job-backend-developer.git
* GitHeroku em produção: https://git.heroku.com/intelipost-api.git

3. Manual de Integração
* Manual de integração front-end a api [Postman](https://documenter.getpostman.com/view/2826688/intelipost-api/RWEjowsV);


## Como foi resolver seu teste
 Esse teste foi bom para aumento de conhecimento sobre o que a empresa exige de nós desenvolvedores. Para mim é um desafio poder trabalhar com apis em núvem e poder amadurecer mais meus conhecimentos. Não trabalhei ainda totalmente em nuvem como google cloud ou AWS. Os clientes em que trabalhei sempre trabalham de maneira hibrida tanto com sistemas erp em infra-estrutura onprimeces com algumas coisas em núvem. Tenho convivencia com servidores de aplicação Tomcat, Widfly, Glassfish e apis rest tanto em Apache Tomcat quanto rodando no próprio heroku. 

 Sobre CI/CD já implantei Jenkins integrado com GitLab para entrega contínua em servidor onpremice onde a ferramenta faz builder e deploy no Apache Tomcat. Porém, para esse teste deixei por conta do próprio heroku essa função de entrega contínua.

 Sobre trabalhar com aplicação em loadbalance é um assunto novo que estou me aprofundando em conhecer e melhorar meu conhecimento.

 Sobre criar layous no spring-boot, apenas trabalhei comm integração de apis rest. Layouts já criei em outros sistemas porém em JSF.

 Sobre trabalhar em banco de dados, já trabalhei com outros bancos com sqlserver, mysql e firebird criando tabelas relacionais, desenvolvendo queries, store procedure e view. Postgres é novo para mim. Mas não percebi muita diferença já que consegui crialo na núvem e também dockeriza-lo em ambiente de desenvolvimento local. 

 Gosto do que faço e aprendo a cada dia mais algo novo. Assim melhoro meu conteúdo e conhecimento sobre o que o mercado pode utilizar como tecnologia e inovação.



