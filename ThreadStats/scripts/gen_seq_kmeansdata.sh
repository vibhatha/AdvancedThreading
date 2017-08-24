outputpath=$1
samples=$2
features=$3
java -cp target/ThreadStats-1.0-SNAPSHOT.jar edu.indiana.ise.stats.sequential.GenerateKMeansData ${outputpath} ${samples} ${features}
