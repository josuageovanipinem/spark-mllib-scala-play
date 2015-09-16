package actors

import actors.Receptionist.GetClassifier
import akka.actor.{Props, Actor}
import org.apache.spark.SparkContext
import play.api.Logger

object Receptionist {
  def props(sparkContext: SparkContext) = Props(new Receptionist(sparkContext))

  case object GetClassifier
}

class Receptionist(sparkContext: SparkContext) extends Actor {

  val log = Logger(this.getClass)

  val vectorizer = context.actorOf(Vectorizer.props(sparkContext), "vectorizer")
  val twitterHandler = context.actorOf(TwitterHandler.props(sparkContext), "twitter-handler")
  val classifier = context.actorOf(Classifier.props(sparkContext, vectorizer, twitterHandler), "classifier")
  val corpusInitializer = context.actorOf(CorpusInitializer.props(sparkContext, classifier), "corpus-initializer")


  override def receive = {

    case GetClassifier => {
      log.info(s"Get classifier")
      sender ! classifier
    }
    case undefined => log.info(s"Unexpected message $undefined")
  }
}