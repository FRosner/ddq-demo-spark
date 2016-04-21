#!/bin/sh

exec /usr/local/spark/bin/spark-submit --master local[*] /etc/service/streamreader/job.jar
