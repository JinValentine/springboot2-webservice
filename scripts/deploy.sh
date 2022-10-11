#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2 # 프로젝트 디렉토리 주소를 변수로 저장
PROJECT_NAME=springboot2-webservice

echo "> Build 파일 복사"

# build 결과물인 jar 파일을 복사해 jar 파일을 모아둔 위치로 복사한다.
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

# 현재 수행 중인 스프링부트 애플리케이션 프로세스 ID를 찾는다.
CURRENT_PID=$(pgrep -fl ${PROJECT_NAME} | grep jar | awk '{print $1})'

echo "현재 구동중인 애플리케이션 pid: $CURRENT_PID"

# if ~ else ~ fi 현재 구동중인 프로세스가 있는지 없는지를 process id값으로 판단해서 기능을 수행한다.
if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 애플리케이션 배포"

# 새로 실행할 jar파일명을 찾는다. 여러 jar파일이 있기 때문에 tail -n으로 가장 최신파일을 변수에 저장하낟.
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME 실행

echo "> $JAR_NAME 실행"

# nohup 명렁어로 터미널을 종료해도 애플리케이션이 계속 구동될 수 있도록 한다.
nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
        -Dspring.profiles.active=real \
        $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &