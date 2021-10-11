DEFAULT_PROJECT_NAME="tobys_spring_vol1"
DEFAULT_PORT=3306

# config----------------------------------
ROOT_USER=root
ROOT_PASSWORD=1234
LOCAL_DB_DATA_DIR=/var/lib/mysql/$DEFAULT_PROJECT_NAME/
# END / config----------------------------------


PROJECT_NAME=${1:-$DEFAULT_PROJECT_NAME}
PORT=${2:-$DEFAULT_PORT}


echo "-----------------------------------------"
echo "PROJECT_NAME : $PROJECT_NAME && PORT : $PORT"
echo " "
echo "Looking for a container on port: $PORT"

ID=$(
  docker container ls --format="{{.ID}}\t{{.Ports}}" |
    grep ${PORT} |
    awk '{print $1}'
)


echo " "
echo "Found Container ID: ${ID}"
echo " "
echo "Stopping and removing it"

docker container stop ${ID}
docker container rm ${ID}

echo " "
echo "-----------------------------------------"
echo " "

# 이미지
docker pull mysql
docker run --name mysql -e MYSQL_ROOT_PASSWORD=1234 --platform linux/x86_64 -d -p 3306:3306 mysql:latest

