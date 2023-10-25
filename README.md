# Gerenciador de faculdade
A aplicação "Gerenciador de faculdade" é uma API REST **em** **desenvolvimento** com o objetivo de servir como interface entre uma aplicação front-end que o consumirá e o banco de dados (por padrão postgreSQL, mas nada impede uma mudança manual).

O que a aplicação irá permitir:

- Alunos
- Professores
- Matrículas
- Disciplinas e quem as leciona
- Cursos 
- Histórico do aluno por disciplina durante todo seu curso

Features planejadas que ainda **não entraram em desenvolvimento**:

- Adição do restante do corpo acadêmico e secretaria
- Autenticação e autorização com Spring Security e seguindo protocolo OAuth2
- Acesso personalizado a cada usuário (aluno, professor, secretaria, etc.)

## Get Started
1. Clone o reposítório
2. Altere as informações necessárias para funcionar na sua máquina no arquivo [application.properties](src/main/resources/application.properties),
como por exemplo username e password. Da forma que está configurado é necessário criar um banco de dados manualmente 
que se chama db_faculdade (o que pode também ser alterado no arquivo applications), porém, não é necessário criar 
as tabelas, ao rodar a aplicação irá criar as tabelas caso já não exista.
3. Na pasta root caso use o gerenciador de dependencias Maven, execute o comando pelo terminal: mvn spring-boot:run, caso use Gradle, é necessario mudar os arquivos que gerenciam as dependencias e após isso executar com o comando ./gradlew bootRun. Para mais informações sobre como passar o projeto para Gradle deixo o link da documentação Springboot referente a Gradle: https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/
4. A documentação da API se encontra no http://localhost:8080/api-docs.html


