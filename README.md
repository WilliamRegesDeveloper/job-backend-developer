

## Analise do primerio desafio

1) Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela é feita uma consulta no banco de dados para pegar as informações do usuário e exibi-las de forma personalizada. Quando há um pico de logins simultâneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Reposta: Pode se pensar em escalar uma aplicação de login em cloud conteinizando-o e configurando-o para trabalhar com balanceamento de carga (loadbalance) como o NGinx ou outro balanceador em núvem. Um balanceador permite que muitas requisições 
sejam distribuídas para mais conteineres de servidores com a mesma aplicação de login. Sendo assim caso necessário atender a milhares de requisições ao mesmo tempo um balanceador poderá instanciar novos conteineres com a mesma aplicação de login e distribuindo a carga de requisição entre esses novos conteineres.


## Modo de cria��o da API
 O modo como est� estou desenvolvendo uma API b�sica com acesso ao login e seguran�a BASIC. 
 O banco em homologa��o est� sendo feito no mysql com dependencias do [Flywaydb](https://flywaydb.org/) para ajudar na cria��o da tabela, 
 por�m pretendo subir a api em um servidor p�blico, o Heroku para teste.
  Embora conhe�a Jenkins para ser meu servidor de builder e deploy e j� ter implantado em clientes locais esse servi�o, para esse teste irei tentar fazer deploy no Heroku e tentar utilizar banco Postgree free dispon�vel para teste. 
  
 
## Criando configua��es no heroku para teste

CRIANDO POSTGREE
heroku addons:create heroku-postgresql:hobby-dev

Creating heroku-postgresql:hobby-dev on intelipost-api... free
Database has been created and is available
 ! This database is empty. If upgrading, you can transfer
 ! data from another database with pg:copy
Created postgresql-deep-75860 as DATABASE_URL

heroku config:get DATABASE_URL
postgres://qnpcwvhgddhyjl:7f2851e281ad75d6bd466097ca6e75350bdee4b4272cdae8c3a3c3a675a7cbf2@ec2-54-163-229-212.compute-1.amazonaws.com:5432/da5588jp7439dm


heroku config:set JDBC_DATABASE_URL=jdbc:postgre://ec2-54-163-229-212.compute-1.amazonaws.com:5432/da5588jp7439dm
heroku config:set JDBC_DATABASE_USERNAME=qnpcwvhgddhyjl 
heroku config:set JDBC_DATA BASE_PASSWORD=7f2851e281ad75d6bd466097ca6e75350bdee4b4272cdae8c3a3c3a675a7cbf2


heroku config
=== intelipost-api Config Vars
DATABASE_URL:           postgres://qnpcwvhgddhyjl:7f2851e281ad75d6bd466097ca6e75350bdee4b4272cdae8c3a3c3a675a7cbf2@ec2-54-163-229-212.compute-1.amazonaws.com:5432/da5588jp7439dm
JDBC_DATABASE_PASSWORD: 7f2851e281ad75d6bd466097ca6e75350bdee4b4272cdae8c3a3c3a675a7cbf2
JDBC_DATABASE_URL:      jdbc:postgresql://ec2-54-163-229-212.compute-1.amazonaws.com:5432/da5588jp7439dm
JDBC_DATABASE_USERNAME: qnpcwvhgddhyjl


## Integra��o Heroku com GitHub
Integra��o entre Heroku e GitHub







