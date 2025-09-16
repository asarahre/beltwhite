
## 🥋 Projeto Faixa Branca

 #### O **Faixa Branca** é um sistema para apoiar professores e academias no **gerenciamento de aulas, graduações e evolução dos alunos**.

### ✨ Funcionalidades
- Organizar **planos de aula, avaliações e histórico dos alunos**.  
- Controlar **graduações** de acordo com as regras mínimas da **IBJJF**.  
- Emitir **alertas** quando requisitos mínimos forem cumpridos ou se a frequência cair abaixo de **4 aulas nas últimas 4 semanas**.  
- Ajudar professores a **identificar pontos de foco** no desenvolvimento dos alunos.  
- Facilitar o **planejamento e acompanhamento do progresso**, de forma clara e visual.
  
## Docs IBJJF
 https://cbjj.com.br/books-videos

## 🛠️ Tecnologias Utilizadas

Java / Spring Boot (backend)

React / Next.js (frontend) (Não inciado)

PostgreSQL (banco de dados)

Docker 


## Modelagem do projeto

https://github.com/user-attachments/assets/071d3ce6-b267-46ed-bd2c-8887cf38cb76


## Docker compose  exemplo  'postgres/docker-compose.yml'
```
services:
  postgres: 
    image: postgres:latest
    container_name: my-postgres
    environment:
      POSTGRES_PASSWORD: *******
    ports:
      - "5432:5432"
    volumes:
      - postgresvolume:/data
  pgadmin:
    image: dpage/pgadmin4
    container_name: my-pgadmin
    environment:
      PGADMIN_DEFAULT_EMAIL: adm@admin.com
      PGADMIN_DEFAULT_PASSWORD: *******
    ports:
      - "8080:80"
    depends_on:
      - postgres
    volumes:
      - pgadmin_data:/pgadmin
volumes:
  postgresvolume:
  pgadmin_data:
```





