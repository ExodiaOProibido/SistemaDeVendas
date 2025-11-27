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
Ver todas as tabelas: \dt

Ver dados dos usuários: SELECT * FROM usuarios;

Ver dados das roles: SELECT * FROM roles;

Resetar senha do admin (senha: 123456): UPDATE usuarios SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMye.vbUO2ZQkQYc9rKqYUVaD7WQ81a6yTe' WHERE username = 'admin';

Sair do PostgreSQL: \q