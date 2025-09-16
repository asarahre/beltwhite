
## 🥋 Projeto Faixa Branca

#### Sistema para gerenciamento de aulas, graduações e histórico de alunos de artes marciais, com foco em jiu-jitsu e regras da IBJJF.

### 📖 Sobre o Projeto

O Faixa Branca foi idealizado para apoiar professores e academias no controle do aprendizado dos alunos.
Com ele é possível:

Gerenciar planos de aula e avaliações.

Acompanhar o histórico aulas de evolução dos alunos.

Controlar graduações de acordo com as regras mínimas da IBJJF.

Auxiliar o professor a lapidar seus alunos, sabendo onde cada um precisa de mais foco.

Manter informações organizadas sobre datas de graduação, considerando regras mínimas, tempo de cada professor/família e particularidades de cada aluno.

Facilitar o planejamento e acompanhamento das aulas.

Criar um sistema simples e visual para alunos e mestres acompanharem evolução.

Aviso que requisitos mínimos  cumpridos. 

Aviso de frequência menor que 4 nas últimas 4 sememas.

## Docs IBJJF
 https://cbjj.com.br/books-videos

## 🛠️ Tecnologias Utilizadas

Java / Spring Boot (backend)

React / Next.js (frontend) (Não inciado)

PostgreSQL (banco de dados)

Docker 

### Modelagem do projeto

https://github.com/user-attachments/assets/071d3ce6-b267-46ed-bd2c-8887cf38cb76


#### Docker compose  exemplo  'postgres/docker-compose.yml'
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





