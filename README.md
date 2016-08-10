# HzCmd

IMDG bencher

1) Install, you will need a bash shell, git, mvn, and a ~/.bashrc file, hzCmd will install activemq-5.13.3, install gnuplot for charts

```sh
git clone https://github.com/Danny-Hazelcast/hzCmd.git

./hzCmd/install

source ~/.bashrc

hz
```
  
2) Run your first local bench, you will need password less ssh access onto the local box from the current user

```sh

ssh $(whoami)@127.0.0.1 'pwd'

hz-bench-get hz/put

cd put

./go
```
