import java.io.File

import LoadedTweets.{loadedList, loadedWrite}
import TwitterFollowing.followingList
import TwitterGetTweet.tweetList
import TwitterLogin.login
import org.openqa.selenium.chrome.ChromeDriver
import TwitterActions._
import com.github.tototoshi.csv.CSVReader

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {
    implicit val chrome: ChromeDriver = new ChromeDriver()

    val loadedHashSet = mutable.HashSet(loadedList: _*)

    scala.sys.addShutdownHook {
      loadedWrite(loadedHashSet)
    }

    val twitterLoginIte = CSVReader.open(new File("src\\main\\resources\\twitter_login.csv")).iterator
    val twitterId = twitterLoginIte.next().head
    login(twitterId, twitterLoginIte.next().head)

    followingList(twitterId)
      .flatMap(following => tweetList(following))
      .filterNot(tweetUrl => loadedHashSet.contains(tweetUrl))
      .foreach { tweetUrl =>
        likeAndRepost(tweetUrl)
        loadedHashSet += tweetUrl
      }
  }
}
