HzCmd

Install steps

wget http://www.eu.apache.org/dist/activemq/5.13.0/apache-activemq-5.13.0-bin.tar.gz

tar xvf ./apache-activemq-5.13.0-bin.tar.gz

mv ./apache-activemq-5.13.0 ~/.

echo "export ACTIVE_MQ_BIN=~/apache-activemq-5.13.0/bin" >> ~/.bashrc

echo 'PATH=${PATH}:${ACTIVE_MQ_BIN}' >> ~/.bashrc


git clone https://github.com/Danny-Hazelcast/hzCmd.git

cd hzCmd
echo "export HZ_CMD_SRC=$(pwd)" >> ~/.bashrc

mvn clean install dependency:copy-dependencies

echo "export HZ_CMD=$(pwd)/src/main/java/bin" >> ~/.bashrc
echo "export HZ_CMD_BIN=$(pwd)/target" >> ~/.bashrc
echo 'PATH="${HZ_CMD}:${PATH}"' >> ~/.bashrc

