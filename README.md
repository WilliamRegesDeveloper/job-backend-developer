

## Analise do primerio desafio

1) Imagine que hoje tenhamos um sistema de login e perfis de usu√°rios. O sistema conta com mais de 10 milh√µes de usu√°rios, sendo que temos um acesso concorrente de cerca de 5 mil usu√°rios. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela √© feita uma consulta no banco de dados para pegar as informa√ß√µes do usu√°rio e exibi-las de forma personalizada. Quando h√° um pico de logins simult√¢neos, o carregamento desta tela fica demasiadamente lento. Na sua vis√£o, como poder√≠amos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?

Reposta: Pode se pensar em escalar uma aplica√ß√£o de login em cloud conteinizando-o e configurando-o para trabalhar com balanceamento de carga (loadbalance) como o NGinx ou outro balanceador em n√∫vem. Um balanceador permite que muitas requisi√ß√µes 
sejam distribu√≠das para mais conteineres de servidores com a mesma aplica√ß√£o de login. Sendo assim caso necess√°rio atender a milhares de requisi√ß√µes ao mesmo tempo um balanceador poder√° instanciar novos conteineres com a mesma aplica√ß√£o de login e distribuindo a carga de requisi√ß√£o entre esses novos conteineres.


## Modo de criaÁ„o da API
 O modo como est· estou desenvolvendo uma API b·sica com acesso ao login e seguranÁa BASIC. 
 O banco em homologaÁ„o est· sendo feito no mysql com dependencias do [Flywaydb](https://flywaydb.org/) para ajudar na criaÁ„o da tabela, 
 porÈm pretendo subir a api em um servidor p˙blico, o Heroku para teste.
  Embora conheÁa Jenkins para ser meu servidor de builder e deploy e j· ter implantado em clientes locais esse serviÁo, para esse teste irei tentar fazer deploy no Heroku e tentar utilizar banco Postgree free disponÌvel para teste. 
  
 
## Criando configuaÁıes no heroku para teste

CRIANDO POSTGREE
heroku addons:create heroku-postgresql:hobby-dev

Creating heroku-postgresql:hobby-dev on intelipost-api... free
Database has been created and is available
 ! This database is empty. If upgrading, you can transfer
 ! data from another database with pg:copy
Created postgresql-deep-75860 as DATABASE_URL




heroku config
=== intelipost-api Config Vars
DATABASE_URL: postgres://qguahpxbleqzgb:1600c44ad4919258372ecdbb0700015d5d28f723754e7af262ffa925cd73a5b7@ec2-54-235-253-198.compute-1.amazonaws.com:5432/d452861qqmakjh
JDBC_DATABASE_URL=jdbc:postgresql://ec2-54-235-253-198.compute-1.amazonaws.com:5432/d452861qqmakjh
JDBC_DATABASE_USERNAME=qguahpxbleqzgb
JDBC_DATABASE_PASSWORD=1600c44ad4919258372ecdbb0700015d5d28f723754e7af262ffa925cd73a5b7


## IntegraÁ„o Heroku com GitHub
IntegraÁ„o entre Heroku e GitHub;
Testando builder e deploy com Heroku;
Tentativas de fazer deploy no Heroku






