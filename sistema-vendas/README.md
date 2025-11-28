Iniciar aplicação: docker-compose up -d
Acessar o container do PostgreSQL: docker exec -it postgres_sistema psql -U postgres -d sistema_vendas
Executar aplicação: ./mvnw spring-boot:run
URL da Aplicação: http://localhost:8080

Credenciais de Login:
Usuário: admin
Senha: admin123

Páginas principais:
Login: http://localhost:8080/login
Produtos: http://localhost:8080/produtos
Clientes: http://localhost:8080/clientes


Comandos úteis dentro do PostgreSQL:
Ver dados dos usuários: SELECT * FROM usuarios;
Ver dados das roles: SELECT * FROM roles;
Sair do PostgreSQL: \q
