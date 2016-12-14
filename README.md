# dockerized-se-ci-cycle
A repository for instructions and base project to run a full CI cycle using docker, selenium, java, maven and test-ng.

## Pre-requisites:  
1. [Install docker](https://docs.docker.com/engine/getstarted/step_one/) - When running on old OSs (Win10 before some update) you first need [docker toolbox](https://www.docker.com/products/docker-toolbox) which uses docker-machine and not docker (engine) directly.
*Note - [Hyper-V and virtual box don't go toghether](http://stackoverflow.com/questions/36885985/cannot-start-docker-after-installation-on-windows/39324758#39324758)...  
2. Run commands (for docker toolbox): 
~~~~
docker-machine create -d virtualbox --engine-env HTTP_PROXY=http://proxy.net:8080 --engine-env HTTPS_PROXY=https://proxy.net:8080 --engine-env NO_PROXY=proxy.net default
~~~~
where default = name, d = the driver, proxy - optionally set by environment variable.
