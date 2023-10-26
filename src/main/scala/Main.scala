import LoadedTweets.{loadedList, loadedWrite}
import TwitterFollowing.followingList
import TwitterGetTweet.tweetList
import TwitterLogin.login
import org.openqa.selenium.chrome.ChromeDriver
import TwitterActions._

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {
    implicit val chrome: ChromeDriver = new ChromeDriver()

    val loadedHashSet = mutable.HashSet(loadedList: _*)

    scala.sys.addShutdownHook {
      loadedWrite(loadedHashSet)
    }

    val twitterId = "@forsuger169566"
    login(twitterId, "liens1234")

    followingList(twitterId)
      .flatMap(following => tweetList(following))
      .filterNot(tweetUrl => loadedHashSet.contains(tweetUrl))
      .foreach { tweetUrl =>
        likeAndRepost(tweetUrl)
        loadedHashSet += tweetUrl
      }
  }
}
