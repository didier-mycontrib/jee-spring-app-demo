#se placer (cd) dans répertoire deviseApi 
#(où est situé Dockerfile et target/deviseApi.jar construit par maven)
su
docker image build -t xyz/devise-api  .
docker image ls
docker run -p 8080:8080 -d --name devise-api-container xyz/devise-api
docker container ls
