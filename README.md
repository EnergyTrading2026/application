# Energy Trading Backend

## Dev
From project root (`/`):
```bash
# Start PostgreSQL only
docker-compose up -d postgres
```

From `backend/`:
```bash
./gradlew bootRun
```
Or just run from IDE

## Prod
From project root (`/`):
```bash
docker-compose up -d --build
```

## Test (examples, to verify API is online)
```bash
curl http://localhost:8080/api/time-series

curl -X POST http://localhost:8080/api/time-series \
  -H "Content-Type: application/json" \
  -d '{"value": 1500.0, "unit": "EUR", "modelIdentifier": "MILP", "timestamp": "2025-05-09T10:00:00+00"}'
```

## Swagger 
For local runs Swagger UI is available at http://localhost:8080/swagger-ui.html (excluded for later prod deployments)

