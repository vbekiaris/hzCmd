1) Install, you will need git mvn, and a ~/.bashrc file, hzCmd will install activemq-5.13.3


git clone https://github.com/Danny-Hazelcast/hzCmd.git

./hzCmd/install

source ~/.bashrc

hz help



2) Run your first local bench you will need password less ssh login to the local box from the current user


hz-bench-get hz/put

cd put

./go

