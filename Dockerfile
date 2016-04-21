FROM frosner/spark-base

MAINTAINER Frank Rosner <frank@fam-rosner.de>

RUN mkdir /etc/service/streamreader
ADD ddq-demo-spark-assembly-1.0.0.jar /etc/service/streamreader/job.jar
ADD streamreader.sh /etc/service/streamreader/run
RUN chmod a+x /etc/service/streamreader/run

CMD ["/sbin/my_init"]
