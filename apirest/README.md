## API GERADA EM JAVA 8

## API gerada com banco Mysql
## Se não possuir Mysql instalado, https://dev.mysql.com/downloads/   (mysql shell seria o mais prático para teste)

## Sem sucesso de inicializar a api criando automaticamente um banco, então se possível, criar manualmente o banco apirest.
No terminal:

$ mysql -u root -p

mysql> CREATE DATABASE apirest;

mysql> exit

## Para alterar a config de banco e deixar conforme sua máquina, editar o arquivo:

apirest/src/main/resources/application.properties

Mudar username/password conforme sua config Mysql.

## Caso deseje diminuir o tempo de verificacao para fins de testes:

apirest\src\main\java\com\produtos\apirest\resources\ProdutoResource

Alterar linha:

LocalDateTime dataFinal = dataRegistro.plusMinutes(10);  // 10 está em minutos.

## No terminal e no interior da pasta apirest:
$ mvn clean install

## Após o comando acima, será gerado uma pasta target. No interior dela, executar comando para executar jar:
$java -jar apirest-0.0.1-SNAPSHOT.jar  (ou o nome correto do arquivo .jar que foi criado)

## Com a aplicacao rodando, em uma nova aba do terminal:

Para teste:
~$ curl -XPOST localhost:8080/api/produto -H 'Content-type:application/json' -d '{"id": "1", "name": "teste1"}'

