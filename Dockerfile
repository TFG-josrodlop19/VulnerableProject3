FROM gcr.io/oss-fuzz-base/base-builder-jvm

RUN apt-get update && apt-get install -y maven

# Copia todo el contenido del proyecto
COPY . /src/

# Establece el directorio de trabajo
WORKDIR /src