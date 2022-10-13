#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real!이 사용 중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음

function find_idle_profile()
{
    # 스프링부트 정상 수행중인지 확인, 응답값 HttpStauts로 받고 400이상이면 모두 예외로 보고 real2를 현재 profile로 사용
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면(즉,  40x/50x 에러 모두 포함)

    then
        CURRENT_PROFILE=real2
    else
        CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then #IDLE_PROFILE : nginx와 연결되지 않은 profile, 스프링 부트를 이 profile로 연결하기 위해 반환한다.
        IDLE_PROFILE=real2
    else
        IDLE_PROFILE=real1
    fi

    echo "${IDLE_PROFILE}" # bash는 값 반환 기능이 없기 떄문에 echo를 사용해서 결과를 출력하여 값으로 이용한다.
}

# 쉬고있는 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real1 ]
    then
        echo "8081"
    else
        echo "8082"
    fi
}
