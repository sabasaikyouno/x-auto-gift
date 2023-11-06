import java.io.File
import java.time.Duration

import LoadedTweets.{loadedList, loadedWrite}
import TwitterFollowing.followingList
import TwitterGetTweet.tweetList
import TwitterLogin.login
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import TwitterActions._
import com.github.tototoshi.csv.CSVReader

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {
    System.setProperty("webdriver.chrome.driver", "C:\\x-auto-gift\\chrome\\chromedriver_win32\\chromedriver.exe")
    val options = new ChromeOptions()
    options.addArguments("--user-data-dir=C:\\x-auto-gift\\chrome")
    options.addArguments("--profile-directory=Profiel1")

    implicit val chrome: ChromeDriver = new ChromeDriver(options)
    chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))

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
