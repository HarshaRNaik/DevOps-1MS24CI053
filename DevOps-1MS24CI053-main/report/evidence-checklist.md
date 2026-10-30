# DevOps Assignment Evidence Checklist

`VERIFIED — SCREENSHOT CAPTURED` is used only when a genuine image file exists in `report/screenshots/`. A verified command without a saved screenshot remains `PENDING`. Do not submit placeholder or simulated evidence.

## Component 1 — CI Pipeline

| # | Screenshot file | Status | What it proves / remaining action |
|---|---|---|---|
| 1 | `C1-01-git-history.png` | PENDING | Local `main` now contains merge `a962cf8` from feature branch `feature/devops-rubric` (`15340e2`), but those commits are not pushed to GitHub. Push both branches, then save a genuine screenshot of the remote branch list and merge history. |
| 2 | `C1-02-jenkins-config.png` | BLOCKED | No Jenkins installation or job configuration is available. Install/configure Jenkins, the source repository, SCM polling, and pipeline definition, then capture the job configuration. |
| 3 | `C1-03-jenkins-success.png` | BLOCKED | No Jenkins build has run. Capture the real console output only after a successful build. |
| 4 | `C1-04-artifact.png` | BLOCKED | No Jenkins artifact has been archived. Capture the artifact link on a successful build page after the pipeline archives it. |
| 5 | `C1-05-ci-enhancement.png` | PENDING | The Maven suite passed (3 tests, 0 failures); Jenkins defines commit-SHA artifact naming and `.github/workflows/ci.yml` runs build/tests and uploads a SHA-named artifact. Push the workflow and capture a successful GitHub Actions run or Jenkins artifact. |

## Component 2 — Ansible, Docker and Kubernetes

| # | Screenshot file | Status | What it proves / remaining action |
|---|---|---|---|
| 6 | `C2-01-ansible.png` | BLOCKED | Ansible is unavailable in Ubuntu WSL and the distribution is currently stopped. Install Ansible, start Ubuntu, run the playbook successfully, and capture the actual `PLAY RECAP`. |
| 7 | `C2-02-docker.png` | BLOCKED | Docker CLI/daemon is unavailable. Start Docker Desktop, build the image from the project Dockerfile, and capture the file plus actual successful build output. |
| 8 | `C2-03-pods-before.png` | BLOCKED | No Kubernetes CLI/cluster is available. After loading the image and applying the manifest, capture three running and ready pods before scaling. |
| 9 | `C2-04-pods-after.png` | BLOCKED | No Kubernetes CLI/cluster is available. Scale the running deployment to five, wait for readiness, and capture all five running and ready pods. |
| 10 | `C2-05-service.png` | BLOCKED | No Kubernetes Service has been applied or queried. Capture `kubectl get svc` from the live cluster. |
| 11 | `C2-06-api-response.png` | BLOCKED | The packaged API was verified locally, but no Kubernetes Service response was tested. Port-forward the deployed Service, run a real `curl.exe` request, and capture its response. |
| 12 | `C2-07-enhancement.png` | BLOCKED | The ConfigMap and liveness/readiness probes are present in the Kubernetes manifest, but no cluster applied or verified them. Apply the manifest and capture live ConfigMap and pod probe evidence. |

## Capture and save genuine screenshots

1. Put the actual tool window and relevant output on screen; maximize the window and enlarge the terminal font so the text is readable.
2. Use Windows Snipping Tool (`Win`+`Shift`+`S`) to capture the relevant region. Do not edit the captured content to simulate a result.
3. Save the original PNG in this directory using the exact filename in the checklist.
4. Keep passwords, tokens, and other credentials out of view.
5. Change an item to `VERIFIED — SCREENSHOT CAPTURED` only after confirming that the named PNG file exists and shows the real evidence described above.

## Verified without a saved screenshot

- `.\mvnw.cmd -B -q clean package` completed successfully from the project folder; all 3 automated tests passed, with no failures or errors.
- Local `main` has a real feature commit and merge commit, but both are still ahead of `origin/main` and need to be pushed for remote evidence.
- No screenshots are currently present in `report/screenshots/`.
