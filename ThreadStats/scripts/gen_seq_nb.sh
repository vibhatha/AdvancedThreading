trainingPath=$1
testingPath=$2
grounTruthPath=$3
samples=$2
features=$3
java -cp target/ThreadStats-1.0-SNAPSHOT.jar edu.indiana.ise.stats.sequential.GenerateNaiveBayesDAAL ${trainingPath} ${testingPath} ${groundTruthPath} ${samples} ${features}
