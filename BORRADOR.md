Lo único que no me gusta (y puede darte problemas de dependencias circulares) es esto:

private final AuthUseCase authUseCase;


en CustomUserDetailsService.

Ahí estás metiendo a toda la capa de aplicación (use case) dentro de la capa de infraestructura / seguridad, y eso puede generar círculos tipo:

SecurityConfig → CustomUserDetailsService → AuthUseCase → (algo que usa SecurityConfig o AuthenticationManager)

La forma más limpia con DDD / clean es que CustomUserDetailsService use el puerto de dominio UserRepository, no el caso de uso.