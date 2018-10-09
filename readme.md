## Dependencies
* Have a Google Cloud Platform Account
* Google Cloud SDK
* kubectl (install from gcloud sdk)
* Docker 
* JDK8

## Kubernetes (part 1)
We need to create a cluster and configure the shell for anything to work.
#### Create a cluster:
`gcloud container clusters create demo-cluster --num-nodes=2`

##### Configure shell to use the cluster as default
`gcloud config set container/cluster demo-cluster`

## Docker
#### Build the docker image
`docker build --no-cache . -t demo`

#### Run the docker image
`docker run -it --rm -p 8080:8080 demo`

#### Tag the image with desired tags (defaults to latest)
`docker tag demo eu.gcr.io/andrelin-dev/demo`

#### Push the image to GCR
`docker push eu.gcr.io/andrelin-dev/demo`

#### List all the images in the repository
`gcloud container images list --repository eu.gcr.io/andrelin-dev`

#### List all the versions of an image
`gcloud container images list-tags eu.gcr.io/andrelin-dev/demo`

## Kubernetes (part 2)
#### Deployment
##### Create a deployment
`kubectl run demo --image=gcr.io/andrelin-dev/demo --port 8080`

##### Expose the deployment by creating a load balancer for the deployment
`kubectl expose deployment demo --type=LoadBalancer --port 80 --target-port 8080`

##### Scale the deployment
`kubectl scale deployment demo --replicas=3

#### Check the deployment
##### View the pods and their status 
`kubectl get pods`

##### View the service, get information like IPs
`kubectl get service`

#### Changing the running image:
`kubectl set image deployment/demo demo=eu.gcr.io/andrelin-dev/demo:v2`
##### Rollback
`kubectl set image deployment/demo demo=eu.gcr.io/andrelin-dev/demo:v1`

#### Cleaning up
##### Delete the service 
`kubectl delete service demo`

Also deallocates the load balancer
The load balancer will be deleted asynchronously. Wait for that process to complete by monitoring the output of:

`gcloud compute forwarding-rules list`

The forwarding rule will disappear when the load balancer is deleted.

##### Delete the cluster 
Deletes the resources used by the cluster, including virtual machines, disks, and network resources:

`gcloud container clusters delete demo-cluster`

#### Delete the project
`gcloud projects delete andrelin-dev`

## References
1. [GCP Tutorial - Run a simple app on GCPs Kubernetes Engine](https://cloud.google.com/community/tutorials/kotlin-springboot-container-engine)
2. [Spring.io Tutorial - Build a Simple Spring Boot Kotlin Blog](https://spring.io/guides/tutorials/spring-boot-kotlin/) 
3. [Spring.io Github repo](https://github.com/spring-guides/tut-spring-boot-kotlin)
