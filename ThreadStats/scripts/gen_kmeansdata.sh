parallelism=$1
outputpath=$2
samples=$3
features=$4
java -cp target/ThreadStats-1.0-SNAPSHOT.jar edu.indiana.ise.stats.parallel.GenerateKMeansData ${parallelism} ${outputpath} ${samples} ${features}
