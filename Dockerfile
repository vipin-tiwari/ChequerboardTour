FROM bulf/ubuntu-java8-gradle-docker

RUN mkdir /home/src
COPY src /home/src
COPY build.gradle /home/build.gradle
COPY gradlew /home/gradlew
COPY settings.gradle /home/settings.gradle

WORKDIR /home/
RUN gradle build --no-daemon 

ENTRYPOINT ["java","-jar","/home/build/libs/Truecaller-1.0-SNAPSHOT.jar"]

CMD /bin/bash