#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 Port: $IDLE_PORT"
  echo "> Port 전환"
  echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc
  # 파이프라인 앞의 내용을 service-url.inc에 덮어쓴다. 엔진엑스가 변경할 프록시 주소를 생성한다.

  echo "> 엔진엑스 Reload"
  sudo service nginx reload # 끊김없이 다시 불러오기 위해 restart대신 reload ( 중요 설정 반영에는 restart 써야한다.)
}