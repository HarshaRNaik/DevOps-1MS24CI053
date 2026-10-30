# DevOps Assignment Report

## Title page

- **Title:** DevOps Automation for a Spring Boot Quote API
- **Student name:** [Enter name]
- **USN / roll number:** [Enter ID]
- **Course / subject:** [Enter course]
- **Department / college:** [Enter department and college]
- **Course coordinator:** [Enter coordinator]
- **Academic year / submission date:** [Enter details]

## Certificate

> [Replace with the certificate wording and signature blocks required by the college template. Obtain the required signatures before submission.]

## Abstract

This assignment applies DevOps practices to a small Spring Boot Quote API. The application exposes quote and health endpoints, is built and tested with Maven, and includes configurations for Jenkins continuous integration, containerization, Ansible deployment to Ubuntu, and Kubernetes deployment. The Jenkins pipeline runs automated tests and archives a commit-versioned JAR. The Kubernetes manifest uses three replicas, health probes, and a ConfigMap. Execution evidence is recorded only after each tool is available and the corresponding workflow has actually run.

## 1. Introduction

The objective is to demonstrate a repeatable path from source code to build, test, packaging, container deployment, configuration management, and local orchestration, without requiring paid cloud services. The application has no database dependency.

## 2. Development environment

| Component | Observed / configured |
|---|---|
| Operating system / shell | Windows PowerShell; Ubuntu 26.04 LTS on WSL2 is installed (currently stopped; systemd was previously observed running when the distro was started) |
| Java | OpenJDK 24.0.2 on Windows; project bytecode target is Java 21 |
| Maven | Not installed globally; Maven Wrapper 3.9.11 is included |
| Git | Git for Windows 2.54.0; configured remote is `https://github.com/HarshaRNaik/DevOps-1MS24CI053.git` |
| Git history | Remote `main` was fetched at `abb7ed080df90faedc804c124a5e2c11aca536c7`; local `main` has no commits and the project is an untracked nested folder |
| WSL Java / Ansible | Not installed in Ubuntu; Ansible playbook has not run |
| Docker Desktop / CLI | Docker command unavailable; image/container execution remains unverified |
| Jenkins | Not yet configured or run |
| Ansible | Not yet available in Ubuntu WSL |
| Kubernetes tools / cluster | Not yet configured or run |

## 3. Application implementation

The Spring Boot application is built from `pom.xml` and packaged as `target/quote-api.jar`. Its endpoints are:

- `GET /quote`: one quote in a JSON object.
- `GET /quotes`: all quotes as a JSON array.
- `GET /health`: `{"status":"OK"}`.

Three automated MockMvc tests verify these endpoint responses. The app is configured for Java 21 bytecode and uses Spring Boot 3.5.6.

## 4. CI pipeline and enhancement

`Jenkinsfile` checks out the configured SCM, runs Maven tests, packages the executable JAR, and archives the JARs with fingerprints. It also creates a JAR named with the short Git commit SHA, and polls SCM every five minutes. The CI enhancement is commit-based artifact versioning. This configuration has not yet been executed in Jenkins; configure Jenkins tools named `JDK-21` and `Maven-3.9.11`, then run a job and confirm a repository change triggers a passing build.

## 5. Containerization

`Dockerfile` uses a Maven/Java 21 build stage and a Java 21 runtime stage. The final image runs as a non-root user and exposes port 8080. Docker Desktop and the Docker daemon are unavailable in the observed environment, so image build, container startup, logs, and HTTP checks remain unverified.

## 6. Ansible deployment

The `ansible` directory contains a localhost inventory, playbook, `quote_api` role, port and Java package variables, systemd service template, and restart handler. It deploys the built JAR as a systemd service on Ubuntu and probes `/health`. The WSL Ubuntu distribution is installed and systemd is running, but Ansible is not installed. Run `sudo apt update` and `sudo apt install -y ansible` inside Ubuntu WSL before executing the playbook.

## 7. Kubernetes deployment and enhancement

`k8s/quote-api.yaml` defines a ConfigMap, a three-replica Deployment, and a ClusterIP Service. Matching selectors and port names connect the Service to the pods. Readiness and liveness probes check `/health`. The ConfigMap is the deployment enhancement. Load the locally built image into the chosen Minikube or Kind cluster before applying the manifest. No cluster or rollout has yet been verified.

Example verification after installing and starting the chosen local tools:

```powershell
kubectl apply -f .\k8s\quote-api.yaml
kubectl rollout status deployment/quote-api
kubectl get pods,deployments,services
kubectl scale deployment/quote-api --replicas=5
kubectl rollout status deployment/quote-api
kubectl get pods
kubectl scale deployment/quote-api --replicas=3
kubectl rollout status deployment/quote-api
kubectl port-forward service/quote-api 8080:8080
```

In a second PowerShell terminal, check the forwarded endpoint with `curl.exe http://localhost:8080/health`.

## 8. Results and evidence

Verified locally: Maven Wrapper generation succeeded, `.\mvnw.cmd clean package` succeeded, all 3 tests passed, and the executable JAR started successfully. Live HTTP checks returned `{"status":"OK"}` from `/health`, a JSON quote from `/quote`, and 3 quotes from `/quotes`.

### 8.1 Component 1 — CI Pipeline results

**Figure 1. Git branches, commits, and merge history.** Screenshot file: `report/screenshots/C1-01-git-history.png` — pending. The fetched GitHub `main` history contains prior feature and contributing merges, but only `main` currently exists as a remote branch. The current local assignment changes are not in that history.

**Figure 2. Jenkins job configuration.** Screenshot file: `report/screenshots/C1-02-jenkins-config.png` — blocked; Jenkins is unavailable and no job has been configured.

**Figure 3. Successful Jenkins build console output.** Screenshot file: `report/screenshots/C1-03-jenkins-success.png` — blocked; no Jenkins build has run.

**Figure 4. Archived artifact on a successful Jenkins build.** Screenshot file: `report/screenshots/C1-04-artifact.png` — blocked; no Jenkins artifact has been archived.

**Figure 5. CI enhancement — passing tests or commit-versioned artifact.** Screenshot file: `report/screenshots/C1-05-ci-enhancement.png` — pending. All three Maven tests passed locally and the pipeline defines commit-SHA artifact naming, but there is no screenshot and no Jenkins verification.

### 8.2 Component 2 — Ansible, Docker and Kubernetes results

**Figure 6. Ansible playbook execution and successful `PLAY RECAP`.** Screenshot file: `report/screenshots/C2-01-ansible.png` — blocked; Ansible is not installed in Ubuntu WSL.

**Figure 7. Dockerfile and successful image build.** Screenshot file: `report/screenshots/C2-02-docker.png` — blocked; Docker CLI/daemon is unavailable.

**Figure 8. Three running and ready pods before scaling.** Screenshot file: `report/screenshots/C2-03-pods-before.png` — blocked; no Kubernetes CLI or cluster is available.

**Figure 9. Five running and ready pods after scaling.** Screenshot file: `report/screenshots/C2-04-pods-after.png` — blocked; no Kubernetes CLI or cluster is available.

**Figure 10. Application Service from `kubectl get svc`.** Screenshot file: `report/screenshots/C2-05-service.png` — blocked; no live Kubernetes Service is available.

**Figure 11. API response from the deployed Service.** Screenshot file: `report/screenshots/C2-06-api-response.png` — blocked. The packaged API responded correctly when tested locally, but a Kubernetes-deployed endpoint has not been tested.

**Figure 12. Deployment enhancement — ConfigMap and health probes.** Screenshot file: `report/screenshots/C2-07-enhancement.png` — blocked. The ConfigMap, readiness probe, and liveness probe are defined in the manifest but have not been applied or verified in a cluster.

No screenshots have been fabricated or saved. The [evidence checklist](./report/evidence-checklist.md) records each required filename, status, and remaining action. Save genuine captures under `report/screenshots/` and update their status only after verifying the actual image file exists.

## 9. Conclusion

The application and Maven test/package flow are verified. CI, Docker, Ansible, and Kubernetes configuration files are prepared, but their execution is not yet verified because Jenkins, Docker Desktop, Ansible, and Kubernetes tooling are not available or configured in the observed environment. No required screenshot has been saved. Complete the manual setup, capture genuine evidence, and update this report and [evidence checklist](./report/evidence-checklist.md) with actual results before submission.

## References

- Spring Boot documentation: https://docs.spring.io/spring-boot/
- Apache Maven documentation: https://maven.apache.org/guides/
- Jenkins Pipeline documentation: https://www.jenkins.io/doc/book/pipeline/
- Docker documentation: https://docs.docker.com/
- Ansible documentation: https://docs.ansible.com/
- Kubernetes documentation: https://kubernetes.io/docs/

## Submission reminders

- Replace all title-page placeholders and use the college certificate template.
- Add genuine screenshots and observed outputs; do not submit unverified claims.
- Confirm the repository contains the intended final changes and branch/merge history.
- Do not push from the current nested/untracked workspace: first align the working tree to the fetched repository root so changes are committed at the correct paths.
- Add the course coordinator as a GitHub collaborator if required.
