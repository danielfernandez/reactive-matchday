
Reactive MatchDay
------------------

**Reactive MatchDay** is a testing Java application that uses the following technology stack:

   * Thymeleaf 3.0 (`3.0.6.RELEASE`)
   * Spring Boot 2.0.0 (`2.0.0.BUILD-SNAPSHOT`)
   * Spring Framework 5 (`5.0.0.BUILD-SNAPSHOT`)
   * Spring WebFlux (`5.0.0.BUILD-SNAPSHOT`)
   * Spring Data MongoDB (`2.0.0.BUILD-SNAPSHOT`)
   * MongoDB (`3.2+`)

Highlights of this application are:

   * Use of Thymeleaf's integration module for Spring 5's WebFlux reactive web framework.
   * Use of Thymeleaf's data-driven support for rendering HTML in a reactive-friendly manner.
   * Use of Server-Sent Events (SSE) rendered in HTML by Thymeleaf from a reactive data stream.
   * Use of Server-Sent Events (SSE) rendered in JSON by Spring WebFlux from a reactive data stream.
   * Use of Spring Data MongoDB's reactive (Reactive Streams) driver support.
   * Use of Spring Data MongoDB's support for infinite reactive data streams based on MongoDB tailable cursors.
   * Use of Thymeleaf's fully-HTML5-compatible syntax
   * Use of many weird, randomly generated team and player names.

