# DevOps Lab - Quote API

A minimal Spring Boot REST service used across the DevOps assignment.

## Requirements

- Java 21 or newer
- No global Maven installation is needed; the Maven Wrapper pins Maven 3.9.11.

## Endpoints

- `GET /quote` returns a random quote in a JSON object.
- `GET /quotes` returns all available quotes as a JSON array.
- `GET /health` returns `{"status":"OK"}`.

## Build and run

From this folder, run:

```powershell
.\mvnw.cmd clean package
java -jar target/quote-api.jar
```

The API listens on port 8080 by default. Verify it with:

```powershell
curl.exe http://localhost:8080/health
curl.exe http://localhost:8080/quote
curl.exe http://localhost:8080/quotes
```

Run only the automated tests with `.\mvnw.cmd test`.

## DevOps assignment files

- `Jenkinsfile` runs tests, packages the app, archives commit-SHA-versioned JARs, and polls SCM every five minutes. Configure Jenkins tools named `JDK-21` and `Maven-3.9.11`.
- `.github/workflows/ci.yml` runs the Maven test/package flow on pushes to `main` and `feature/**` branches and pull requests to `main`, then uploads a SHA-named JAR artifact.
- `Dockerfile` builds a Java 21 image. From this folder, build and run it with `docker build -t quote-api:local .` and `docker run --rm -p 8080:8080 quote-api:local`.
- `ansible` deploys the packaged JAR to a local Ubuntu systemd service. From Ubuntu on WSL2, use `/mnt/c/projects/DevOps-1MS24CI053` as the project path, then run `sudo ansible-playbook -i ansible/inventory.ini ansible/site.yml --ask-become-pass`.
- `k8s/quote-api.yaml` defines the ConfigMap, three-replica Deployment, probes, and ClusterIP Service. Build the Docker image and load it into Minikube or Kind before applying the manifest:

```powershell
minikube image load quote-api:local
kubectl apply -f .\k8s\quote-api.yaml
kubectl rollout status deployment/quote-api
kubectl port-forward service/quote-api 8080:8080
```

The Jenkins, Docker, Ansible, and Kubernetes workflows require those tools to be installed and have not been claimed as executed until their real runs succeed. See [REPORT.md](./REPORT.md) and [SCREENSHOT-CHECKLIST.md](./SCREENSHOT-CHECKLIST.md) for submission status and evidence requirements.
