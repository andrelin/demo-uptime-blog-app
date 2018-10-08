# Docker

```
docker build --no-cache . -t demo
docker run -it --rm -p 8080:8080 demo

docker tag demo eu.gcr.io/andrelin-dev/demo
docker push eu.gcr.io/andrelin-dev/demo
```

# Kubernetes

```
kubectl set image deployment/demo demo=eu.gcr.io/andrelin-dev/demo:latest

kubectl get pods
kubectl get service
```
