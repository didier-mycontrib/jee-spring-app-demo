#se placer (cd) dans répertoire deviseApi 
#(où est situé Dockerfile et target/deviseApi.jar construit par maven)
su
docker image build -t xyz/devise-api  .
docker image ls
docker run -p 8282:8282 -d --name devise-api-container --network mynetwork --network-alias=devise.api.host xyz/devise-api
docker container ls
