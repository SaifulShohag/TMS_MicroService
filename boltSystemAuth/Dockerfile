FROM openjdk:25-jdk-slim

# Install Maven and utilities (curl, unzip, git)

RUN apt-get update && apt-get install -y maven curl unzip git && rm -rf /var/lib/apt/lists/*

# Set working directory

WORKDIR /app

# Default command: interactive shell

CMD ["/bin/bash"]