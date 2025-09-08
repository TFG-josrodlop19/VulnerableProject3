FROM gcr.io/oss-fuzz-base/base-builder-jvm

RUN apt-get update && apt-get install -y maven

# Copia los archivos necesarios a sus ubicaciones correctas en el contenedor
COPY build.sh /src/
COPY pom.xml /src/jacksondatabind/
COPY src /src/jacksondatabind/src

# Establece el directorio de trabajo
WORKDIR /src/jacksondatabind