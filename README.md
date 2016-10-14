# HzCmd


Docker https://hub.docker.com/r/dannyhazelcast/hzcmd/ ready to go container 
```
    docker pull dannyhazelcast/hzcmd
    
    sudo docker run -p 61616:61616 -it dannyhazelcast/hzcmd /bin/bash
```


1) Install, you will need 
 
 * bash shell
 * git
 * mvn 
 * ~/.bashrc file 
 * hzCmd will install activemq-5.13.3
 * install gnuplot and add to your path for charts
 * killall cmd on remote boxes,  'apt-get install psmisc'  or  'yum install psmisc'
 

```sh
git clone https://github.com/Danny-Hazelcast/hzCmd.git

./hzCmd/install

source ~/.bashrc

hz
```

2) update to the latest version of hzCmd
```
    hz-update
```


3) update to the latest version of hzCmd benchmarks/tests
```
    hz-bench-update
```
 

4) copy benchmarks/tests directory to cwd
```
    hz-bench-get hz
```

  
5) Run your first local bench, you will need password less ssh access onto the local box from the current user

```sh

ssh $(whoami)@127.0.0.1 'pwd'

cd hz/put

./go
```


6) setup aws cli

http://docs.aws.amazon.com/cli/latest/userguide/installing.html
```
sudo pip install awscli
```

http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html
```
$ aws configure
AWS Access Key ID [None]: 123***
AWS Secret Access Key [None]: 123***
Default region name [None]: us-east-1
Default output format [None]: ENTER
```


7) Create aws ec2 instances 
```
    aws-create  --key yourAwsKey --count 1  --instanceType c4.xlarge  --imageId ami-08111162  --region us-east-1  --subnetId subnet-378d2140  --placement hzpc2  --outputFile a.box
```
   if all the default values listed explicitly above are avaliable to you.
```
    aws-create --key yourAwsKey
```

8) terminate aws ec2 instances 
```
    aws-terminate a.box
```
