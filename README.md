# Java Spring Boot ecommerce project
This portfolio project is an implementation of my thought process and understanding of these concepts:
- [Domain driven design](https://en.wikipedia.org/wiki/Domain-driven_design)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Hexagonal Architecture](https://medium.com/the-software-architecture-chronicles/ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together-f2590c0aa7f6)
- [Screaming architecture](https://blog.cleancoder.com/uncle-bob/2011/09/30/Screaming-Architecture.html)
----------------------------
The screaming architecture is achieved here by implementing the system as a modulith. This keeps the system decoupled, since a direct communication across domains is not easily achieved by introducing of coupling, but instead by a portal, or domain events.

The particular domains are divided into three possible submodules:
- application
  - the UI layer
- domain
  - domain core
    - behavior defined by real world scenarios
  - application specific domain logic
    - here lies the boundary defined by the input and output ports
    - contains business rules, that are **application specific**
- infrastructure
  - persistence adapters
  - message queue adapters