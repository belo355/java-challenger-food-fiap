
<!-- ABOUT THE PROJECT -->
## Challenger POS-TECH 

Projeto para desenvolvimento de sistema de fast food, para apoio a uma lanchonete de baiiro que está expandindo suas operacoes devido seu grande sucesso. 

Entrega parte 1 (APIs):
* Cadastro de cliente. 
  * idenitficação via cpf
* Criar, editar e remover Produtos
* Buscar produto por categoria
* Fake checkout 
* lista de pedidos 

Mais sobre a aplicação .. 

### Built With

* Java 8+
* Maven 3.3+ 
* Docker


<!-- GETTING STARTED -->
## Vamos  Começar

Abaixo segue configurações locais e exemplo de apis entregues da parte 1 da POS-TECH

### API Especification

http://localhost:8080/swagger-ui/index.html

### Prerequisites

* docker
  ```sh
  sudo apt-get install docker.io
  ```
* docker-compose
  ```sh
  sudo apt-get install docker-compose
  ```

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/belo355/java-challenger-food-fiap.git
   ```
2. Build docker images
   ```sh
   docker-compose build --no-cache --pull
   ```
3. Run docker app
   ```sh
   docker-compose up
   ```



<!-- USAGE EXAMPLES -->
## Usage

Apos pre-requsitos executados, estes sao exempo de funcionamento para o sistema de fastfood



<!-- ROADMAP -->
## Application

- [x] Identificação de cliente 
 ```sh
   curl --location 'http://localhost:8080/cliente/registre' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Jose da Silva",
    "age": "30", 
    "mail": "jose123@gmail.com",
    "document": "12345678910"
}'
   ```

- [x] Criação de pedidos 
 ```sh
curl --location 'http://localhost:8080/order/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productoList": [
        {
            "description": "Refirgerante",
            "brand": "Coca-cola",
            "category": "BEBIDAS",
            "valor": "10"
        }
    ],
    "cliente": {
        "id": 1,
        "name": "Jose da Silva",
        "age": 30,
        "mail": "jose123@gmail.com",
        "document": "12345678910"
    }
}'
   ```




