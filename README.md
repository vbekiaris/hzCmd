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





    hzCmd help

    usage: hzCmd <command> [ <args> ]
    
    Commands are:
    
    add         add boxes, clusters, members clients
    async       invokeAsync Async a method with a given thread count on a class identified by taskId,  on a cluster/member/client
    bench       invoke each stage of a benchmark provided the task all point to a class which extends remote.Bench
    broker      broker ip address
    cat         cat cluster/members/clients std out
    chart       chart bench data gnuplot install required
    clean       clean cluster/members/clients
    download    download all files from jvm's with name matching id regex
    exit        system.exit on jvm's in cluster/members/clients
    grep        cat cluster/members/clients
    help        Display help information
    info        print info on managed boxes/clusters/jvm's
    install     install Hazelcast version's onto boxes in cluster
    kill        kill -9 cluster/members/clients
    listen      listen for event about your workspace
    load        load a class
    ls          cat cluster/members/clients std out
    memberBox   set number of dedicated member boxes for a cluster
    ping        ping a cluster's/member's/client's
    restart     restart a jvm in a cluster using its id
    scpUp       file of ip address and ssh login user name
    set         set public member variables of a taskId in some jvms
    ssh         ssh instruction from cwd of each member client
    sync        invoke sync a method with a given thread count on a class identified by taskId,  on a cluster/member/client
    tail        cat cluster/members/clients
    uploadCwd   upload to cwd
    uploadlib   upload to lib dir of a cluster
    wipe        killall -9 java on all box;  rm -fr hzCmd home dir
    
See 'hzCmd help <command>' for more information on a specific command.