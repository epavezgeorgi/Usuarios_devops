# Usuarios DevOps

Microservicio de gestión de Usuarios, Productos y Tarjetas, desarrollado en **Java 25** con **Spring Boot** y **Maven**.

## Estrategia de ramificación: GitFlow

Este proyecto utiliza **GitFlow** como estrategia de ramificación, por las siguientes razones:

- Permite separar claramente el código **estable en producción** (`main`) del código **en integración** (`develop`).
- Facilita el trabajo colaborativo: cada nueva funcionalidad se desarrolla de forma aislada en una rama `feature/`, sin afectar el trabajo de otros integrantes ni el código estable.
- Ofrece un mecanismo claro para **arreglos urgentes** (`hotfix/`) que deben llegar a producción sin esperar el ciclo normal de desarrollo.
- Es un modelo ampliamente documentado y estándar en la industria, lo que facilita la incorporación de nuevos desarrolladores al equipo.

Se descartó trunk-based development porque, al ser un equipo simulando distintos flujos (features y hotfixes en paralelo), GitFlow ofrece más trazabilidad sobre el origen y destino de cada cambio.

### Estructura de ramas

| Rama | Propósito |
|---|---|
| `main` | Código estable, listo para producción. Solo recibe merges desde `develop` (releases) o `hotfix/*` (arreglos urgentes). |
| `develop` | Rama de integración. Todos los `feature/*` se mergean aquí antes de pasar a `main`. |
| `feature/<nombre>` | Nueva funcionalidad. Nace de `develop` y se mergea de vuelta a `develop` vía Pull Request. |
| `hotfix/<nombre>` | Arreglo urgente sobre producción. Nace de `main` y se mergea a `main` **y** a `develop`. |



## Convención de commits

Se utiliza el estándar [Conventional Commits](https://www.conventionalcommits.org/):

- `feature:' nueva funcionalidad (ej. `feature: agrega modelo de Tarjeta`)
- `fix:` corrección de errores (ej. `fix: corrige contraseña datasource`)
- `docs:` cambios en documentación

## Naming de ramas

- Features: `feature/<descripcion-corta-en-minusculas>` — ej. `feature/repositorio-tarjeta`
- Hotfixes: `hotfix/<descripcion-corta-en-minusculas>` — ej. `hotfix/hotfix-1.1`

## Flujo de merge

1. **Feature → develop**: se abre un Pull Request desde `feature/<nombre>` hacia `develop`. Tras revisión, se mergea y se elimina la rama feature.
2. **Hotfix → main y develop**: se abre un Pull Request desde `hotfix/<nombre>` hacia `main`. Una vez mergeado, el cambio se replica también a `develop` (merge o cherry-pick) para que no se pierda en la siguiente release.
3. **Develop → main**: al cerrar un ciclo de desarrollo (release), se abre un Pull Request de `develop` hacia `main`.

## Estrategia de revisión de Pull Requests

- Todo cambio a `develop` o `main` se realiza mediante Pull Request, nunca con push directo.
- Antes de aprobar un PR se revisa:
  - Que el build compile correctamente (validado automáticamente por GitHub Actions).
  - Que el mensaje de commit siga la convención definida.
  - Que la rama de destino sea la correcta (`feature` → `develop`, `hotfix` → `main`).


## Integración continua (CI)

Se configuró un workflow de **GitHub Actions** (`.github/workflows/ci.yml`) que se ejecuta automáticamente:
- En cada `push` a la rama `develop`.
- En cada `pull request` con destino a `main`.

El workflow compila el proyecto con Maven y ejecuta las pruebas unitarias, asegurando que ningún cambio roto llegue a `main`.

## Cómo ejecutar el proyecto localmente

```bash
./mvnw clean install
./mvnw spring-boot:run
```
