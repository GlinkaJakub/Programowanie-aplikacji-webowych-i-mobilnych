cd python

sudo docker build -t server .

cd ../view

sudo docker build -t view .

sudo docker run -p 5000:5000 server

sudo docker run -p 8585:80 view
