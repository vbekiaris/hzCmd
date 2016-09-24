# HzCmd

IMDG bencher

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
  
2) Run your first local bench, you will need password less ssh access onto the local box from the current user

```sh

ssh $(whoami)@127.0.0.1 'pwd'

hz-bench-get hz

cd hz/put

./go
```


2) setup aws cli

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


3) Create aws ec2 instances 
```
    aws-create  --key yourKeyName --count 1  --instanceType c4.xlarge  --imageId ami-08111162  --region us-east-1  --subnetId subnet-378d2140  --placement hzpc2  --outputFile a.box
```


4) terminate aws ec2 instances 
```
    aws-terminate a.box
```


5) get the latest version of hzCmd
```
    hz-update
```


6) get the latest version of hzCmd benchMarks/tests
```
    hz-bench-update
```