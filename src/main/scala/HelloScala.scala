import java.time.Duration
import java.util
import java.util.Properties
import java.util.Collections

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer

import net.liftweb.json._

object HelloScala {
  implicit val formats = DefaultFormats
  case class Query(month: String, year: String, maximum: String, minimum: String, day: String, rayon: String)

  def main(args:  Array[String]): Unit = {

  // consume user requet

  val properties = new Properties()
  properties.put("bootstrap.servers","localhost:9092")
  properties.put("group.id","scala-spark")
  properties.put("key.deserializer", classOf[StringDeserializer])
  properties.put("value.deserializer", classOf[StringDeserializer])
  properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  val producer = new KafkaProducer[String, String](properties)
	val kafkaConsumer = new KafkaConsumer[String, String](properties)
	val ls : util.Collection[String] = util.Arrays.asList("topicA")
	kafkaConsumer.subscribe(ls)
	while (true) {
	      val results = kafkaConsumer.poll( Duration.ofSeconds(100)).asScala
	      for (record <- results.iterator ) {
          // 1. json2object
          println(record.value)
          val x = record.value
          val jValue = parse(s"$x")
          val numbers = jValue.extract[Query]
          println(numbers.minimum, numbers.maximum)
          val y = """[{"ville":"Lyon a","pays":"France s","distance":"70","temperature":"5","humidite":"40","precipitation":"40"},{"ville":"Lyon a","pays":"France s","distance":"70","temperature":"5","humidite":"40","precipitation":"40"},{"ville":"Lyon a","pays":"France s","distance":"70","temperature":"5","humidite":"40","precipitation":"40"},{"ville":"Lyon a","pays":"France s","distance":"70","temperature":"5","humidite":"40","precipitation":"40"}]"""
          val topic = "livetemperature"
          val data = new ProducerRecord[String,String](topic,y)
          Thread.sleep(1000)
          producer.send(data)
	      }
	      println("Cycle")
	}

  }





  // 2. spark connection


  // process
  // 3. send query to spark
  // 4. store response
  // send response
  // 5. send response using kafka topic to webapp

}
