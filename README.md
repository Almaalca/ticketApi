# Readme de la API GraphQL

## Visión general

Esta API GraphQL proporciona funcionalidades para gestionar tickets y sus estados. Permite a los usuarios realizar diversas operaciones, como recuperar tickets y estados, filtrar tickets, crear nuevos tickets, actualizar tickets existentes y eliminar tickets.

## Prerequisitos 
1. Docker compose
2. Tener IDE configurado para correr Sprint boot


## Empezar

Para comenzar con la API GraphQL, sigue los siguientes pasos:

1. Ir a la carpeta docker y ejecutar docker-compose build para preparar la base de datos Postgresql 

2. Ejecutar el comando docker-compose up -d para subir la base de datos Postgresql

3. Ejecutar en su ID el proyecto TicketApi.

4. Accede a la API GraphQL a través de esta url http://localhost:8082/graphiql?path=/graphql.

## Esquema de GraphQL

El esquema de la API GraphQL consiste en los siguientes tipos y operaciones:

### Consulta (Query)

1. `tickets`: Obtiene una lista de todos los tickets.
2. `states`: Obtiene una lista de todos los estados de los tickets.
3. `ticketsFilter(page: Int!, size: Int!): TicketPage`: Obtiene tickets paginados.

### Mutación (Mutation)

1. `createTicket(ticket: TicketDTO): Ticket`: Crea un nuevo ticket.
2. `updateTicket(ticket: TicketDTO): Ticket`: Actualiza un ticket existente.
3. `removeTicket(id: ID!): String`: Elimina un ticket por su ID.

### Tipos (Types)

1. `Ticket`: Representa un ticket con los siguientes campos:
   - `id`: El ID único del ticket.
   - `user`: El usuario asociado con el ticket.
   - `state`: El estado del ticket (hace referencia al tipo `State`).
   - `creationDate`: La fecha en que se creó el ticket (en formato de cadena).
   - `updateDate`: La fecha de la última actualización del ticket (en formato de cadena).

2. `State`: Representa el estado de un ticket con los siguientes campos:
   - `id`: El ID único del estado.
   - `name`: El nombre del estado.

3. `TicketDTO`: Representa los datos necesarios para crear o actualizar un ticket con los siguientes campos:
   - `id`: El ID único del ticket.
   - `user`: El usuario asociado con el ticket.
   - `stateId`: El ID del estado asociado con el ticket.

4. `TicketPage`: Representa una lista paginada de tickets con los siguientes campos:
   - `items`: Un array de objetos `Ticket`.
   - `total`: El número total de tickets disponibles.

## Uso

Para utilizar la API GraphQL, realiza solicitudes POST al punto final de la API con tu cliente GraphQL. Puedes enviar consultas y mutaciones GraphQL para interactuar con los datos.

### Ejemplos

#### Recuperar todos los estados:

```graphql
query {
  states {
    id
    name
  }
}
```

#### Recuperar todos los tickets:

```graphql
query {
  tickets {
    id
    user
    state {
      id
      name
    }
    creationDate
    updateDate
  }
}
```

#### Recuperar los tickets paginados:

```graphql
query {
  ticketsFilter(page:0,size:5) {
    items{	
      id
      user
      state {
        id
        name
      }
      creationDate
      updateDate
   },
   total	
 }
}
```

#### Crear un nuevo ticket:

```graphql
mutation {
  createTicket(ticket: {
    user: "Aldis ALmeida"
    stateId: "ID_ESTADO"
  }) {
    id
    user
    state {
      id
      name
    }
    creationDate
    updateDate
  }
}
```

#### Actualizar un ticket existente:

```graphql
mutation {
  updateTicket(ticket: {
    id: "ID_TICKET_EXISTENTE"
    user: "Aldis Almeida"
    stateId: "ID_ESTADO_ACTUALIZADO"
  }) {
    id
    user
    state {
      id
      name
    }
    creationDate
    updateDate
  }
}
```

#### Eliminar un ticket:

```graphql
mutation {
  removeTicket(id: "ID_TICKET_A_ELIMINAR")
}
```